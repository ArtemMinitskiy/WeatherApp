package com.example.weatherapp.utils

import com.example.weatherapp.R

object Utils {

    fun formatTemp(temp: String): String? {
        val i = temp.toFloat() - 273
        return String.format("%.2f", i) + " \u00B0C"
    }

    fun setAdapterAnimation(description: String): Int {
        return when (description) {
            "01d" -> R.raw.weather_day_clear_sky
            "02d" -> R.raw.weather_day_few_clouds
            "03d" -> R.raw.weather_day_scattered_clouds
            "04d" -> R.raw.weather_day_broken_clouds
            "09d" -> R.raw.weather_day_shower_rains
            "10d" -> R.raw.weather_day_rain
            "11d" -> R.raw.weather_day_thunderstorm
            "13d" -> R.raw.weather_day_snow
            "50d" -> R.raw.weather_day_mist
            "01n" -> R.raw.weather_night_clear_sky
            "02n" -> R.raw.weather_night_few_clouds
            "03n" -> R.raw.weather_night_scattered_clouds
            "04n" -> R.raw.weather_night_broken_clouds
            "09n" -> R.raw.weather_night_shower_rains
            "10n" -> R.raw.weather_night_rain
            "11n" -> R.raw.weather_night_thunderstorm
            "13n" -> R.raw.weather_night_snow
            "50n" -> R.raw.weather_night_mist
            else -> {
                R.raw.weather_day_clear_sky
            }
        }
    }
}