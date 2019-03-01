package com.belemaogan.newnotekeeper

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import android.util.Log
import com.belemaogan.newnotekeeper.models.NoteInfo
import com.jwhh.notekeeper.PseudoLocationManager
import com.jwhh.notekeeper.PseudoMessagingConnection
import com.jwhh.notekeeper.PseudoMessagingManager

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

    val messagingManager = PseudoMessagingManager(context)
    var messagingConnection: PseudoMessagingConnection? = null

    fun sendMessage(note: NoteInfo){
        val getTogetherMessage = "$currentLatitude|$currentLongitude|${note.course?.title}"
        messagingConnection?.send(getTogetherMessage)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun startHandler(){
        locationManager.start()
        messagingManager.connect { connection ->
            Log.d(tag, "Connection callback - Lifecycle state: ${lifecycle.currentState}")

            //this code ensures that the lifecycle we are observing is in an atleast started state
            //before we assign the value we have received
            //we use state because in the case of async calls it is possible that we get delayed response and
            //so resources can leak because by the time our stophandler is called the messagingConnection is null
            //and so the disconnect method is not called
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)){
                messagingConnection = connection
            } else {
                //if the lifecycle owner is not in an atleast started started state we disconnect the
                //connection
                connection.disconnect()
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stopHandler(){
        locationManager.stop()
        messagingConnection?.disconnect()
    }
}