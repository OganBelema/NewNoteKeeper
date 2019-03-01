package com.belemaogan.newnotekeeper.views.common

import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import com.belemaogan.newnotekeeper.models.CourseInfo

/**
 * Created by Belema Ogan on 3/1/2019.
 */
interface IEditNoteView {
    val mRootView: View
    val mToolbar: Toolbar
    val mCourseSpinner: Spinner
    val mNoteTitleEditText: EditText
    val mNoteTextEditText: EditText
    fun populateCourseSpinner(adapterCourses: ArrayAdapter<CourseInfo>)
    fun populateView(coursePosition: Int, title: String?, text: String?)
    fun showMessageWithSnackbar(message: String)
}