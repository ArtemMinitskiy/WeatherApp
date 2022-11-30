package com.example.weatherapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.adapter.WeatherAdapter
import com.example.weatherapp.databinding.FragmentForecastBinding
import com.example.weatherapp.model.ModelWeather
import com.example.weatherapp.utils.LocationProvider

class ForecastFragment : Fragment() {
    private var _binding: FragmentForecastBinding? = null
    private val binding get() = _binding!!
    private var forecastViewModel: ForecastViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentForecastBinding.inflate(inflater, container, false)
        forecastViewModel = ViewModelProvider(requireActivity())[ForecastViewModel::class.java]

        binding.swipeRefreshLayout.setOnRefreshListener {
            LocationProvider.setLocation(requireActivity()) { updateLocation() }
            binding.swipeRefreshLayout.isRefreshing = false
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object :
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {}
        })

        updateUi()
    }

    private fun initRecyclerView(modelWeather: ModelWeather) {
        binding.recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireActivity().applicationContext, LinearLayoutManager.VERTICAL, false)
            adapter = WeatherAdapter(modelWeather)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateLocation() {
        forecastViewModel!!.getLocationWeather(LocationProvider.wayLatitude, LocationProvider.wayLongitude)
        updateUi()
    }

    private fun updateUi() {
        try {
            forecastViewModel!!.getWeatherList()!!.observe(viewLifecycleOwner) {
                initRecyclerView(it)
                binding.cityText.text = it.city!!.city
            }
        } catch (e: Exception) {
            Log.e("mLog", "Exception $e")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}