package com.belemaogan.newnotekeeper

import org.junit.Assert.*
import org.junit.Test

/**
 * Created by Belema Ogan on 2/26/2019.
 */

class DataManagerTest {

    /**Create new note test */
    //requirements:
    //the method should create a new noteInfo object with the parameters passed into it
    //the new note's course should be the course that was passed into the method
    //the new note title should be the title that was passed into the method
    //the new note text should be the text that was passed into the method
    //the method should add the note info the noteInfo list
    //the method should return the position of the note

    @Test
    fun createNewNote_shouldReturnTheCorrectNotePosition() {
        val course = DataManager.courses["java_lang"]!!
        val noteTitle = "Test Note Title"
        val noteText = "Test Note Text"

        val expectedNewNoteIndex = DataManager.notes.lastIndex + 1

        val newNoteIndex: Int = DataManager.createNewNote(course, noteTitle, noteText)

        val newNote = DataManager.notes[newNoteIndex]

        assertEquals(expectedNewNoteIndex, newNoteIndex)
        assertEquals(course, newNote.course)
        assertEquals(noteTitle, newNote.title)
        assertEquals(noteText, newNote.text)
    }
}