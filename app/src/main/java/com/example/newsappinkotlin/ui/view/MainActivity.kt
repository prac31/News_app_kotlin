package com.example.newsappinkotlin.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.newsappinkotlin.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_nav_view.setupWithNavController(findNavController(R.id.nav_host_fragment_container))

        navController= Navigation.findNavController(this,R.id.nav_host_fragment_container)
        NavigationUI.setupWithNavController(bottom_nav_view,navController)
        syncNavigationBar() }

    // hides the navigation bar in some fragments
    private fun syncNavigationBar()
    {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id==R.id.itemDetailsFragment || destination.id==R.id.splashFragment || destination.id==R.id.webFragment3 )
                bottom_nav_view.visibility= View.GONE
            else
                bottom_nav_view.visibility=View.VISIBLE }
    }

}
