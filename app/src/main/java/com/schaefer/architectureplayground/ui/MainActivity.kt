package com.schaefer.architectureplayground.ui

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.schaefer.architectureplayground.R
import com.schaefer.architectureplayground.databinding.ActivityMainBinding
import com.schaefer.architectureplayground.ui.characters.CharactersFragment
import com.schaefer.architectureplayground.ui.episodes.EpisodesFragment
import com.schaefer.architectureplayground.ui.locations.LocationsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val fragmentsList = listOf(
        CharactersFragment(),
        EpisodesFragment(),
        LocationsFragment()
    )
    private var activeFragment = fragmentsList.first()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        setupFragments()
        navView.setOnNavigationItemSelectedListener {
            return@setOnNavigationItemSelectedListener when (it.itemId) {
                R.id.navigation_characters -> {
                    supportFragmentManager.beginTransaction()
                        .hide(activeFragment)
                        .show(fragmentsList.first())
                        .commit()
                    activeFragment = fragmentsList.first()
                    true
                }
                R.id.navigation_episodes -> {
                    supportFragmentManager.beginTransaction()
                        .hide(activeFragment)
                        .show(fragmentsList[1])
                        .commit()
                    activeFragment = fragmentsList[1]
                    true
                }
                R.id.navigation_locations -> {
                    supportFragmentManager.beginTransaction()
                        .hide(activeFragment)
                        .show(fragmentsList.last())
                        .commit()
                    activeFragment = fragmentsList.last()
                    true
                }
                else -> false
            }
        }
    }

    private fun setupFragments() {
        supportFragmentManager.beginTransaction()
            .add(R.id.contentMain, fragmentsList.last(), "LocationsFragment")
            .hide(fragmentsList.last())
            .commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.contentMain, fragmentsList[1], "EpisodesFragment")
            .hide(fragmentsList[1])
            .commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.contentMain, fragmentsList.first(), "CharactersFragment")
            .commit()
    }
}