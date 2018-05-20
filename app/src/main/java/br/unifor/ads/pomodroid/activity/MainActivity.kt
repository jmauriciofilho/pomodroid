package br.unifor.ads.pomodroid.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import br.unifor.ads.pomodroid.R
import br.unifor.ads.pomodroid.adapter.UserAdapter
import br.unifor.ads.pomodroid.dao.UserDAO
import br.unifor.ads.pomodroid.entity.User

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mUserList: RecyclerView
    private lateinit var mUserAdd: FloatingActionButton

    private lateinit var mRecyclerViewAdapter: RecyclerView.Adapter<*>
    private lateinit var mRecyclerViewLayoutManager: LinearLayoutManager

    private lateinit var userDAO: UserDAO
    private lateinit var userLists: MutableList<User>


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userDAO = UserDAO(this)

        userLists = userDAO.findAll().toMutableList()

        mRecyclerViewAdapter = UserAdapter(this, userLists)
        mRecyclerViewLayoutManager = LinearLayoutManager(this)

        mUserList = findViewById<RecyclerView>(R.id.main_recycler_users_list).apply {
            setHasFixedSize(false)
            layoutManager = mRecyclerViewLayoutManager
            adapter = mRecyclerViewAdapter
        }

        mUserAdd = findViewById<FloatingActionButton>(R.id.main_recycler_users_add)
        mUserAdd.setOnClickListener(this)


    }

    override fun onStart() {
        super.onStart()
        userLists.clear()
        userLists.addAll(0, userDAO.findAll())
        mUserList.adapter.notifyDataSetChanged()
    }

    override fun onClick(v: View?) {

        val userForm = Intent(this, UserFormActivity::class.java)
        startActivity(userForm)

    }


}
