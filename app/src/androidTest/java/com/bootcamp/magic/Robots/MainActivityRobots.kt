package com.bootcamp.magic.Robots

import androidx.navigation.findNavController
import androidx.test.core.app.ActivityScenario
import com.bootcamp.magic.Extensions.clickAtPosition
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
    fun replaceToEmpty(){
        val fragmentManager = mainActivity.supportFragmentManager.fragments[0].childFragmentManager
        fragmentManager.beginTransaction().replace(R.id.nav_host,EmptyFragment()).commit()
    }
    fun replaceToError(){
        val fragmentManager = mainActivity.supportFragmentManager.fragments[0].childFragmentManager
        fragmentManager.beginTransaction().replace(R.id.nav_host,ErrorFragment()).commit()
    }

    fun navigateToError(){
        mainActivity.findNavController(R.id.nav_host).navigate(R.id.action_go_to_error)
    }
    fun navigateToEmpty(){
        mainActivity.findNavController(R.id.nav_host).navigate(R.id.action_go_to_empty)
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
        R.id.recycleCards.clickAtPosition(1)
    }

    fun clickOnDetailCloseButton() {
        R.id.detail_button_close.clickOn()
    }
}

class MainAssert {

    fun bottomTabIsDisplayed() {
        R.id.bottom_tab_button.isDisplayed()
    }

    fun checkDetailFragment() {
        val fragmentManager = mainActivity.supportFragmentManager.fragments[0].childFragmentManager
        assert(fragmentManager.fragments[0] is DetailFragment)
    }


    fun checkFavoriteFragment() {
        val fragmentManager = mainActivity.supportFragmentManager.fragments[0].childFragmentManager
        assert(fragmentManager.fragments[0] is FavoriteFragment)
    }

    fun checkHomeFragment() {
        val fragmentManager = mainActivity.supportFragmentManager.fragments[0].childFragmentManager
        assert(fragmentManager.fragments[0] is HomeFragment)
    }
    fun checkErrorFragment() {
        val fragmentManager = mainActivity.supportFragmentManager.fragments[0].childFragmentManager
        assert(fragmentManager.fragments[0] is ErrorFragment)
    }
}