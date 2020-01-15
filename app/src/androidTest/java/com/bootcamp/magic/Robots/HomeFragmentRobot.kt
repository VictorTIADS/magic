package com.bootcamp.magic.Robots

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import com.bootcamp.magic.R
import com.bootcamp.magic.View.HomeFragment
import com.bootcamp.magic.View.HomeFragmentTest


fun HomeFragmentTest.withDetailFragment(mock: HomeFragmentAct.() -> Unit): HomeFragmentAct {
    return HomeFragmentAct().apply {
        mock()
    }

}

class HomeFragmentAct {

    
    val argsMock = Bundle()
    

    infix fun initHomeFragment(act: HomeFragmentAct.() -> Unit): HomeFragmentAct {
        launchFragmentInContainer<HomeFragment>(
            themeResId = R.style.Theme_MaterialComponents_NoActionBar,
            fragmentArgs = argsMock
        )

        return this.apply(act)
    }

    infix fun checkIf(assert: HomeFragmentAssert.() -> Unit) {
        HomeFragmentAssert().apply(assert)
    }

    
    


}

class HomeFragmentAssert {

  

}