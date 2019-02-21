package com.belemaogan.newnotekeeper.models

/**
 * Created by Belema Ogan on 2/21/2019.
 */
data class CourseInfo(val courseId: String, val title: String) {
    override fun toString(): String {
        return title
    }
}

data class NoteInfo(var course: CourseInfo, var title: String, var text: String)