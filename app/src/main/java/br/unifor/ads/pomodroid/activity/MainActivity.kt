package br.unifor.ads.pomodroid.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import br.unifor.ads.pomodroid.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val fragment = supportFragmentManager
        val ft = fragment.beginTransaction()
        ft.add(R.id.content_main, TasksListsFragment())
        ft.commit()

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {

            R.id.lists_tasks -> {
                val fragment = supportFragmentManager
                val ft = fragment.beginTransaction()
                ft.replace(R.id.content_main, TasksListsFragment())
                ft.commit()
            }

            R.id.lists_tags -> {
                val fragment = supportFragmentManager
                val ft = fragment.beginTransaction()
                ft.replace(R.id.content_main, TagsFragment())
                ft.commit()
            }

            R.id.logout_app -> {
                val sharedPreferences = getSharedPreferences("dadosUser", 0)
                val edit = sharedPreferences.edit()
                edit.putBoolean("is_loged", false)
                edit.apply()

                val it = Intent(this, LoginActivity::class.java)
                startActivity(it)
                finish()
            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
