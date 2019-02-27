package com.belemaogan.newnotekeeper.controllers

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import com.belemaogan.newnotekeeper.DataManager
import com.belemaogan.newnotekeeper.views.NoteListView

class NoteListActivity : AppCompatActivity(), NoteListView.Listener {

    private lateinit var mNoteListView: NoteListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mNoteListView = NoteListView(LayoutInflater.from(this), null)

        setSupportActionBar(mNoteListView.mToolbar)

        mNoteListView.populateNoteRecyclerView(DataManager.notes)

        mNoteListView.registerListener(this)

        setContentView(mNoteListView.mRootView)
    }

    override fun onEditNoteButtonClicked() {
        startActivity(Intent(this, EditNoteActivity::class.java))
    }

    override fun onResume() {
        super.onResume()
        mNoteListView.mNoteRecyclerView.adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        mNoteListView.unregisterListener(this)
    }
}
