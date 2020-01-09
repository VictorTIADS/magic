package com.bootcamp.magic.View

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bootcamp.magic.Adapter.DetailAdapter
import org.junit.Assert.*
import com.bootcamp.magic.Robots.withDetailFragment
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)

class DetailFragmentTest{

    val mock  = HomeFragment

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

    @Test
    fun test(){
        withDetailFragment {

        } initHomeFragment {

        } checkIf {
           Thread.sleep(5000)
        }
    }
}