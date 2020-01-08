package com.bootcamp.magic.View

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import com.bootcamp.magic.Robots.withDetailFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)

class DetailFragmentTest{
    @Test
    fun whenOpenDetailFragment_shouldScrollList(){
        withDetailFragment {

        } initHomeFragment {

        } checkIf {
            scrollToPosition()
        }
    }

    @Test
    fun whenOpenDetailFragment_shoulShowFavoriteButton(){
        withDetailFragment {

        } initHomeFragment {

        } checkIf {
            isFavoriteButtonDisplayed()
        }
    }

    @Test
    fun whenOpenDetailFragment_shoulShowCloseButton(){
        withDetailFragment {

        } initHomeFragment {

        } checkIf {
            isCloseButtonDisplayed()
        }
    }
}