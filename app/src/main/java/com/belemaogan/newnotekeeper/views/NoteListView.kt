package com.belemaogan.newnotekeeper.views

import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.belemaogan.newnotekeeper.R
import com.belemaogan.newnotekeeper.models.NoteInfo

/**
 * Created by Belema Ogan on 2/21/2019.
 */
class NoteListView(inflater: LayoutInflater, parent: ViewGroup?) {

    interface Listener {
        fun onEditNoteButtonClicked()
        fun onNoteListItemClicked(position: Int)
    }

    val mRootView: View = inflater.inflate(R.layout.activity_note_list, parent, false)

    private val mContext = mRootView.context

    val mToolbar: Toolbar
    private val mEditNoteButton: FloatingActionButton
    val mNoteListView: RecyclerView
    private val mListeners = ArrayList<Listener>()

    fun registerListener(listener: Listener){
        mListeners.add(listener)
    }

    fun unregisterListener(listener: Listener){
        mListeners.remove(listener)
    }

    init {
        mToolbar = findViewById(R.id.toolbar)
        mEditNoteButton = findViewById(R.id.fab)
        mEditNoteButton.setOnClickListener { _ ->
            for (listener in mListeners){
                listener.onEditNoteButtonClicked()
            }
        }
        mNoteListView = findViewById(R.id.note_recycler_view)

        val linearLayoutManager = LinearLayoutManager(mContext)

        mNoteListView.layoutManager = linearLayoutManager
    }

    private fun <T: View> findViewById(id: Int) : T {
        return mRootView.findViewById(id)
    }

    fun populateNoteListView(adapter: ArrayAdapter<NoteInfo>){
        //mNoteListView.adapter = adapter
    }
}