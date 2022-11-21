package com.example.weatherapp.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.model.ModelWeather
import com.example.weatherapp.R
import com.example.weatherapp.databinding.WeatherItemBinding
import com.example.weatherapp.model.listweather.ListWeather
import com.example.weatherapp.utils.Utils

class WeatherAdapter(private val modelWeather: ModelWeather) :
    RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {
    private var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view: View = LayoutInflater.from(context).inflate(R.layout.weather_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindData(modelWeather.listWeather!![position])

    override fun getItemCount(): Int = modelWeather.listWeather!!.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: WeatherItemBinding = WeatherItemBinding.bind(itemView)

        @SuppressLint("SetTextI18n")
        fun bindData(weather: ListWeather) {
            binding.apply {
                tempText.text = Utils.formatTemp(weather.main!!.temp.toString())
                feelslikeText.text = "Feels like: ${weather.main!!.feels_like}"
                descriptionText.text = weather.weather!![0].description!!.capitalize()
                dateText.text = weather.date
                animationView.setAnimation(Utils.setAdapterAnimation(modelWeather.listWeather!![position].weather!![0].icon!!))
                cardView.setBackgroundResource(R.drawable.morning_card_bg)
                cardView.setOnClickListener {
                    transition(modelWeather, position)
                }
            }
        }
    }

    private fun transition(modelWeather: ModelWeather, detailCode: Int) {
        val bundle = Bundle()
        bundle.putSerializable("modelWeather", modelWeather)
        bundle.putInt("detailCode", detailCode)
        try {
            Navigation.findNavController(context as Activity, R.id.container).navigate(R.id.action_ForecastFragment_to_DetailFragment, bundle)
        } catch (e: Exception) {
            Log.e("mLog", "Exception $e")
        }
    }
}