package com.example.weatherapp.model.listweather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Clouds {
    @SerializedName("all")
    @Expose
    val clouds: String? = null

    override fun toString(): String {
        return "Clouds{" +
                "clouds='" + clouds + '\'' +
                '}'
    }
}