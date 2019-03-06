package com.belemaogan.newnotekeeper.views

import android.app.Activity
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.belemaogan.newnotekeeper.R
import com.belemaogan.newnotekeeper.models.CourseInfo
import com.belemaogan.newnotekeeper.models.NoteInfo
import com.belemaogan.newnotekeeper.views.adapters.CourseRecyclerAdapter
import com.belemaogan.newnotekeeper.views.adapters.NoteRecyclerAdapter
import com.belemaogan.newnotekeeper.views.common.IItemsView


/**
 * Created by Belema Ogan on 2/28/2019.
 */
class ItemsView(layoutInflater: LayoutInflater, parent: ViewGroup?) : NoteRecyclerAdapter.Listener, IItemsView {

    override val mRootView: View = layoutInflater.inflate(R.layout.activity_items, parent, false)

    private val mDrawerLayout: DrawerLayout
    private val mNavigationView: NavigationView
    private val mContext = mRootView.context

    override val mToolbar: Toolbar
    private val mEditNoteButton: FloatingActionButton
    override val mRecyclerView: RecyclerView
    private val mNoteLayoutManager = LinearLayoutManager(mContext)
    private val mNoteRecyclerAdapter = NoteRecyclerAdapter(mContext, this)
    private val mCourseLayoutManager = GridLayoutManager(mContext, mContext.resources.getInteger(R.integer.course_grid_span))
    private val mCourseRecyclerAdapter = CourseRecyclerAdapter(mContext)
    private val mListeners: MutableList<IItemsView.Listener> = ArrayList()

    override fun registerListener(listener: IItemsView.Listener) {
        mListeners.add(listener)
    }

    override fun unregisterListener(listener: IItemsView.Listener){
        mListeners.remove(listener)
    }


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
        mRecyclerView = findViewById(R.id.note_list_recyclerview)

        mNavigationView.setNavigationItemSelectedListener {
            mDrawerLayout.closeDrawers()
            for (listener in mListeners){
                listener.onDrawerItemClicked(it.itemId)
            }
             true
        }

    }

    private fun <T: View> findViewById(id: Int): T = mRootView.findViewById(id)

    override fun displayNotes(notes: List<NoteInfo>){
        mRecyclerView.layoutManager = mNoteLayoutManager
        mRecyclerView.adapter = mNoteRecyclerAdapter
        mNoteRecyclerAdapter.addListOfNotes(notes)
        mNavigationView.menu.findItem(R.id.nav_notes).isChecked = true
    }

    override fun displayCourses(courses: List<CourseInfo>){
        mRecyclerView.layoutManager = mCourseLayoutManager
        mRecyclerView.adapter = mCourseRecyclerAdapter
        mCourseRecyclerAdapter.addListOfCourses(courses)
        mNavigationView.menu.findItem(R.id.nav_courses).isChecked = true
    }

    override fun setupToggle(activity: Activity){
        val toggle = ActionBarDrawerToggle(
                activity, mDrawerLayout, mToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        mDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun showSnackbarWithMessage(message: String) {
        Snackbar.make(mRootView, message, Snackbar.LENGTH_LONG)
                .show()
    }

    override fun isDrawerOpen(): Boolean{
        return mDrawerLayout.isDrawerOpen(GravityCompat.START)
    }

    override fun closeDrawerView(){
        mDrawerLayout.closeDrawer(GravityCompat.START)
    }

    override fun onNoteSelected(note: NoteInfo) {
        for (listener in mListeners){
            listener.onNoteItemClicked(note)
        }
    }

}