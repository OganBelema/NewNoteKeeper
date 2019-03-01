package com.belemaogan.newnotekeeper.controllers

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import com.belemaogan.newnotekeeper.DataManager
import com.belemaogan.newnotekeeper.ItemsActivityViewModel
import com.belemaogan.newnotekeeper.R
import com.belemaogan.newnotekeeper.models.NoteInfo
import com.belemaogan.newnotekeeper.views.ItemsView

class ItemsActivity : AppCompatActivity(), ItemsView.Listener {

    //lazy is used with val properties that you want their instantiation to be delayed till
    //when they are first used
    private val mItemView by lazy {
        ItemsView(LayoutInflater.from(this), null)
    }

    private val mItemsActivityViewModel by lazy {
        ViewModelProviders.of(this)[ItemsActivityViewModel::class.java]
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(mItemView.mToolbar)

        mItemView.setupToggle(this)

        //this is to restore the state when the activity is destroyed in a case of system-initiated
        // shutdown
        if (mItemsActivityViewModel.isNewlyCreated && savedInstanceState != null){
            mItemsActivityViewModel.restoreState(savedInstanceState)
        }

        //this way the view model's isNewlyCreated property is only true when it is created
        mItemsActivityViewModel.isNewlyCreated = false

       handleDisplaySelection(mItemsActivityViewModel.navigationDrawerDisplaySelection)

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
            R.id.nav_notes,
            R.id.nav_courses,
            R.id.nav_recently_viewed_notes -> {
                handleDisplaySelection(id)
                mItemsActivityViewModel.navigationDrawerDisplaySelection = id
            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

    }


    private fun handleDisplaySelection(itemId: Int){
        when(itemId){
            R.id.nav_notes -> {
                mItemView.displayNotes(DataManager.notes)
            }
            R.id.nav_courses -> {
                mItemView.displayCourses(DataManager.courses.values.toList())
            }
            R.id.nav_recently_viewed_notes -> {
                mItemView.displayNotes(mItemsActivityViewModel.mRecentlyViewedNotes)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mItemView.mRecyclerView.adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        mItemView.unregisterListener(this)
    }

    override fun onNoteItemClicked(note: NoteInfo) {
        mItemsActivityViewModel.addToRecentlyViewedNotes(note)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        //this saves the selected navigation drawer option  before the activity is destroyed
        // so it can be restored when the onCreate method is called
        if (outState != null){
            mItemsActivityViewModel.saveState(outState)
        }
    }

}
