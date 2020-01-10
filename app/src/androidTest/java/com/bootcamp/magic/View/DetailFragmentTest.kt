package com.bootcamp.magic.View

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bootcamp.magic.Robots.withDetailFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)

class DetailFragmentTest {

    @Test
    fun whenOpenDetailFragment_shouldRecyclerView() {
        withDetailFragment {
            mockArgs()
            mockCardsEmptyList()
            mockIndex(0)
        } initHomeFragment {

        } checkIf {
            isHozirontalScrollViewDisplayed()
        }
    }

    @Test
    fun whenOpenDetailFragment_shouldHasAListOfCards() {

        withDetailFragment {
            mockArgs()
            mockCardsGoodList()
            mockIndex(5)
        } initHomeFragment {
            scrollToPosition(5)
        } checkIf {
            isAtRightPosition()
        }
    }

    @Test
    fun whenOpenDetailFragment_shouldShowAtTheRightIndex() {
        withDetailFragment {
            mockArgs()
            mockCardsGoodList()
            mockIndex(5)
        } initHomeFragment {

        } checkIf {
            isAtRightPosition()
        }
    }

    @Test
    fun whenOpenDetailFragment_shouldShowFavoriteButton() {
        withDetailFragment {
            mockArgs()
            mockCardsEmptyList()
        } initHomeFragment {

        } checkIf {
            isFavoriteButtonDisplayed()
        }
    }

    @Test
    fun whenOpenDetailFragment_shouldShowCloseButton() {
        withDetailFragment {
            mockArgs()
            mockCardsEmptyList()
        } initHomeFragment {

        } checkIf {
            isCloseButtonDisplayed()
        }
    }

    @Test
    fun whenDoNotLoadTheImage_shouldShowAPlaceHolder() {
        withDetailFragment {
            mockArgs()
            mockCardsBadList()
            mockIndex(0)
        } initHomeFragment {

        } checkIf {
            isPlaceHolderShowing()
        }
    }

    @Test
    fun whenShowList_shouldShowItem() {
        withDetailFragment {
            mockArgs()
            mockCardsBadList()
            mockIndex(0)
        } initHomeFragment {

        } checkIf {
            isRecyclerViewWithItem()
        }
    }

}