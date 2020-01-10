package com.bootcamp.magic.Robots

import androidx.test.core.app.ActivityScenario
import com.bootcamp.magic.Extensions.clickOn
import com.bootcamp.magic.Extensions.isDisplayed
import com.bootcamp.magic.R
import com.bootcamp.magic.View.*

private lateinit var mainActivity: MainActivity

fun MainActivityTest.withMainActivity(mock: MainAct.() -> Unit): MainAct {
    return MainAct().apply {
        mock()
    }
}

class MainAct {

    private lateinit var mainAct: ActivityScenario<MainActivity>

    infix fun initMainActivity(act: MainAct.() -> Unit): MainAct {
        mainAct = ActivityScenario.launch(MainActivity::class.java)
        mainAct.onActivity {
            mainActivity = it
        }

        return this.apply(act)
    }

    infix fun checkIf(assert: MainAssert.() -> Unit) {
        MainAssert().apply(assert)
    }

    fun clickHomeBottomTab() {
        R.id.home_button.clickOn()
    }

    fun clickFavoriteBottomTab() {
        R.id.favorite_button.clickOn()
    }

    fun clickOnItemList() {
        R.id.detail.clickOn()
    }

    fun clickOnDetailCloseButton() {
        R.id.detail_button_close.clickOn()
    }
}

class MainAssert {

    fun bottomTabIsDisplayed() {
        R.id.bottom_tab_button.isDisplayed()
    }

    fun checkGoToDetail() {
        val fragmentManager = mainActivity.supportFragmentManager.fragments[0].childFragmentManager
        assert(fragmentManager.fragments[0] is DetailFragment)
    }


    fun checkGoToFavorites() {
        val fragmentManager = mainActivity.supportFragmentManager.fragments[0].childFragmentManager
        assert(fragmentManager.fragments[0] is FavoriteFragment)
    }

    fun checkGoToHome() {
        val fragmentManager = mainActivity.supportFragmentManager.fragments[0].childFragmentManager
        assert(fragmentManager.fragments[0] is HomeFragment)
    }
}