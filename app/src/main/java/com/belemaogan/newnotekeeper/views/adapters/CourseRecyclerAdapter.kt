package com.belemaogan.newnotekeeper.views.adapters

import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.belemaogan.newnotekeeper.R
import com.belemaogan.newnotekeeper.models.CourseInfo

/**
 * Created by Belema Ogan on 2/28/2019.
 */
class CourseRecyclerAdapter(context: Context):
        RecyclerView.Adapter<CourseRecyclerAdapter.CourseItemViewHolder>() {

    private var mCourses: List<CourseInfo> = ArrayList(1)

    private val mLayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseItemViewHolder {
        val itemView = mLayoutInflater.inflate(R.layout.item_course_list, parent, false)
        return CourseItemViewHolder(itemView)
    }

    override fun getItemCount() = mCourses.size

    override fun onBindViewHolder(holder: CourseItemViewHolder, position: Int) {
        val course = mCourses[position]
        holder.courseTextView?.text = course.title

        holder.itemView.setOnClickListener {
            Snackbar.make(it, course.title, Snackbar.LENGTH_LONG).show()
        }
    }

    fun addListOfCourses(courses: List<CourseInfo>){
        mCourses = courses
        notifyDataSetChanged()
    }


    class CourseItemViewHolder(itemView: View?):RecyclerView.ViewHolder(itemView){
        val courseTextView = itemView?.findViewById<TextView?>(R.id.course_title_textView)
    }
}