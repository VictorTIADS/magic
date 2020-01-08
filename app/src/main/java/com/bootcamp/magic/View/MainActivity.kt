package com.bootcamp.magic.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.core.view.get
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bootcamp.magic.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.tab_bottom.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpNavControler()
        bottomListener()
    }

    private fun setUpNavControler() {
        navController = Navigation.findNavController(this, R.id.nav_host)
    }

    private fun bottomListener() {
        content_bottom_navigation.setOnClickListenerFirstButton {
            navController.navigate(R.id.action_go_to_home)
        }
        content_bottom_navigation.setOnClickListenerSecondButton {
            navController.navigate(R.id.action_go_to_favorite)
        }
    }
}
