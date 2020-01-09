package com.bootcamp.magic.Extensions

import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.bootcamp.magic.Mathers.RecyclerViewMatcher.Companion.withRecyclerView
import com.bootcamp.magic.Mathers.withDrawable
import com.bootcamp.magic.Models.Card
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.hasItem

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

fun ViewInteraction.hasViewWithDrawable(viewId: Int, drawableId: Int) =
    this.check(
        matches(
            hasDescendant(
                allOf(
                    withId(viewId),
                    isDisplayed(),
                    withDrawable(drawableId)
                )
            )
        )
    )

fun Int.atPosition(position: Int) = onView(withRecyclerView(this).atPosition(position))

fun <T : RecyclerView.ViewHolder> Int.isRecyclerWithItem(id:Int): ViewInteraction =
    onView(withId(this)).check(matches(hasDescendant(withId(id))))

fun <T : RecyclerView.ViewHolder> Int.scrollToPostionRecyclerView(position: Int): ViewInteraction =
    onView(withId(this)).perform(RecyclerViewActions.scrollToPosition<T>(position))

fun ViewInteraction.hasViewWithText(id: Int, text: String) =
    this.check(matches(hasDescendant(allOf(withId(id), isDisplayed(), withText(text)))))
