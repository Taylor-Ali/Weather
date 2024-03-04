package com.leaf.weather.ui.forecast

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.leaf.weather.R
import com.leaf.weather.databinding.FragmentWeatherForecastBinding
import com.leaf.weather.model.LocationData
import com.leaf.weather.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WeatherForecastFragment : Fragment() {
    private var _binding: FragmentWeatherForecastBinding? = null
    private val binding get() = _binding!!
    private val weatherForecastViewModel: WeatherForecastViewModel by viewModels()
    private val args: WeatherForecastFragmentArgs by navArgs()
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var forecastAdapter: ForecastAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()

        args.city?.let {
            weatherForecastViewModel.addAsCityFavorite(city = it)
        }

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.add -> {
                        val action =
                            WeatherForecastFragmentDirections.actionWeatherForecastToCitiesFragment()
                        findNavController().navigate(action)
                        return true
                    }

                    R.id.refresh -> {
                        weatherForecastViewModel.retrieveLocationData()?.let {
                            weatherForecastViewModel.fetchCurrentForecast(it, refresh = true)
                            weatherForecastViewModel.fetchWeatherForecast(it, refresh = true)
                        }

                        return true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner)
    }

    private fun setupObservers() {
        weatherForecastViewModel.currentWeatherForecastLiveData.observe(viewLifecycleOwner) {
            updateForecastUI(it)
            changeWeatherForecastColourScheme(it)

        }

        mainViewModel.locationLiveData.observe(viewLifecycleOwner) {
            if (args.city == null) {
                weatherForecastViewModel.fetchCurrentForecast(it)
                weatherForecastViewModel.fetchWeatherForecast(it)
            }
        }

        weatherForecastViewModel.weatherForecastLiveData.observe(viewLifecycleOwner) {
            showForecastList(it)
        }

        weatherForecastViewModel.showErrorMessageLiveData.observe(viewLifecycleOwner) {
            if (it) showError()
        }

        weatherForecastViewModel.isLoadingLiveData.observe(viewLifecycleOwner) {
            if (it) binding.loadingLayout.visibility = View.VISIBLE
            else binding.loadingLayout.visibility = View.GONE
        }

        weatherForecastViewModel.cityLiveData.observe(viewLifecycleOwner) {
            val location = LocationData(cityName = it.name, longitude = it.lat, latitude = it.long)
            weatherForecastViewModel.fetchCurrentForecast(location, refresh = true)
            weatherForecastViewModel.fetchWeatherForecast(location, refresh = true)
        }
    }

    private fun updateForecastUI(it: CurrentWeatherForecast) {
        binding.currentConditionsLayout.visibility = View.VISIBLE
        binding.temperatureLayout.visibility = View.VISIBLE
        binding.temperatureValue.text = "${it.currentTemp}${getString(R.string.degree_symbol)}"
        binding.forcast.text = it.weatherConditions
        binding.minTempValue.text = "${it.minTemp}${getString(R.string.degree_symbol)}"
        binding.currentTempValue.text = "${it.currentTemp}${getString(R.string.degree_symbol)}"
        binding.maxTempValue.text = "${it.maxTemp}${getString(R.string.degree_symbol)}"
        binding.temperatureValue.setTextColor(
            ContextCompat.getColor(
                requireContext(), it.weatherConditionTheme.textColor
            )
        )
        binding.forcast.setTextColor(
            ContextCompat.getColor(
                requireContext(), it.weatherConditionTheme.textColor
            )
        )

    }

    private fun changeWeatherForecastColourScheme(it: CurrentWeatherForecast) {
        (activity as AppCompatActivity?)!!.supportActionBar?.title = it.cityName
        binding.weatherForecastLayout.background =
            requireContext().getDrawable(it.weatherConditionTheme.color)
        binding.currentConditionsLayout.background =
            requireContext().getDrawable(it.weatherConditionTheme.backgroundImage)
        setStatusBarColor(it.weatherConditionTheme.color)
        setActionBarColor(it.weatherConditionTheme.color)
    }


    private fun showForecastList(it: List<WeatherForecastReport>) {

        forecastAdapter.setItems(it)
        binding.forecastList.visibility = View.VISIBLE
    }


    private fun showError() {
        binding.loadingLayout.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
        binding.message.text = getString(R.string.message_error)
    }


    private fun setStatusBarColor(color: Int) {
        val window = (activity as AppCompatActivity?)!!.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = requireContext().getColor(color)
    }

    private fun setActionBarColor(color: Int) {
        (activity as AppCompatActivity?)!!.supportActionBar?.elevation = 0F
        (activity as AppCompatActivity?)!!.supportActionBar?.setBackgroundDrawable(
            ColorDrawable(
                requireContext().getColor(color)
            )
        )
    }

    private fun setupRecyclerView() {
        binding.forecastList.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL, false
        )

        forecastAdapter = ForecastAdapter()
        binding.forecastList.adapter = forecastAdapter
    }
}