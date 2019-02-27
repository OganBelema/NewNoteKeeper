package com.belemaogan.newnotekeeper

import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.belemaogan.newnotekeeper.controllers.NoteListActivity
import com.belemaogan.newnotekeeper.models.NoteInfo
import org.hamcrest.CoreMatchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Belema Ogan on 2/27/2019.
 */
@RunWith(AndroidJUnit4::class)
class NextThroughNotesTest {

    //code that starts up the activity to test and closes it after test
    @Rule
    @JvmField
    val noteListActivity = ActivityTestRule(NoteListActivity::class.java)


    //the test confirms the app's next through notes functionality works fine
    @Test
    fun nextThroughNotes(){
        //code that performs the click action on the first item in the listview
        //notice we didn't have to call the onClick method first because it is not a spinner
        onData(allOf(instanceOf(NoteInfo::class.java), equalTo(DataManager.notes[0]))).perform(click())

        //code that loops through the note list and clicks on the next action menu and checks the
        //the behaviour is what is expected
        for (index in 0..DataManager.notes.lastIndex){
            val note = DataManager.notes[index]

            //code that checks that the course being displayed corresponds to the one in the current note
            onView(withId(R.id.spinnerCourses)).check(matches(withSpinnerText(note.course?.title)))
            //code that checks that the title being displayed corresponds to the one in the current note
            onView(withId(R.id.textNoteTitle)).check(matches(withText(note.title)))
            //code that checks that the text being displayed corresponds to the one in the current note
            onView(withId(R.id.textNoteText)).check(matches(withText(note.text)))

            //code that checks that the index is within the note list and clicks the next menu action
            if (index != DataManager.notes.lastIndex){
                onView(allOf(withId(R.id.action_next), isEnabled())).perform(click())
            }

        }

        //code that checks if the menu action next is still enabled
        onView(withId(R.id.action_next)).check(matches(isEnabled()))
    }
}