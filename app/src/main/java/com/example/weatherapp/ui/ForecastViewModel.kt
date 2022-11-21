package com.example.weatherapp.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.model.ModelWeather
import com.example.weatherapp.retrofit.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ForecastViewModel : ViewModel() {
    private var weatherList: MutableLiveData<ModelWeather>? = null
    private fun saveWeatherList(body: ModelWeather) {
        if (weatherList == null) {
            weatherList = MutableLiveData()
            weatherList!!.postValue(body)
        }
    }

    fun getWeatherList(): MutableLiveData<ModelWeather>? {
        return weatherList
    }

    private val BASE_URL = "http://api.openweathermap.org/"

    private fun makeRetrofitService(): RetrofitService {
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(RetrofitService::class.java)
    }

    fun getWeatherDetail(wayLatitude: Double, wayLongitude: Double) {
        val service = makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("mLog", "wayLatitude $wayLatitude, wayLongitude $wayLongitude")
//            val response = service.getWeather("46.48719996790696", "30.720671684814914")
            val response = service.getWeather(wayLatitude.toString(), wayLongitude.toString())

            try {
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        saveWeatherList(response.body()!!)
                        Log.i("mLog", "RESPONSE: ${response.body()!!}")
                    } else {
                        Log.e("mLog", "Error network operation failed with ${response.code()}")
                    }
                }
            } catch (e: HttpException) {
                Log.e("mLog", "Exception ${e.message}")
            } catch (e: Throwable) {
                Log.e("mLog", "Ooops: Something else went wrong")
            }
        }

    }
}