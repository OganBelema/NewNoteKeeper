package com.belemaogan.newnotekeeper

import android.arch.lifecycle.ViewModel
import com.belemaogan.newnotekeeper.models.NoteInfo

/**
 * Created by Belema Ogan on 2/28/2019.
 */
class ItemsActivityViewModel: ViewModel() {

    var navigationDrawerDisplaySelection = R.id.nav_notes

    private val mMaxRecentlyViewedNotes = 5
    val mRecentlyViewedNotes = ArrayList<NoteInfo>(mMaxRecentlyViewedNotes)

    fun addToRecentlyViewedNotes(note: NoteInfo) {
        //Check if selection is already in list
        val existingIndex = mRecentlyViewedNotes.indexOf(note)

        if (existingIndex == -1){
            //it isn't in the list
            //Add new one to beginning of list and remove any beyond max we want to keep
            mRecentlyViewedNotes.add(0, note)
            for (index in mRecentlyViewedNotes.lastIndex downTo mMaxRecentlyViewedNotes){
                mRecentlyViewedNotes.removeAt(index)
            }

        } else {
            //it is in the list
            // Shift the ones above down the list and make it first member of the list
            for (index in (existingIndex - 1) downTo 0){
                mRecentlyViewedNotes[index + 1] = mRecentlyViewedNotes[index]
            }
            mRecentlyViewedNotes[0] = note
        }
    }
}