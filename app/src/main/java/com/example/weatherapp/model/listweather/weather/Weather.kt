package com.example.weatherapp.model.listweather.weather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Weather {
    @SerializedName("description")
    @Expose
    val description: String? = null

    @SerializedName("icon")
    @Expose
    val icon: String? = null

    override fun toString(): String {
        return "Weather{" +
                "description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                '}'
    }
}