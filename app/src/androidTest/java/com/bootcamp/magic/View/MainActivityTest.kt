package com.bootcamp.magic.View

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bootcamp.magic.Robots.withMainActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)

class MainActivityTest {

    @Test
    fun whenOpenActivity_shouldShowBottomTabs() {
        withMainActivity {

        } initMainActivity {
            replaceToEmpty()
        } checkIf {
            bottomTabIsDisplayed()
        }
    }

    @Test
    fun whenError_shouldShowErrorFragment(){
        withMainActivity {

        } initMainActivity {
            navigateToError()
        } checkIf {
            checkErrorFragment()
        }
    }

    @Test
    fun whenClickOnHome_shouldNavigateToHomeFragment() {
        withMainActivity {

        } initMainActivity {
            clickFavoriteBottomTab()
            clickHomeBottomTab()
        } checkIf {
            checkHomeFragment()
        }
    }

    @Test
    fun whenClickOnFavorite_shouldNavigateToFavoriteFragment() {
        withMainActivity {

        } initMainActivity {
            clickFavoriteBottomTab()
        } checkIf {
            checkFavoriteFragment()
        }
    }

//    @Test
//    fun whenClickOnItemList_shouldShowDetailFragment() {
//        withMainActivity {
//
//        } initMainActivity {
//            clickOnItemList()
//        } checkIf {
//            checkDetailFragment()
//        }
//    }

//    @Test
//    fun whenClickOnCloseButtonInDetailFragment_shouldNavigateToHome() {
//        withMainActivity {
//
//        } initMainActivity {
//            clickOnItemList()
//            clickOnDetailCloseButton()
//        } checkIf {
//            checkHomeFragment()
//        }
//    }

}