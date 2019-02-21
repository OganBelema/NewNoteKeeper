package com.belemaogan.newnotekeeper.models

/**
 * Created by Belema Ogan on 2/21/2019.
 */
class CourseInfo(val courseId: String, val title: String)

class NoteInfo(var course: CourseInfo, var title: String, var text: String)