package com.bootcamp.magic.View

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bootcamp.magic.Robots.withMainActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)

class MainActivityTest{

    @Test
    fun whenOpenActivity_shouldShowBottomTabs(){
        withMainActivity {

        } initMainActivity {

        } checkIf {
            bottomTabIsDisplayed()
        }
    }

    @Test
    fun whenClickOnHome_shouldNavigateToHomeFragment(){
        withMainActivity {

        } initMainActivity {
            clickFavoriteBottomTab()
            clickHomeBottomTab()
        } checkIf {
            checkGoToHome()
        }
    }

    @Test
    fun whenClickOnFavorite_shouldNavigateToFavoriteFragment(){
        withMainActivity {

        } initMainActivity {
            clickFavoriteBottomTab()
        } checkIf {
            checkGoToFavorites()
        }
    }

    @Test
    fun whenClickOnItemList_shouldShowDetailFragment(){
        withMainActivity {

        } initMainActivity {
            clickOnItemList()
        } checkIf {
            checkGoToDetail()
        }
    }

    @Test
    fun whenClickOnCloseButtonInDetailFragment_shouldNavigateToHome(){
        withMainActivity {

        } initMainActivity {
            clickOnItemList()
            clickOnDetailCloseButton()
        } checkIf {
            checkGoToHome()
        }
    }

}