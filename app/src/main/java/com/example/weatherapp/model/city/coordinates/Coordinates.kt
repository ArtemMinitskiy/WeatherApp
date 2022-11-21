package com.example.weatherapp.model.city.coordinates

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Coordinates {
    @SerializedName("lat")
    @Expose
    val latitude: String? = null

    @SerializedName("lon")
    @Expose
    val longitude: String? = null

    override fun toString(): String {
        return "Coord{" +
                "lat='" + latitude + '\'' +
                ", lon='" + longitude + '\'' +
                '}'
    }

}
