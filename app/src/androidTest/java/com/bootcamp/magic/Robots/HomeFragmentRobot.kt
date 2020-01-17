package com.bootcamp.magic.Robots

import android.os.Bundle
import android.view.View
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.bootcamp.magic.Adapter.ViewlHolder
import com.bootcamp.magic.Extensions.atPosition
import com.bootcamp.magic.Extensions.clickAtPosition
import com.bootcamp.magic.Extensions.isDisplayed
import com.bootcamp.magic.Extensions.scrollToPostionRecyclerView
import com.bootcamp.magic.Injection.viewmodel
import com.bootcamp.magic.Models.Cards
import com.bootcamp.magic.Models.adapter.ViewHolderCategory
import com.bootcamp.magic.Models.adapter.ViewHolderItem
import com.bootcamp.magic.R
import com.bootcamp.magic.Robots.HomeFragmentAct.Companion.NAV_CONTROLLER_HOME
import com.bootcamp.magic.Utils.MockedJsonReader
import com.bootcamp.magic.View.HomeFragment
import com.bootcamp.magic.View.HomeFragmentDirections
import com.bootcamp.magic.View.HomeFragmentTest
import com.bootcamp.magic.View.MainActivity
import io.mockk.*
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import java.util.concurrent.TimeUnit


fun HomeFragmentTest.withDetailFragment(mock: HomeFragmentAct.() -> Unit): HomeFragmentAct {
    return HomeFragmentAct().apply {
        mock()
    }

}

class HomeFragmentAct {


    val argsMock = Bundle()
   companion object{
       val NAV_CONTROLLER_HOME = mockk<NavController>(relaxed = true)
   }


    infix fun initHomeFragment(act: HomeFragmentAct.() -> Unit): HomeFragmentAct {
        val scenario = launchFragmentInContainer<HomeFragment>(
            themeResId = R.style.Theme_MaterialComponents_NoActionBar,
            fragmentArgs = argsMock
        )
            scenario.onFragment {
                Navigation.setViewNavController(it.requireView(), NAV_CONTROLLER_HOME)


            }



        return this.apply(act)
    }

    fun mockCardsResponse(mockWebServer: MockWebServer) {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(MockedJsonReader.readmockedJson("sets.json"))
        )
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(MockedJsonReader.readmockedJson("cards.json"))
        )
    }

    fun mockCardsResponseWithCode(mockWebServer: MockWebServer, requestCode: Int) {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(requestCode)
                .setBody(MockedJsonReader.readmockedJson("sets.json"))
        )
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(requestCode)
                .setBody(MockedJsonReader.readmockedJson("cards.json"))
        )
    }
    fun mockABadCardsResponse(mockWebServer: MockWebServer) {
        mockWebServer.enqueue(
            MockResponse().setHttp2ErrorCode(302)
                .setBody(MockedJsonReader.readmockedJson("bad_sets.json"))
        )
        mockWebServer.enqueue(
            MockResponse().setHttp2ErrorCode(302)
                .setBody(MockedJsonReader.readmockedJson("bad_cards.json"))
        )
    }
    fun mockABadCardsResponseWithDelay(mockWebServer: MockWebServer) {
        mockWebServer.enqueue(
            MockResponse().setResponseCode(200)
                .setBodyDelay(40000,TimeUnit.SECONDS)
                .setBody(MockedJsonReader.readmockedJson("sets.json"))
        )
        mockWebServer.enqueue(
            MockResponse().setResponseCode(200)
                .setBody(MockedJsonReader.readmockedJson("cards.json"))
        )
    }

    infix fun checkIf(assert: HomeFragmentAssert.() -> Unit) {
        HomeFragmentAssert().apply(assert)
    }

    fun peformScrollRecyclerCards(position: Int) {
        R.id.recycleCards.scrollToPostionRecyclerView<RecyclerView.ViewHolder>(position)
    }

    fun peformScrollRecyclerCategory(position: Int) {
        R.id.categoryList.scrollToPostionRecyclerView<ViewHolderItem>(position)
    }

    fun clickOnItemCategoryList(){
        R.id.categoryList.clickAtPosition(4)

    }
    fun clickOnItemCardsList(){
        R.id.recycleCards.clickAtPosition(4)

    }




}

class HomeFragmentAssert {

    fun checkShimmerLoadIsDisplayed() {
        verify { R.id.home_loader_place_holder.isDisplayed() }
    }

    fun checkListCardIsDisplayed(){
        R.id.recycleCards.isDisplayed()
    }

    fun checkListCategoryIsDisplayed(){
        R.id.categoryList.isDisplayed()
    }

    fun checkNavigateToDetail(){
        verify { NAV_CONTROLLER_HOME.navigate(R.id.action_go_to_detail) }
    }

    fun checkNavigateToError(){
        verify { NAV_CONTROLLER_HOME.navigate(R.id.action_go_to_error) }
    }

    fun checkisAtRightPositionCategory(position: Int) {
        R.id.categoryList.atPosition(position).check(matches(isDisplayed()))
    }
    fun checkisAtRightPositionCards(position: Int) {
        R.id.recycleCards.atPosition(position).check(matches(isDisplayed()))
    }

    fun checkisSetNameDisplayed(){
        "RNA Guild Kit".isDisplayed()
    }
    fun checkisTypeNameDisplayed(){
        "Enchantment".isDisplayed()
    }


}