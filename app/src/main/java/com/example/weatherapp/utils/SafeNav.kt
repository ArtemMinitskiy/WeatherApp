package com.example.weatherapp.utils

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController

fun NavController.safeNavigate(@IdRes currentDestinationId: Int, @IdRes id: Int, args: Bundle? = null) {
    if (currentDestinationId == currentDestination?.id) {
        navigate(id, args)
    }
}