package com.belemaogan.newnotekeeper.views

import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.belemaogan.newnotekeeper.R
import com.belemaogan.newnotekeeper.models.NoteInfo
import com.belemaogan.newnotekeeper.views.adapters.NoteRecyclerAdapter

/**
 * Created by Belema Ogan on 2/21/2019.
 */
class NoteListView(inflater: LayoutInflater, parent: ViewGroup?) : NoteRecyclerAdapter.Listener {
    override fun onNoteSelected(note: NoteInfo) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    interface Listener {
        fun onEditNoteButtonClicked()
    }

    val mRootView: View = inflater.inflate(R.layout.activity_note_list, parent, false)

    private val mContext = mRootView.context

    val mToolbar: Toolbar
    private val mEditNoteButton: FloatingActionButton
    val mNoteRecyclerView: RecyclerView
    private val mNoteRecyclerAdapter: NoteRecyclerAdapter
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
        mNoteRecyclerView = findViewById(R.id.note_recycler_view)

        val linearLayoutManager = LinearLayoutManager(mContext)

        mNoteRecyclerView.layoutManager = linearLayoutManager

        mNoteRecyclerAdapter = NoteRecyclerAdapter(mContext, this)

        mNoteRecyclerView.adapter = mNoteRecyclerAdapter
    }

    private fun <T: View> findViewById(id: Int) : T {
        return mRootView.findViewById(id)
    }

    fun populateNoteRecyclerView(notes: List<NoteInfo>){
        mNoteRecyclerAdapter.addListOfNotes(notes)
    }
}