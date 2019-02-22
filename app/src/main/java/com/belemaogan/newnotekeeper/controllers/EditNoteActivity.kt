package com.belemaogan.newnotekeeper.controllers

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.belemaogan.newnotekeeper.R
import com.belemaogan.newnotekeeper.DataManager
import com.belemaogan.newnotekeeper.EXTRA_NOTE_POSITION
import com.belemaogan.newnotekeeper.POSITION_NOT_SET
import com.belemaogan.newnotekeeper.views.EditNoteView



class EditNoteActivity : AppCompatActivity() {

    private lateinit var mEditNoteView: EditNoteView

    private var mNotePosition = POSITION_NOT_SET

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mEditNoteView = EditNoteView(LayoutInflater.from(this), null)

        setSupportActionBar(mEditNoteView.mToolbar)

        val adapterCourses = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, DataManager.courses.values.toList())
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        mEditNoteView.populateCourseSpinner(adapterCourses)

        mNotePosition = intent.getIntExtra(EXTRA_NOTE_POSITION, POSITION_NOT_SET)

        if (mNotePosition != POSITION_NOT_SET) {
            displayNote()
        }

        setContentView(mEditNoteView.mRootView)
    }

    private fun displayNote() {
        val note = DataManager.notes[mNotePosition]
        val coursePosition = DataManager.courses.values.indexOf(note.course)
        mEditNoteView.populateView(coursePosition, note.title, note.text)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (mNotePosition >= DataManager.notes.lastIndex){
            val menuItem = menu?.findItem(R.id.action_next)
            if (menuItem != null){
                menuItem.icon = resources.getDrawable(R.drawable.ic_block_white_24dp)
                menuItem.isEnabled = false
            }

        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_edit_note, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_next -> {
                moveNext()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun moveNext() {
        mNotePosition++
        displayNote()
        invalidateOptionsMenu()
    }

}
