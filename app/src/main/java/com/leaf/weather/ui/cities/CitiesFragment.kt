package com.leaf.weather.ui.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.leaf.weather.R
import com.leaf.weather.databinding.FragmentCitiesBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class CitiesFragment : Fragment(), CityAdapter.CityItemListener {
    private var _binding: FragmentCitiesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CitiesViewModel by viewModels()
    private lateinit var cityAdapter: CityAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCitiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.getAllCity()
        viewModel.citiesLiveData.observe(viewLifecycleOwner) {
            cityAdapter.setItems(it)
        }


        val autocompleteFragment =
            childFragmentManager.findFragmentById(R.id.autocomplete_fragment)
                    as AutocompleteSupportFragment

        autocompleteFragment.setPlaceFields(
            listOf(
                Place.Field.NAME,
                Place.Field.LAT_LNG,
                Place.Field.ICON_URL
            )
        )

        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                Timber.i("Place: ${place.name}, ${place.latLng}")
                viewModel.addCity(
                    city = City(
                        name = place.name,
                        lat = place.latLng.latitude,
                        long = place.latLng.longitude,
                    ),
                )
            }

            override fun onError(status: Status) {
                Timber.i("An error occurred: $status")
            }
        })
    }

    private fun setupRecyclerView() {
        binding.cityList.layoutManager = GridLayoutManager(context, 2)

        cityAdapter = CityAdapter(this)
        binding.cityList.adapter = cityAdapter
    }

    override fun onClickedCity(city: City) {
        val action = CitiesFragmentDirections.actionCitiesFragmentToWeatherForecast().apply {
            this.city = city
        }
        findNavController().navigate(action)
    }

    override fun onDeleteItemClick(city: City) {
        viewModel.deleteCity(city)
    }
}