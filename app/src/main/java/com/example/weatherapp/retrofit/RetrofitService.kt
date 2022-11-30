package com.example.weatherapp.retrofit

import com.example.weatherapp.model.ModelWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("/data/2.5/forecast?&appid=811938537aa2fa23ff4838a05e5ae044")
    suspend fun getWeather(@Query("lat") latitude: String, @Query("lon") longitude: String): Response<ModelWeather>

    @GET("/data/2.5/forecast?q=Odessa,UA&appid=811938537aa2fa23ff4838a05e5ae044")
    suspend fun getWeather(): Response<ModelWeather>

}