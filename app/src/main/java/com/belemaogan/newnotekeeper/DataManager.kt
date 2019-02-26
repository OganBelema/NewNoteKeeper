package com.belemaogan.newnotekeeper

import com.belemaogan.newnotekeeper.models.CourseInfo
import com.belemaogan.newnotekeeper.models.NoteInfo

/**
 * Created by Belema Ogan on 2/21/2019.
 */
object DataManager {
    val courses = HashMap<String, CourseInfo>()
    val notes = ArrayList<NoteInfo>()

    init {
        initialiseCourses()
        initialiseNotes()
    }

    private fun initialiseCourses() {
        var course = CourseInfo("android_intents", "Android Programming with Intents")
        courses[course.courseId] = course

        course = CourseInfo(courseId = "android_async", title = "Android Async Programming and Services")
        courses[course.courseId] = course

        course = CourseInfo(title = "Java Fundamentals: The Java Language", courseId = "java_lang")
        courses[course.courseId] = course

        course = CourseInfo("java_core", "Java Fundamentals: The Core Platform")
        courses[course.courseId] = course
    }

    fun initialiseNotes(){
        var course = courses["android_intents"]!!
        var note = NoteInfo(course, "Dynamic intent resolution",
                "Wow, intents allow components to be resolved at runtime")
        notes.add(note)

        note = NoteInfo(course, "Delegating intents",
                "PendingIntents are powerful; they delegate much more than just a component invocation")
        notes.add(note)

        course = courses["android_async"]!!
        note = NoteInfo(course, "Service default threads",
                "Did you know that by default an Android Service will tie up the UI thread?")
        notes.add(note)

        note = NoteInfo(course, "Long running operations",
                "Foreground Services can be tied to a notification icon")
        notes.add(note)

        course = courses["java_lang"]!!
        note = NoteInfo(course, "Parameters",
                "Leverage variable-length parameter lists")
        notes.add(note)

        note = NoteInfo(course, "Anonymous classes",
                "Anonymous classes simplify implementing one-use types")
        notes.add(note)

        course = courses["java_core"]!!
        note = NoteInfo(course, "Compiler options",
                "The -jar option isn't compatible with with the -cp option")
        notes.add(note)

        note = NoteInfo(course, "Serialization",
                "Remember to include SerialVersionUID to assure version compatibility")
        notes.add(note)
    }

    fun createNewNote(course: CourseInfo, noteTitle: String, noteText: String): Int {
        val newNote = NoteInfo(course = course, title = noteTitle, text = noteText)
        DataManager.notes.add(newNote)
        return DataManager.notes.indexOf(newNote)
    }

    fun findNote(course: CourseInfo, noteTitle: String?, noteText: String?): NoteInfo? {

        for(note in notes)
            if (note.course == course && note.title == noteTitle && note.text == noteText)
                return note

        return null
    }

}