package com.belemaogan.newnotekeeper.views

import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.belemaogan.newnotekeeper.R

/**
 * Created by Belema Ogan on 2/21/2019.
 */
class MainView(inflater: LayoutInflater, parent: ViewGroup?){

    interface Listener {

    }

    val mRootView: View = inflater.inflate(R.layout.activity_main, parent, false)

    val mToolbar: Toolbar
    private val mFloatingActionButton: FloatingActionButton
    private val mTextDisplayedTextView: TextView
    private var mListeners: MutableList<Listener> = ArrayList()

    fun registerListener(listener: Listener){
        mListeners.add(listener)
    }

    fun unregisterListener(listener: Listener){
        mListeners.remove(listener)
    }

    init {
        mToolbar = findViewById(R.id.toolbar)
        mFloatingActionButton = findViewById(R.id.fab)
        mTextDisplayedTextView = findViewById(R.id.textDisplayedValue)

        mFloatingActionButton.setOnClickListener { view ->
            val originalValue = mTextDisplayedTextView.text.toString().toInt()
            val newValue = originalValue * 2
            mTextDisplayedTextView.text = newValue.toString()
            Snackbar.make(view, "Value $originalValue changed to $newValue", Snackbar.LENGTH_LONG).show()
        }


    }

    private fun <T: View> findViewById(id: Int): T {
        return mRootView.findViewById(id)
    }

}