package com.example.weatherapp.model.listweather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Main {
    @SerializedName("temp")
    @Expose
    val temp: String? = null

    @SerializedName("feels_like")
    @Expose
    val feels_like: String? = null

    @SerializedName("humidity")
    @Expose
    val humidity: String? = null

    override fun toString(): String {
        return "Main{" +
                "temp='" + temp + '\'' +
                "feels_like='" + feels_like + '\'' +
                "humidity='" + humidity + '\'' +
                '}'
    }
}