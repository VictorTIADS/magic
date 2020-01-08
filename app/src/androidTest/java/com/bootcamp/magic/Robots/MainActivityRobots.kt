package com.bootcamp.magic.Robots

import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bootcamp.magic.Extensions.clickOn
import com.bootcamp.magic.Extensions.isDisplayed
import com.bootcamp.magic.R
import com.bootcamp.magic.View.MainActivityTest
import io.mockk.mockk

fun MainActivityTest.withLoginActivity(mock: MainAct.() -> Unit): MainAct{
    return MainAct().apply {
        mock()
    }

}

class MainAct{

    infix fun doSomething(act: MainAct.() -> Unit): MainAct {
        return this.apply(act)
    }

    infix fun checkIf(assert: MainAssert.() -> Unit){
        MainAssert().apply(assert)
    }

    fun ClickHomeBottomTab(){
        R.id.home_button.clickOn()
    }

    fun ClickFavoriteBottomTab(){
        R.id.favorite_button.clickOn()
    }



}

class MainAssert{

    fun bottomTabIsDisplayed(){
        R.id.bottom_tab_button.isDisplayed()
    }


}