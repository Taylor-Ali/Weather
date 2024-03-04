package com.leaf.weather.ui.forecast

import com.leaf.weather.R


enum class WeatherConditionTheme(
    val backgroundImage: Int,
    val color: Int,
    val textColor: Int,
    val icon: Int,
) {
    CLEAR(
        R.drawable.forest_sunny,
        R.color.sunny,
        R.color.white,
        R.drawable.clear
    ),
    CLOUDS(
        R.drawable.forest_cloudy,
        R.color.cloudy,
        R.color.white,
        R.drawable.partlysunny
    ),
    SNOW(
        R.drawable.snowy,
        R.color.pale_purple,
        R.color.orange,
        R.drawable.snowflake
    ),
    RAIN(
        R.drawable.forest_rainy,
        R.color.rainy,
        R.color.skyBlue,
        R.drawable.rain
    ),
    WIND(
        R.drawable.windy,
        R.color.pale_grey,
        R.color.pale_yellow,
        R.drawable.wind
    )
}