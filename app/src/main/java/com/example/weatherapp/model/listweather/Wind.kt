package com.example.weatherapp.model.listweather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Wind {

    @SerializedName("speed")
    @Expose
    var speed: String? = null

    override fun toString(): String {
        return "Wind{" +
                "speed='" + speed + '\'' +
                '}'
    }
}