package com.bootcamp.magic.View

import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bootcamp.magic.Robots.withLoginActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)

class MainActivityTest{

    @get:Rule
    val activityRule = IntentsTestRule(MainActivity::class.java)

    @Test
    fun whenOpenActivity_shouldShowBottomTabs(){
        withLoginActivity {

        } checkIf {
            bottomTabIsDisplayed()
        }
    }

    @Test
    fun whenClickOnHome_shouldNavigateToHomeFragment(){

    }

    @Test
    fun whenClickOnFavorite_shouldNavigateToFavoriteFragment(){

    }

    @Test
    fun whenClickOnListItem_shouldNavigateToDetailFragment(){

    }



}