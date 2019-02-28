package com.belemaogan.newnotekeeper.views

import android.app.Activity
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.belemaogan.newnotekeeper.R
import com.belemaogan.newnotekeeper.models.NoteInfo

/**
 * Created by Belema Ogan on 2/28/2019.
 */
class ItemsView(layoutInflater: LayoutInflater, parent: ViewGroup?) {

    interface Listener {
        fun onEditNoteButtonClicked()
        fun onDrawerItemClicked(id: Int)
    }

    fun registerListener(listener: Listener){
        mListeners.add(listener)
    }

    fun unregisterListener(listener: Listener){
        mListeners.remove(listener)
    }

    val mRootView: View = layoutInflater.inflate(R.layout.activity_items, parent, false)

    private val mDrawerLayout: DrawerLayout
    private val mNavigationView: NavigationView
    private val mContext = mRootView.context

    val mToolbar: Toolbar
    private val mEditNoteButton: FloatingActionButton
    val mNoteRecyclerView: RecyclerView
    private val mNoteRecyclerAdapter: NoteRecyclerAdapter
    private val mListeners: MutableList<Listener> = ArrayList()


    init {
        mDrawerLayout = findViewById(R.id.drawer_layout)
        mNavigationView = findViewById(R.id.nav_view)

        mToolbar = findViewById(R.id.toolbar)
        mEditNoteButton = findViewById(R.id.fab)
        mEditNoteButton.setOnClickListener { _ ->
            for (listener in mListeners){
                listener.onEditNoteButtonClicked()
            }
        }
        mNoteRecyclerView = findViewById(R.id.note_list_recyclerview)

        val linearLayoutManager = LinearLayoutManager(mContext)

        mNoteRecyclerView.layoutManager = linearLayoutManager

        mNoteRecyclerAdapter = NoteRecyclerAdapter(mContext)

        mNoteRecyclerView.adapter = mNoteRecyclerAdapter

        mNavigationView.setNavigationItemSelectedListener {
            mDrawerLayout.closeDrawers()
            for (listener in mListeners){
                listener.onDrawerItemClicked(it.itemId)
            }
             true
        }

    }

    private fun <T: View> findViewById(id: Int): T = mRootView.findViewById(id)

    fun populateNoteRecyclerView(notes: List<NoteInfo>){
        mNoteRecyclerAdapter.addListOfNotes(notes)
    }

    fun setupToggle(activity: Activity){
        val toggle = ActionBarDrawerToggle(
                activity, mDrawerLayout, mToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        mDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    fun isDrawerOpen(): Boolean{
        return mDrawerLayout.isDrawerOpen(GravityCompat.START)
    }

    fun closeDrawerView(){
        mDrawerLayout.closeDrawer(GravityCompat.START)
    }

}