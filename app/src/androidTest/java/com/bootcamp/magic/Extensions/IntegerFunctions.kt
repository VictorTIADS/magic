package com.bootcamp.magic.Extensions

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers

fun Int.isComponentDisplayed(){
    Espresso.onView(ViewMatchers.withText(this))
        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
}

fun Int.isComponentEmpty(){
    Espresso.onView(ViewMatchers.withId(this))
        .check(ViewAssertions.matches(ViewMatchers.withText("")))
        .perform(ViewActions.closeSoftKeyboard())
}

fun Int.clickOn(){
    Espresso.onView(ViewMatchers.withId(this))
        .perform(ViewActions.click())
}

fun Int.isDisplayed(){
    Espresso.onView(ViewMatchers.withId(this))
        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
}