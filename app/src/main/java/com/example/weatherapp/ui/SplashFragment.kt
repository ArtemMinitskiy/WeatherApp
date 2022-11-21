package com.example.weatherapp.ui

import android.Manifest
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentSplashBinding
import com.example.weatherapp.utils.GpsUtils
import com.example.weatherapp.utils.GpsUtils.OnGpsListener
import com.example.weatherapp.utils.LocationProvider
import com.example.weatherapp.utils.safeNavigate

class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    private var forecastViewModel: ForecastViewModel? = null

    private var isGPS = false
    private var isGranted = false

    private var permissionsStr = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        val view = binding.root

        forecastViewModel = ViewModelProvider(requireActivity())[ForecastViewModel::class.java]

        requestPermission.launch(permissionsStr)

        return view
    }

    private fun initLocation() {
        LocationProvider.initLocationRequest()
        LocationProvider.initFusedLocation(requireActivity())
//        setLocationRequest()
//        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        GpsUtils(requireActivity()).turnGPSOn(object : OnGpsListener {
            override fun gpsStatus(isGPSEnable: Boolean) {
                isGPS = isGPSEnable
            }
        })
        LocationProvider.initLocationCallback()
        LocationProvider.setLocation(requireActivity()) { transition() }
    }

    private val requestPermission = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
        isGranted = it.values.first()
        if (it.values.first()) {
            initLocation()
            Toast.makeText(requireActivity(), "Permission granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireActivity(), "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object :
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {}
        })

    }

    private fun createTimer(milliseconds: Long) {
        val mCountDownTimer = object : CountDownTimer(milliseconds, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                if (isGranted) findNavController().safeNavigate(R.id.SplashFragment, R.id.action_SplashFragment_to_ForecastFragment)
            }
        }
        mCountDownTimer.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun transition() {
        forecastViewModel!!.getWeatherDetail(LocationProvider.wayLatitude, LocationProvider.wayLongitude)
        if (isGranted) createTimer(2000)
    }

    /*
    private var mFusedLocationClient: FusedLocationProviderClient? = null
    private var wayLatitude = 0.0
    private var wayLongitude = 0.0
    private var locationRequest: LocationRequest? = null
    private var locationCallback: LocationCallback? = null
    private val isContinue = false
    private fun setLocationRequest() {
        locationRequest = LocationRequest.create()
        locationRequest!!.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest!!.interval = (10 * 1000).toLong()
        locationRequest!!.fastestInterval = (5 * 1000).toLong()
    }

    private fun initLocationCallback() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                if (locationResult == null) {
                    return
                }
                for (location in locationResult.locations) {
                    if (location != null) {
                        wayLatitude = location.latitude
                        wayLongitude = location.longitude
                        if (!isContinue && mFusedLocationClient != null) {
                            mFusedLocationClient!!.removeLocationUpdates(locationCallback!!)
                        }
                    }
                }
            }
        }
    }

    private fun setLocation() {
        if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (isContinue) {
                mFusedLocationClient!!.requestLocationUpdates(locationRequest!!, locationCallback!!, null)
            } else {
                mFusedLocationClient!!.lastLocation.addOnSuccessListener { location: Location? ->
                    if (location != null) {
                        wayLatitude = location.latitude
                        wayLongitude = location.longitude
                        forecastViewModel!!.getWeatherDetail(wayLatitude, wayLongitude)
                        if (isGranted) createTimer(2000)
                    } else {
                        mFusedLocationClient!!.requestLocationUpdates(locationRequest!!, locationCallback!!, null)
                    }
                }
            }
        }
    }*/
}