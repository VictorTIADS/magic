package com.bootcamp.magic.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.viewpager.widget.ViewPager
import com.bootcamp.magic.Adapter.PageAdapter
import com.bootcamp.magic.Animation.slideInUp
import com.bootcamp.magic.Animation.slideOutDown
import com.bootcamp.magic.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private val homeFragment by lazy { HomeFragment() }
    private val favoriteFragment by lazy { FavoriteFragment() }
    lateinit var mViewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpNavController()
        bottomListener()
        initViewPager()
        setupViewPager(mViewPager)
    }
    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = PageAdapter(supportFragmentManager)
        adapter.addFragment(homeFragment, "Home")
        adapter.addFragment(favoriteFragment, "Favorite")
        viewPager.adapter = adapter
    }
    private fun initViewPager(){
        mViewPager = main_view_pager
    }
    private fun setUpNavController() {
        navController = Navigation.findNavController(this, R.id.nav_host)
    }

    private fun bottomListener() {
        content_bottom_navigation.setOnHomeClick {
            mViewPager.currentItem = 0
        }
        content_bottom_navigation.setOnFavoriteClick {
                mViewPager.currentItem = 1
        }
    }

    fun hideComponentsWhenGoToDetail() {
        content_bottom_navigation.slideOutDown()
        main_gradient.slideOutDown()
    }

    fun showComponentsBack() {
        content_bottom_navigation.slideInUp()
        main_gradient.slideInUp()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        navController.navigate(R.id.action_go_to_home)
    }
}
