package com.ebogoliub.spacex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ebogoliub.spacex.databinding.ActivityMainBinding
import com.ebogoliub.spacex.ui.extentions.setStatusBarLightMode
import com.ebogoliub.spacex.ui.extentions.setupWithMultiBackStackNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.insetter.Insetter
import dev.chrisbanes.insetter.Side

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding(R.id.container)
    private var currentNavController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Insetter.builder()
            .applySystemWindowInsetsToPadding(Side.LEFT or Side.RIGHT)
            .consumeSystemWindowInsets(Insetter.CONSUME_AUTO)
            .applyToView(binding.root)

        Insetter.builder()
            .applySystemWindowInsetsToPadding(Side.BOTTOM)
            .consumeSystemWindowInsets(Insetter.CONSUME_AUTO)
            .applyToView(binding.homeBottomNavigation)

        Insetter.setEdgeToEdgeSystemUiFlags(binding.container, true)

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
    }

    private fun setupBottomNavigationBar() {
        binding.homeBottomNavigation.setupWithMultiBackStackNavController(
            listOf(
                R.navigation.next_launch_nav_graph,
                R.navigation.launches_nav_graph,
                R.navigation.rockets_nav_graph
            ),
            supportFragmentManager,
            R.id.nav_host_fragment,
            intent
        ).observe(this) { navController ->
            currentNavController = navController

            navController.addOnDestinationChangedListener { _, destination, _ ->
                setStatusBarLightMode(destination.id != R.id.navigation_next_launch)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.navigateUp() ?: super.onSupportNavigateUp()
    }
}