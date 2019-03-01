package com.belemaogan.newnotekeeper

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import android.util.Log
import com.jwhh.notekeeper.PseudoLocationManager

/**
 * Created by Belema Ogan on 3/1/2019.
 */

class NoteGetTogetherHelper(val context: Context, val lifecycle: Lifecycle): LifecycleObserver {

    init {
        lifecycle.addObserver(this)
    }

    private val tag = NoteGetTogetherHelper::class.java.simpleName
    var currentLatitude = 0.0
    var currentLongitude = 0.0

    private val locationManager = PseudoLocationManager(context) {latitude, longitude ->
        currentLatitude = latitude
        currentLongitude = longitude

        Log.d(tag, "Location Callback Lat:$currentLatitude Long:$currentLongitude")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun startHandler(){
        locationManager.start()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stopHandler(){
        locationManager.stop()
    }
}