package com.example.bmicalculatorwithhistory

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawer: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val setuser = setUserProfile()
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawer = findViewById(R.id.drawer_layout)
        val navigationview: NavigationView = findViewById(R.id.nav_view)
        navigationview.setNavigationItemSelectedListener(this)
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(
                R.id.fragment_container,
                BMICalculatorFragment()
            ).commit()
            navigationview.setCheckedItem(R.id.nav_share)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_message -> getSupportFragmentManager().beginTransaction().replace(
                R.id.fragment_container,
                BMICalculatorFragment()
            ).commit()
//            R.id.navigation_chat -> getSupportFragmentManager().beginTransaction().replace(
//                R.id.fragment_container,
//                BMIHistoryFragment()
//            ).commit()
            R.id.navigation_location -> getSupportFragmentManager().beginTransaction().replace(
                R.id.fragment_container,
                mapswebview()
            ).commit()
//            R.id.nav_profile -> getSupportFragmentManager().beginTransaction().replace(
//                R.id.fragment_container,
//                UserProfile()
//            ).commit()
            R.id.nav_share -> Toast.makeText(this, "share", Toast.LENGTH_LONG).show()
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}