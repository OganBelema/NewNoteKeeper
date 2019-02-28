package com.belemaogan.newnotekeeper

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.DrawerActions
import android.support.test.espresso.contrib.NavigationViewActions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.belemaogan.newnotekeeper.controllers.ItemsActivity
import com.belemaogan.newnotekeeper.views.adapters.CourseRecyclerAdapter
import com.belemaogan.newnotekeeper.views.adapters.NoteRecyclerAdapter
import org.hamcrest.CoreMatchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Belema Ogan on 2/28/2019.
 */
@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @Rule
    @JvmField
    val mItemsActivity = ActivityTestRule(ItemsActivity::class.java)

    @Test
    fun selectNoteAfterNavigationDrawerChange(){

        //the code that opens the drawer
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())

        //the code that selects the course option on the navigation menu
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_courses))

        val coursePosition = 0

        //the code that clicks the first position in the course recycler view
        onView(withId(R.id.note_list_recyclerview)).perform(
                RecyclerViewActions.actionOnItemAtPosition<CourseRecyclerAdapter.CourseItemViewHolder>(coursePosition, click()))

        //the code that opens the drawer
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())

        //the code that selects the note option on the navigation menu
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_notes))

        val notePosition = 0

        //the code that clicks the first position in the note recycler view
        onView(withId(R.id.note_list_recyclerview)).perform(
                RecyclerViewActions.actionOnItemAtPosition<NoteRecyclerAdapter.NoteItemViewHolder>(notePosition, click()))

        val note = DataManager.notes[notePosition]

        //the code that verifies that the course displayed on the spinner is the expected course
        onView(withId(R.id.spinnerCourses)).check(matches(withSpinnerText(containsString(note.course?.title))))

        //the code that verifies that the text displayed in the note title editText is the expected title
        onView(withId(R.id.textNoteTitle)).check(matches(withText(note.title)))

        //the code that verifies that the text displayed in the note text editText is the expected text
        onView(withId(R.id.textNoteText)).check(matches(withText(note.text)))
    }
}