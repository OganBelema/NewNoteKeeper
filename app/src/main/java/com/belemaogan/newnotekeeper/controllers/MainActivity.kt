package com.belemaogan.newnotekeeper.controllers

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.belemaogan.newnotekeeper.R
import com.belemaogan.newnotekeeper.models.CourseInfo
import com.belemaogan.newnotekeeper.models.DataManager
import com.belemaogan.newnotekeeper.views.MainView



class MainActivity : AppCompatActivity(), MainView.Listener {

    private lateinit var mMainView: MainView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mMainView = MainView(LayoutInflater.from(this), null)

        setSupportActionBar(mMainView.mToolbar)

        mMainView.registerListener(this)

        val dataManager = DataManager()
        val adapterCourses = ArrayAdapter<CourseInfo>(this,
                android.R.layout.simple_spinner_item, dataManager.courses.values.toList())
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        mMainView.setUpCourseSpinner(adapterCourses)

        setContentView(mMainView.mRootView)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mMainView.unregisterListener(this)
    }
}
