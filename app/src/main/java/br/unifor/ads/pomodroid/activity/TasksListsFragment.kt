package br.unifor.ads.pomodroid.activity


import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.unifor.ads.pomodroid.R
import br.unifor.ads.pomodroid.adapter.ListsTasksAdapter
import br.unifor.ads.pomodroid.dao.TaskListDAO
import br.unifor.ads.pomodroid.entity.TaskList


/**
 * A simple [Fragment] subclass.
 */
class TasksListsFragment : Fragment(),  View.OnClickListener {

    private lateinit var mListsTasks: RecyclerView
    private lateinit var mListTaskAdd: FloatingActionButton

    private lateinit var mRecyclerViewAdapter: RecyclerView.Adapter<*>
    private lateinit var mRecyclerViewLayoutManager: LinearLayoutManager

    private lateinit var taskListDAO: TaskListDAO
    private lateinit var tasksLists: MutableList<TaskList>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val inflaterTaskList = inflater.inflate(R.layout.fragment_tasks_lists, container, false)


        taskListDAO = TaskListDAO(activity as MainActivity)
        tasksLists = taskListDAO.findAll().toMutableList()

        mRecyclerViewAdapter = ListsTasksAdapter(activity as MainActivity, tasksLists)
        mRecyclerViewLayoutManager = LinearLayoutManager(activity as MainActivity)

        mListsTasks = inflaterTaskList.findViewById<RecyclerView>(R.id.main_recycler_lists_tasks).apply {
            setHasFixedSize(false)
            layoutManager = mRecyclerViewLayoutManager
            adapter = mRecyclerViewAdapter
        }

        mListTaskAdd = inflaterTaskList.findViewById<FloatingActionButton>(R.id.main_recycler_list_add)
        mListTaskAdd.setOnClickListener(this)

        return inflaterTaskList
    }


    override fun onStart() {
        super.onStart()
        tasksLists.clear()
        tasksLists.addAll(0, taskListDAO.findAll())
        mListsTasks.adapter.notifyDataSetChanged()
    }


    override fun onClick(v: View?) {
        val taskListForm = Intent(activity as MainActivity, TaskListFormActivity::class.java)
        startActivity(taskListForm)
    }

}
