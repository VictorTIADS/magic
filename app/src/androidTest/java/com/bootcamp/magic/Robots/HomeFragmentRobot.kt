package com.bootcamp.magic.Robots

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp.magic.Extensions.isDisplayed
import com.bootcamp.magic.Extensions.scrollToPostionRecyclerView
import com.bootcamp.magic.Injection.viewmodel
import com.bootcamp.magic.R
import com.bootcamp.magic.Utils.MockedJsonReader
import com.bootcamp.magic.View.HomeFragment
import com.bootcamp.magic.View.HomeFragmentDirections
import com.bootcamp.magic.View.HomeFragmentTest
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer


fun HomeFragmentTest.withDetailFragment(mock: HomeFragmentAct.() -> Unit): HomeFragmentAct {
    return HomeFragmentAct().apply {
        mock()
    }

}

class HomeFragmentAct {


    val argsMock = Bundle()
    val NAV_CONTROLLER = mockk<NavController>(relaxed = true)


    infix fun initHomeFragment(act: HomeFragmentAct.() -> Unit): HomeFragmentAct {
        launchFragmentInContainer<HomeFragment>(
            themeResId = R.style.Theme_MaterialComponents_NoActionBar,
            fragmentArgs = argsMock
        )
            .onFragment {
                Navigation.setViewNavController(it.requireView(), DetailFragmentAct.NAV_CONTROLLER)
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

    infix fun checkIf(assert: HomeFragmentAssert.() -> Unit) {
        HomeFragmentAssert().apply(assert)
    }

    fun peformScroll(position: Int) {
        R.id.recycleCards.scrollToPostionRecyclerView<RecyclerView.ViewHolder>(position)
    }


}

class HomeFragmentAssert {

    fun checkShimmerLoadIsDisplayed() {
        R.id.home_loader_place_holder.isDisplayed()
    }

}