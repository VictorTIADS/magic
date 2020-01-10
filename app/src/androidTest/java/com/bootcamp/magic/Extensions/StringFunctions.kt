package com.bootcamp.magic.Extensions

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers

fun String.typeIn(id: Int) {
    Espresso.onView(ViewMatchers.withId(id))
        .perform(ViewActions.typeText(this))
        .perform(ViewActions.closeSoftKeyboard())
}

fun String.isDisplayed() {
    Espresso.onView(ViewMatchers.withText(this))
        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
}