package com.thirdwayv.videogamediscovery.features.main_activity.presentation.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.thirdwayv.videogamediscovery.R
import com.thirdwayv.videogamediscovery.core.views.fragments.FABFunctionality
import com.thirdwayv.videogamediscovery.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), LoaderTrigger, IFABImage {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        setFABFunctionality()

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun toggleLoader(shouldShowLoader: Boolean) {
        binding.loaderContainer.isVisible = shouldShowLoader
        binding.fab.isClickable = !shouldShowLoader
    }

    private fun setFABFunctionality() {
        binding.fab.setOnClickListener {
            val navHostFragment: NavHostFragment? =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment?
            navHostFragment?.let {
                val fragments = it.childFragmentManager.fragments
                val lastIndex = fragments.lastIndex
                for (index in lastIndex..0) {
                    val fragment = fragments[index]
                    if (fragment is FABFunctionality) {
                        fragment.doOnFABClick()
                        break
                    }
                }
            }
        }
    }

    override fun changeFABImage(drawableRes: Int) {
        binding.fab.setImageResource(drawableRes)
    }
}