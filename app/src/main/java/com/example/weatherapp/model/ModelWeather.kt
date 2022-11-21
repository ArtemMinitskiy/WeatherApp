package com.example.weatherapp.model

import com.example.weatherapp.model.city.City
import com.example.weatherapp.model.listweather.ListWeather
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class ModelWeather: Serializable {
    @SerializedName("cod")
    @Expose
    val cod: String? = null

    @SerializedName("message")
    @Expose
    val message: String? = null

    @SerializedName("cnt")
    @Expose
    val cnt: String? = null

    @SerializedName("list")
    @Expose
    val listWeather: ArrayList<ListWeather>? = null

    @SerializedName("city")
    @Expose
    val city: City? = null

    override fun toString(): String {
        return "ModelWeather{" +
                "cod='" + cod + '\'' +
                ", message='" + message + '\'' +
                ", cnt='" + cnt + '\'' +
                ", city=" + city +
                ", listWeather=" + listWeather +
                '}'
    }
}

