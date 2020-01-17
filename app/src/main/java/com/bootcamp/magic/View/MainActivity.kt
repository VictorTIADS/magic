package com.bootcamp.magic.View

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bootcamp.magic.Animation.slideInUp
import com.bootcamp.magic.Animation.slideOutDown
import com.bootcamp.magic.R
import com.bootcamp.magic.ViewModel.HomeFragmentViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpNavController()
        bottomListener()

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id){
                R.id.detail_fragment -> {
                    hideComponentsWhenGoToDetail()
                }
                R.id.error_fragment -> {
                    hideComponentsWhenGoToDetail()
                }
                R.id.empty_fragment -> {
                    hideComponentsWhenGoToDetail()
                }
                R.id.home_fragment -> {
                    if (content_bottom_navigation.visibility== View.GONE){
                        showComponentsBack()
                    }
                }
                R.id.favorite_fragment -> {
                    if (content_bottom_navigation.visibility== View.GONE){
                        showComponentsBack()
                    }
                }
            }

        }
    }

    private fun setUpNavController() {
        navController = Navigation.findNavController(this, R.id.nav_host)
    }

    private fun bottomListener() {
        content_bottom_navigation.setOnHomeClick {
            if(navController.currentDestination?.id != R.id.home_fragment){
                navController.navigate(R.id.action_go_to_home)
            }
        }
        content_bottom_navigation.setOnFavoriteClick {
            if(navController.currentDestination?.id != R.id.favorite_fragment){
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

    override fun onBackPressed() {
        super.onBackPressed()
        navController.navigateUp()
    }
}
