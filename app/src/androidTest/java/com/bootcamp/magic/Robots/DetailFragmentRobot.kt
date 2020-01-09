package com.bootcamp.magic.Robots

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.bootcamp.magic.Adapter.ViewlHolder
import com.bootcamp.magic.Extensions.*
import com.bootcamp.magic.Models.Card
import com.bootcamp.magic.Models.Cards
import com.bootcamp.magic.R
import com.bootcamp.magic.Robots.DetailFragmentAct.Companion.CURRENT_ITEM_INDEX
import com.bootcamp.magic.Robots.DetailFragmentAct.Companion.INDEX_MOCKED
import com.bootcamp.magic.Robots.DetailFragmentAct.Companion.NAV_CONTROLLER
import com.bootcamp.magic.View.DetailFragment
import com.bootcamp.magic.View.DetailFragmentTest
import io.mockk.mockk
import io.mockk.verify


fun DetailFragmentTest.withDetailFragment(mock: DetailFragmentAct.() -> Unit): DetailFragmentAct {
    return DetailFragmentAct().apply {
        mock()
    }

}

class DetailFragmentAct {

    companion object{
        var CURRENT_ITEM_INDEX = 0
        var INDEX_MOCKED = 0
        val NAV_CONTROLLER = mockk<NavController>(relaxed = true)
    }

    lateinit var argsMock : Bundle
    lateinit var  listCardsMock : Cards

    infix fun initHomeFragment(act: DetailFragmentAct.() -> Unit): DetailFragmentAct {
        launchFragmentInContainer<DetailFragment>(themeResId = R.style.Theme_MaterialComponents_NoActionBar,fragmentArgs = argsMock)
            .onFragment {
                CURRENT_ITEM_INDEX  = it.scrollView.currentItem
                Navigation.setViewNavController(it.requireView(), NAV_CONTROLLER)
            }
        return this.apply(act)
    }

    infix fun checkIf(assert: DetailFragmentAssert.() -> Unit) {
        DetailFragmentAssert().apply(assert)
    }
    fun mockArgs(){
        argsMock = Bundle()
    }

    fun mockCardsEmptyList(){
        listCardsMock = Cards(arrayListOf())
        argsMock.putParcelable("cardList",listCardsMock)
    }

    fun mockCardsGoodList(){
        listCardsMock = Cards(arrayListOf(
            Card(
                1,
                "A",
                "asd",
                "A",
                arrayListOf()
            ),
            Card(
                2,
                "A",
                "asd",
                "A",
                arrayListOf()
            ),
            Card(
                3,
                "A",
                "asd",
                "A",
                arrayListOf()
            ),
            Card(
                4,
                "A",
                "asd",
                "A",
                arrayListOf()
            ),
            Card(
                5,
                "A",
                "asd",
                "A",
                arrayListOf()
            ),
            Card(
                6,
                "A",
                "asd",
                "A",
                arrayListOf()
            ),
            Card(
                7,
                "A",
                "asd",
                "A",
                arrayListOf()
            ),
            Card(
                8,
                "A",
                "asd",
                "A",
                arrayListOf()
            ),
            Card(
                9,
                "A",
                "asd",
                "A",
                arrayListOf()
            ),
            Card(
                10,
                "A",
                "asd",
                "A",
                arrayListOf()
            )
        ))
        argsMock.putParcelable("cardList",listCardsMock)
    }

    fun mockCardsBadList(){
        listCardsMock = Cards(arrayListOf(
            Card(
                1,
                "A",
                "",
                "A",
                arrayListOf()
            )))
        argsMock.putParcelable("cardList",listCardsMock)
    }

    fun mockIndex(index:Int){
        INDEX_MOCKED = index
        argsMock.putInt("listIndex",INDEX_MOCKED)
    }

    fun scrollToPosition(position:Int) {
        R.id.detail_scroll_view.scrollToPostionRecyclerView<ViewlHolder>(position)
    }

}

class DetailFragmentAssert {

    fun checkNavigateUp(){
        verify {
            NAV_CONTROLLER.navigateUp()
        }
    }

    fun isFavoriteButtonDisplayed() {
        R.id.detail_button_favorite.isDisplayed()
    }

    fun isCloseButtonDisplayed() {
        R.id.detail_button_close.isDisplayed()
    }

    fun isHozirontalScrollViewDisplayed(){
        R.id.detail_scroll_view.isDisplayed()
    }

    fun isRecyclerViewWithItem(){
        R.id.detail_scroll_view.isRecyclerWithItem<ViewlHolder>(R.id.image_item)
    }
    fun isPlaceHolderShowing(){
        R.id.detail_scroll_view.atPosition(INDEX_MOCKED).hasViewWithDrawable(R.id.image_item,R.drawable.magic_place_holder)
    }

    fun isAtRightPosition(){
        R.id.detail_scroll_view.atPosition(INDEX_MOCKED).check(matches(isDisplayed()))
    }


}