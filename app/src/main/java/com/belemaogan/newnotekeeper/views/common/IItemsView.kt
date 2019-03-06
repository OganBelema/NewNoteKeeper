package com.belemaogan.newnotekeeper.views.common

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import com.belemaogan.newnotekeeper.models.CourseInfo
import com.belemaogan.newnotekeeper.models.NoteInfo

/**
 * Created by Belema Ogan on 3/1/2019.
 */
interface IItemsView {

    interface Listener {
        fun onEditNoteButtonClicked()
        fun onDrawerItemClicked(id: Int)
        fun onNoteItemClicked(note: NoteInfo)
    }

    val mRootView: View
    val mRecyclerView: RecyclerView
    val mToolbar: Toolbar
    fun registerListener(listener: Listener)
    fun unregisterListener(listener: Listener)
    fun displayNotes(notes: List<NoteInfo>)
    fun displayCourses(courses: List<CourseInfo>)
    fun showSnackbarWithMessage(message: String)
    fun setupToggle(activity: Activity)
    fun isDrawerOpen(): Boolean
    fun closeDrawerView()
}