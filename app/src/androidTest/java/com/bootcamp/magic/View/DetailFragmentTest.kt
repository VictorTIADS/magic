package com.bootcamp.magic.View

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bootcamp.magic.Robots.withDetailFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)

class DetailFragmentTest{

    val mock  = HomeFragment

    @Test
    fun whenOpenDetailFragment_shouldRecyclerView(){

        withDetailFragment {

        } initHomeFragment {

        } checkIf {
            isHozirontalScrollViewDisplayed()
        }
    }

    @Test
    fun whenOpenDetailFragment_shouldHasAListOfCards(){

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