package com.belemaogan.newnotekeeper.controllers

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import com.belemaogan.newnotekeeper.DataManager
import com.belemaogan.newnotekeeper.R
import com.belemaogan.newnotekeeper.views.ItemsView

class ItemsActivity : AppCompatActivity(), ItemsView.Listener {

    private lateinit var mItemView: ItemsView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mItemView = ItemsView(LayoutInflater.from(this), null)

        setSupportActionBar(mItemView.mToolbar)

        mItemView.setupToggle(this)

        mItemView.populateNoteRecyclerView(DataManager.notes)

        mItemView.registerListener(this)

        setContentView(mItemView.mRootView)
    }

    override fun onBackPressed() {
        if (mItemView.isDrawerOpen()) {
            mItemView.closeDrawerView()
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings ->  true
            else ->  super.onOptionsItemSelected(item)
        }
    }

    override fun onEditNoteButtonClicked() {
        startActivity(Intent(this, EditNoteActivity::class.java))
    }

    override fun onDrawerItemClicked(id: Int) {
        when (id) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

    }

    override fun onResume() {
        super.onResume()
        mItemView.mNoteRecyclerView.adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        mItemView.unregisterListener(this)
    }
}
