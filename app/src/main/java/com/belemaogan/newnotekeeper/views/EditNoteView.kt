package com.belemaogan.newnotekeeper.views

import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import com.belemaogan.newnotekeeper.R
import com.belemaogan.newnotekeeper.models.CourseInfo
import com.belemaogan.newnotekeeper.views.common.IEditNoteView


/**
 * Created by Belema Ogan on 2/21/2019.
 */
class EditNoteView(inflater: LayoutInflater, parent: ViewGroup?) : IEditNoteView {

    override val mRootView: View = inflater.inflate(R.layout.activity_edit_note, parent, false)

    override val mToolbar: Toolbar
    override val mCourseSpinner: Spinner
    override val mNoteTitleEditText: EditText
    override val mNoteTextEditText: EditText

    init {
        mToolbar = findViewById(R.id.toolbar)
        mCourseSpinner = findViewById(R.id.spinnerCourses)
        mNoteTitleEditText = findViewById(R.id.textNoteTitle)
        mNoteTextEditText = findViewById(R.id.textNoteText)
    }

    private fun <T: View> findViewById(id: Int): T {
        return mRootView.findViewById(id)
    }

    override fun populateCourseSpinner(adapterCourses: ArrayAdapter<CourseInfo>) {
        mCourseSpinner.adapter = adapterCourses
    }

    override fun populateView(coursePosition: Int, title: String?, text: String?) {
        mCourseSpinner.setSelection(coursePosition)
        mNoteTitleEditText.setText(title?:"No Title")
        mNoteTextEditText.setText(text?:"No Text")
    }

    override fun showMessageWithSnackbar(message: String){
        Snackbar.make(mRootView, message, Snackbar.LENGTH_LONG).show()
    }

}