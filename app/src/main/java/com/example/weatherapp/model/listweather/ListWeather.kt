package com.example.weatherapp.model.listweather

import com.example.weatherapp.model.listweather.weather.Weather
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class ListWeather {

    @SerializedName("main")
    @Expose
    var main: Main? = null

    @SerializedName("weather")
    @Expose
    val weather: ArrayList<Weather>? = null

    @SerializedName("wind")
    @Expose
    val wind: Wind? = null

    @SerializedName("dt_txt")
    @Expose
    val date: String? = null

    @SerializedName("clouds")
    @Expose
    val clouds: Clouds? = null

    override fun toString(): String {
        return "ListWeather{" +
                "main=" + main +
                ", weather=" + weather +
                ", wind=" + wind +
                ", dt_txt='" + date + '\'' +
                ", clouds=" + clouds +
                '}'
    }
}