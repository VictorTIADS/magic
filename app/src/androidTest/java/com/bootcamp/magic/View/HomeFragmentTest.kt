package com.bootcamp.magic.View

import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.FragmentScenario.launchInContainer
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bootcamp.magic.Extensions.clickAtPosition
import com.bootcamp.magic.Extensions.clickOn
import com.bootcamp.magic.MockWebServerRule
import com.bootcamp.magic.R
import com.bootcamp.magic.Robots.withDetailFragment
import com.bootcamp.magic.Utils.MockedJsonReader
import com.bootcamp.magic.Utils.retry
import io.mockk.mockk
import io.mockk.verify
import okhttp3.mockwebserver.MockResponse
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

    @get:Rule
    val mockWebServer = MockWebServerRule()

    @Test
    fun whenSuccess_shouldShowCardsList() {
        withDetailFragment {
            mockCardsResponse(mockWebServer.mockWebServer)
        } initHomeFragment {

        } checkIf {
            retry {
                checkListCardIsDisplayed()
            }

        }
    }

    @Test
    fun whenOpenFragment_shouldShowShimmerLoader() {
        withDetailFragment {
            mockABadCardsResponse(mockWebServer.mockWebServer)
        } initHomeFragment {

        } checkIf {
            retry {
                checkShimmerLoadIsDisplayed()
            }
        }
    }

    @Test
    fun whenSuccess_shouldShowCategoryList() {
        withDetailFragment {
            mockCardsResponse(mockWebServer.mockWebServer)
        } initHomeFragment {

        } checkIf {
            retry {
                checkListCategoryIsDisplayed()
            }

        }
    }


    @Test
    fun whenSuccess_shouldShowTypeName() {
        withDetailFragment {
            mockCardsResponse(mockWebServer.mockWebServer)
        } initHomeFragment {

        } checkIf {
            retry {
                checkisTypeNameDisplayed()
            }
        }
    }


    @Test
    fun whenSuccess_shouldShowSetName() {
        withDetailFragment {
            mockCardsResponse(mockWebServer.mockWebServer)
        } initHomeFragment {
        } checkIf {

            retry {
                checkisSetNameDisplayed()
            }
        }
    }

    @Test
    fun whenClickOnItem_shouldCallDetail() {
        withDetailFragment {
            mockCardsResponseWithCode(mockWebServer.mockWebServer, 200)
        } initHomeFragment {
            retry {
                clickOnItemCategoryList()
            }
        } checkIf {
            retry {
                checkNavigateToDetail()
            }

        }
    }

    @Test
    fun whenError_shouldNavigateToErrorFragment() {
        withDetailFragment {
            mockABadCardsResponse(mockWebServer.mockWebServer)
        } initHomeFragment {
        } checkIf {
            retry {
                checkNavigateToError()
            }
        }
    }






}