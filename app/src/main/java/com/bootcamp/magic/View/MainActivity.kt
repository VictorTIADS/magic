package com.bootcamp.magic.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bootcamp.magic.Animation.slideInUp
import com.bootcamp.magic.Animation.slideOutDown
import com.bootcamp.magic.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpNavController()
        bottomListener()
    }

    private fun setUpNavController() {
        navController = Navigation.findNavController(this, R.id.nav_host)
    }

    private fun bottomListener() {
        content_bottom_navigation.setOnHomeClick {
            if (navController.currentDestination?.id != R.id.home_fragment) {
                navController.navigate(R.id.action_go_to_home)
            }
        }
        content_bottom_navigation.setOnFavoriteClick {
            if (navController.currentDestination?.id != R.id.favorite_fragment) {
                navController.navigate(R.id.action_go_to_favorite)
            }
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
}
