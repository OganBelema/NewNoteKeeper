package com.belemaogan.newnotekeeper.views.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.belemaogan.newnotekeeper.NOTE_POSITION
import com.belemaogan.newnotekeeper.R
import com.belemaogan.newnotekeeper.controllers.EditNoteActivity
import com.belemaogan.newnotekeeper.models.NoteInfo

/**
 * Created by Belema Ogan on 2/27/2019.
 */
class NoteRecyclerAdapter(private val context: Context) :
        RecyclerView.Adapter<NoteRecyclerAdapter.NoteItemViewHolder>() {

    private val mLayoutInflater = LayoutInflater.from(context)

    private var mNotes: List<NoteInfo> = ArrayList(1)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemViewHolder {
        val itemView = mLayoutInflater.inflate(R.layout.item_note_list, parent, false)
        return NoteItemViewHolder(itemView)
    }

    override fun getItemCount() = mNotes.size

    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int) {
        val note = mNotes[position]
        holder.courseTitleTextView?.text = note.course?.title
        holder.noteTitleTextView?.text = note.title

        holder.itemView.setOnClickListener {
            //the key NOTE_POSITION is from my Constant file, it helps to prevent me from making
            //an error in the spelling which will introduce bugs
            val intent = Intent(context, EditNoteActivity::class.java)
            intent.putExtra(NOTE_POSITION, position)
            context.startActivity(intent)
        }
    }

    fun addListOfNotes(notes: List<NoteInfo>) {
        mNotes = notes
        notifyDataSetChanged()
    }

    class NoteItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val courseTitleTextView = itemView?.findViewById<TextView?>(R.id.text_course)
        val noteTitleTextView = itemView?.findViewById<TextView?>(R.id.text_title)
    }
}