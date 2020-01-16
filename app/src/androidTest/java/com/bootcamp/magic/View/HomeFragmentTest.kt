package com.bootcamp.magic.View

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bootcamp.magic.MockWebServerRule
import com.bootcamp.magic.Robots.withDetailFragment
import com.bootcamp.magic.Utils.retry
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HomeFragmentTest{

    @get:Rule
    val mockWebServer = MockWebServerRule()

    @Test
    fun whenError_shouldNavigateToErrorFragment(){
        withDetailFragment {
            mockCardsResponseWithCode(mockWebServer.mockWebServer,404)
        } initHomeFragment {

        } checkIf {
            checkShimmerLoadIsDisplayed()
        }
    }

}