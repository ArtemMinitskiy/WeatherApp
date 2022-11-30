package com.example.weatherapp.utils

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*

object LocationProvider {
    private var mFusedLocationClient: FusedLocationProviderClient? = null
    var wayLatitude = 0.0
    var wayLongitude = 0.0
    private var locationRequest: LocationRequest? = null
    private var locationCallback: LocationCallback? = null
    private val isContinue = false

    fun initFusedLocation(activity: Activity) {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
    }

    fun initLocationCallback() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult?.let { return }
                for (location in locationResult.locations) {
                    if (location != null) {
                        wayLatitude = location.latitude
                        wayLongitude = location.longitude
                        Log.i("mLog", "initLocationCallback wayLatitude $wayLatitude, wayLongitude $wayLongitude")
                        if (!isContinue && mFusedLocationClient != null) {
                            mFusedLocationClient!!.removeLocationUpdates(locationCallback!!)
                        }
                    }
                }
            }
        }
    }

    fun initLocationRequest() {
        locationRequest?.apply {
            LocationRequest.create()
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = (10 * 1000).toLong()
            fastestInterval = (5 * 1000).toLong()
        }
    }

    fun setLocation(activity: Activity, transition: () -> (Unit)) {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            try {
                if (isContinue) {
                    mFusedLocationClient!!.requestLocationUpdates(locationRequest!!, locationCallback!!, null)
                } else {
                    mFusedLocationClient!!.lastLocation.addOnSuccessListener { location: Location? ->
                        if (location != null) {
                            Log.i("mLog", "setLocation() wayLatitude $wayLatitude, wayLongitude $wayLongitude")
                            wayLatitude = location.latitude
                            wayLongitude = location.longitude
                            transition()
                        } else {
                            try {
                                mFusedLocationClient!!.requestLocationUpdates(locationRequest!!, locationCallback!!, null)
                            } catch (e: Exception) {
                                Log.e("mLog", "Exception $e")
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("mLog", "Exception $e")
            }
        }
    }

}