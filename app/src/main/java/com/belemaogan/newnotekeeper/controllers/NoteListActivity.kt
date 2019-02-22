package com.belemaogan.newnotekeeper.controllers

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import com.belemaogan.newnotekeeper.DataManager
import com.belemaogan.newnotekeeper.NOTE_POSITION
import com.belemaogan.newnotekeeper.models.NoteInfo
import com.belemaogan.newnotekeeper.views.NoteListView

class NoteListActivity : AppCompatActivity(), NoteListView.Listener {

    private lateinit var mNoteListView: NoteListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mNoteListView = NoteListView(LayoutInflater.from(this), null)

        setSupportActionBar(mNoteListView.mToolbar)

        val noteListAdapter = ArrayAdapter(this,
                android.R.layout.simple_list_item_1, DataManager.notes)

        mNoteListView.populateNoteListView(noteListAdapter)

        mNoteListView.registerListener(this)

        setContentView(mNoteListView.mRootView)
    }

    override fun onEditNoteButtonClicked() {
        startActivity(Intent(this, EditNoteActivity::class.java))
    }

    override fun onNoteListItemClicked(position: Int) {
        val activityIntent = Intent(this, EditNoteActivity::class.java)
        //the key NOTE_POSITION is from my Constant file, it helps to prevent me from making
        //an error in the spelling which will introduce bugs
        activityIntent.putExtra(NOTE_POSITION, position)
        startActivity(activityIntent)
    }

    override fun onResume() {
        super.onResume()
        (mNoteListView.mNoteListView.adapter as ArrayAdapter<NoteInfo>).notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        mNoteListView.unregisterListener(this)
    }
}
