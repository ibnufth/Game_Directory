package com.ibnufth.gamedirectory

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.ibnufth.gamedirectory.databinding.ActivityMainBinding
import com.ibnufth.gamedirectory.ui.home.HomeFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var binding: ActivityMainBinding? = null
    private val activityMainBinding get() = binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        setSupportActionBar(activityMainBinding.appBarMain.toolbar)
        val toggle = ActionBarDrawerToggle(
            this,
            activityMainBinding.drawerLayout,
            activityMainBinding.appBarMain.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        activityMainBinding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        activityMainBinding.navView.setNavigationItemSelectedListener(this)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, HomeFragment())
                .commit()
            supportActionBar?.title = getString(R.string.app_name)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        var title = getString(R.string.app_name)

        when (item.itemId) {
            R.id.nav_home -> {
                fragment = HomeFragment()
                title = getString(R.string.app_name)
            }
            R.id.nav_favorite -> {
                val uri = Uri.parse("gamedirectory://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
        }
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .commit()
        }
        supportActionBar?.title = title
        activityMainBinding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}