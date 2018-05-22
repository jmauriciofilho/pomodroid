package br.unifor.ads.pomodroid.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.MenuItem
import android.view.View
import br.unifor.ads.pomodroid.R
import br.unifor.ads.pomodroid.adapter.ListsTasksAdapter
import br.unifor.ads.pomodroid.dao.TagDAO
import br.unifor.ads.pomodroid.dao.TaskListDAO
import br.unifor.ads.pomodroid.entity.Tag
import br.unifor.ads.pomodroid.entity.TaskList
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private lateinit var mListsTasks: RecyclerView
    private lateinit var mListTaskAdd: FloatingActionButton

    private lateinit var mRecyclerViewAdapter: RecyclerView.Adapter<*>
    private lateinit var mRecyclerViewLayoutManager: LinearLayoutManager

    private lateinit var taskListDAO: TaskListDAO
    private lateinit var tasksLists: MutableList<TaskList>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        taskListDAO = TaskListDAO(this)
        tasksLists = taskListDAO.findAll().toMutableList()

        mRecyclerViewAdapter = ListsTasksAdapter(this, tasksLists)
        mRecyclerViewLayoutManager = LinearLayoutManager(this)

        mListsTasks = findViewById<RecyclerView>(R.id.main_recycler_lists_tasks).apply {
            setHasFixedSize(false)
            layoutManager = mRecyclerViewLayoutManager
            adapter = mRecyclerViewAdapter
        }

        mListTaskAdd = findViewById<FloatingActionButton>(R.id.main_recycler_list_add)
        mListTaskAdd.setOnClickListener(this)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onStart() {
        super.onStart()
        tasksLists.clear()
        tasksLists.addAll(0, taskListDAO.findAll())
        mListsTasks.adapter.notifyDataSetChanged()
    }

    override fun onClick(v: View?) {
        val taskListForm = Intent(this, TaskListFormActivity::class.java)
        startActivity(taskListForm)
        finish()
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
                val listsTasks = Intent(this, MainActivity::class.java)
                startActivity(listsTasks)
                finish()
            }

            R.id.lists_tags -> {
                Log.d("teste", "tags")
            }

            R.id.logout_app -> {
                Log.d("teste", "sair")
            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
