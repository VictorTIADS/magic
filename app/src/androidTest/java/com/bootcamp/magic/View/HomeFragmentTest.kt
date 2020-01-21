package com.bootcamp.magic.View

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bootcamp.magic.MockWebServerRule
import com.bootcamp.magic.Robots.withDetailFragment
import com.bootcamp.magic.Utils.retry
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