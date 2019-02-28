package com.belemaogan.newnotekeeper

import android.support.test.espresso.Espresso.*
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.belemaogan.newnotekeeper.models.CourseInfo
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.support.test.espresso.action.ViewActions.closeSoftKeyboard
import com.belemaogan.newnotekeeper.controllers.ItemsActivity
import com.belemaogan.newnotekeeper.controllers.NoteListActivity

/**
 * Created by Belema Ogan on 2/26/2019.
 */
@RunWith(AndroidJUnit4::class)
class CreateNewNoteTest {

    //code that starts up the activity for the test and closes it after test
    @Rule
    @JvmField
    val itemsActivity = ActivityTestRule(ItemsActivity::class.java)


    //this test performs the process of adding a new note using the application and confirms that the
    //note was created with the information selected and provided by the user
    @Test
    fun createNewNote(){
        val course = DataManager.courses["android_async"]
        val noteTitle = "Test note title"
        val noteText = "This is the body of our test note"

        //code that clicks the floating action button
        onView(withId(R.id.fab)).perform(click())

        //code that selects the course from the spinner
        //the onView before the on data is peculiar to spinners
        onView(withId(R.id.spinnerCourses)).perform(click())
        onData(allOf(instanceOf(CourseInfo::class.java), equalTo(course))).perform(click())

        //code that types the note title into the note title's edit text
        onView(withId(R.id.textNoteTitle)).perform(typeText(noteTitle))
        //code that types the note text into the note text's edit text and closes the soft keyboard
        onView(withId(R.id.textNoteText)).perform(typeText(noteText), closeSoftKeyboard())

        //code that returns to the previous activity resulting in the creation of the note
        pressBack()

        //test to confirm that the new note was created and it has the properties that were passed
        val newNote = DataManager.notes.last()
        assertEquals(course, newNote.course)
        assertEquals(noteTitle, newNote.title)
        assertEquals(noteText, newNote.text)
    }

}