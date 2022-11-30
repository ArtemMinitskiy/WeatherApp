package com.example.weatherapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.weatherapp.databinding.FragmentDetailBinding
import com.example.weatherapp.model.ModelWeather
import com.example.weatherapp.utils.Utils

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        arguments?.let {
            val modelWeather = arguments?.get("modelWeather") as ModelWeather
            val detailCode = arguments?.get("detailCode") as Int
            binding.apply {
                cityDetailText.text = modelWeather.city!!.city
                coordinateDetailText.text = modelWeather.city.listCoordinates!!.latitude + "; " + modelWeather.city.listCoordinates.longitude
                animationView.setAnimation(Utils.setAdapterAnimation(modelWeather.listWeather!![detailCode].weather!![0].icon!!))
                temperatureDetailText.text = Utils.formatTemp(modelWeather.listWeather[detailCode].main!!.temp.toString())
                feelsDetailText.text = "Feels like ${Utils.formatTemp(modelWeather.listWeather[detailCode].main!!.feels_like.toString())}"
                descriptionDetailText.text = modelWeather.listWeather[detailCode].weather!![0].description!!.capitalize()
                dateDetailText.text = modelWeather.listWeather[detailCode].date
                cloudsDetailText.text = "Clouds ${modelWeather.listWeather[detailCode].clouds!!.clouds}"
                windDetailText.text = "Wind speed ${modelWeather.listWeather[detailCode].wind!!.speed}"
                humidityDetailText.text = "Humidity ${modelWeather.listWeather[detailCode].main!!.humidity}"

            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}