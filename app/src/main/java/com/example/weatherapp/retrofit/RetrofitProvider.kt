//package com.example.weatherapp.retrofit
//
//import android.util.Log
//import com.example.weatherapp.Model.ModelWeather
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//import retrofit2.HttpException
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//object RetrofitProvider {
//
//    private const val BASE_URL = "http://api.openweathermap.org/"
//
//    private fun makeRetrofitService(): RetrofitService {
//        return Retrofit.Builder().baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build().create(RetrofitService::class.java)
//    }
//
//    fun getWeatherDetail(): ModelWeather? {
//        var data: ModelWeather? = null
//        val service = makeRetrofitService()
//        CoroutineScope(Dispatchers.IO).launch {
//            val response = service.getWeather("46.48719996790696", "30.720671684814914")
//
//            try {
//                withContext(Dispatchers.Main) {
//                    if (response.isSuccessful) {
//                        data = response.body()
//                    } else {
//                        Log.e("mLog", "Error network operation failed with ${response.code()}")
//                    }
//                }
//            } catch (e: HttpException) {
//                Log.e("mLog", "Exception ${e.message}")
//            } catch (e: Throwable) {
//                Log.e("mLog", "Ooops: Something else went wrong")
//            }
//        }
//        return data
//    }
//
//}