package com.belemaogan.newnotekeeper.controllers

import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.belemaogan.newnotekeeper.*
import com.belemaogan.newnotekeeper.models.CourseInfo
import com.belemaogan.newnotekeeper.models.NoteInfo
import com.belemaogan.newnotekeeper.views.EditNoteView
import com.belemaogan.newnotekeeper.views.common.IEditNoteView


class EditNoteActivity : AppCompatActivity(), IEditNoteView.Listener {


    private val mTag = this::class.simpleName

    private val mEditNoteView: IEditNoteView by lazy {
        EditNoteView(LayoutInflater.from(this), null)
    }

    private var mNotePosition = POSITION_NOT_SET

    private var mNoteColor = Color.TRANSPARENT

    private val mNoteGetTogetherHelper = NoteGetTogetherHelper(this, lifecycle)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(mEditNoteView.mToolbar)

        val adapterCourses = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, DataManager.courses.values.toList())
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        mEditNoteView.populateCourseSpinner(adapterCourses)

        mNotePosition = savedInstanceState?.getInt(NOTE_POSITION, POSITION_NOT_SET) ?:
                intent.getIntExtra(NOTE_POSITION, POSITION_NOT_SET)

        if (mNotePosition != POSITION_NOT_SET) {
            displayNote()
        } else {
            createNewNote()
        }

        Log.d(mTag, "onCreate")

        mEditNoteView.registerListener(this)

        setContentView(mEditNoteView.mRootView)
    }

    override fun onColorSelected(color: Int) {
        mNoteColor = color
    }

    private fun createNewNote() {
        DataManager.notes.add(NoteInfo())
        mNotePosition = DataManager.notes.lastIndex
    }

    private fun displayNote() {
        if (mNotePosition > DataManager.notes.lastIndex){
            val message = "Note not found"
            mEditNoteView.showMessageWithSnackbar(message)
            Log.e(mTag, "Invalid note position $mNotePosition, max valid position is ${DataManager.notes.lastIndex}")
            return
        }

        Log.i(mTag, "displaying note for position: $mNotePosition")

        val note = DataManager.notes[mNotePosition]
        val coursePosition = DataManager.courses.values.indexOf(note.course)
        mNoteColor = note.color
        mEditNoteView.populateView(coursePosition, note.title, note.text, note.color)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (mNotePosition >= DataManager.notes.lastIndex){
            val menuItem = menu?.findItem(R.id.action_next)
            if (menuItem != null){
                menuItem.icon = ContextCompat.getDrawable(this, R.drawable.ic_block_white_24dp)
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
                if (mNotePosition < DataManager.notes.lastIndex) {
                    moveNext()
                } else {
                    val message = "No more notes"
                    mEditNoteView.showMessageWithSnackbar(message)
                }
                true
            }
            R.id.action_get_together -> {
                mNoteGetTogetherHelper.sendMessage(DataManager.loadNote(mNotePosition))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun moveNext() {
        mNotePosition++
        displayNote()
        invalidateOptionsMenu()
    }

    override fun onPause() {
        saveNote()
        Log.d(mTag, "onPause")
        super.onPause()
    }

    private fun saveNote() {
        val note = DataManager.notes[mNotePosition]
        note.title = mEditNoteView.mNoteTitleEditText.text.toString()
        note.text = mEditNoteView.mNoteTextEditText.text.toString()
        note.course = mEditNoteView.mCourseSpinner.selectedItem as CourseInfo
        note.color = mNoteColor
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(NOTE_POSITION, mNotePosition)
    }

    override fun onDestroy() {
        mEditNoteView.unregisterListener(this)
        super.onDestroy()
    }

}
