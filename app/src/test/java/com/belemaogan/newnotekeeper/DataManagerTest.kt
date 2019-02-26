package com.belemaogan.newnotekeeper

import com.belemaogan.newnotekeeper.models.NoteInfo
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Created by Belema Ogan on 2/26/2019.
 */

class DataManagerTest {

    @Before
    fun setup(){
        DataManager.notes.clear()
        DataManager.initialiseNotes()
    }

    /**Create new note test */
    //requirements:
    //the method should create a new noteInfo object with the parameters passed into it
    //the new note's course should be the course that was passed into the method
    //the new note title should be the title that was passed into the method
    //the new note text should be the text that was passed into the method
    //the method should add the note info the noteInfo list
    //the method should return the position of the note

    @Test
    fun createNewNote_shouldReturnTheCorrectPositionOfNewNote() {
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

    /** Create find note method*/
    //Requirements:
    //the method should receive a course, noteTitle and noteText and return the correct note from
    //the DataManager note list or return null if the note is not in the list
    //the course of the note returned should be the course that was passed to the method
    //the title of the note returned should be the title that was passed to the method
    //the text of the note returned should be the text that was passed to the method


    //my test method naming convention is
    //(name of the method to test)_(the state to test)_(the result expected)
    @Test
    fun findNote_noteExist_shouldReturnTheNoteWhosePropertiesMatchWhatWasPassed(){
        val testNote = DataManager.notes[0]
        val course = testNote.course!!
        val noteTitle = testNote.title
        val noteText = testNote.text

        val foundNote: NoteInfo? = DataManager.findNote(course, noteTitle, noteText)

        assertNotNull(foundNote)
        assertEquals(course, foundNote?.course)
        assertEquals(noteTitle, foundNote?.title)
        assertEquals(noteText, foundNote?.text)
    }

    @Test
    fun findNote_noteDoesNotExist_shouldReturnNull(){
        val course = DataManager.courses["java_lang"]!!
        val noteTitle = "Does not exist"
        val noteText = "Does not exist"

        val foundNote: NoteInfo? = DataManager.findNote(course, noteTitle, noteText)

        assertNull(foundNote)
    }

}