package br.unifor.ads.pomodroid.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.unifor.ads.pomodroid.R
import br.unifor.ads.pomodroid.dao.TaskListDAO
import br.unifor.ads.pomodroid.dao.UserDAO
import br.unifor.ads.pomodroid.entity.TaskList

class TestTaskListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_task_list)


        deleteTaskList()

    }

    private fun createTaskList(){
        val userDAO = UserDAO(TestTaskListActivity@this)
        val taskListDAO = TaskListDAO(TestTaskListActivity@this)

        val user = userDAO.find(1)

        val taskList = TaskList(null, "Tarefas do trabalho", "tarefas que tenho pendentes no trabalho", user!!)

        taskListDAO.insert(taskList)

        val taskLists = taskListDAO.findAll()

        Log.d("result", taskLists.toString())
    }

    private fun updateTaskList(){
        val userDAO = UserDAO(TestTaskListActivity@this)
        val taskListDAO = TaskListDAO(TestTaskListActivity@this)

        val taskListOld = taskListDAO.find(2)

        val user = userDAO.find(1)

        Log.d("result", taskListOld.toString())

        val taskList = TaskList(2, "Tarefas UNIFOR", "tarefas que tenho pendentes no trabalho", user!!)

        taskListDAO.update(taskList)

        val taskListAlterada = taskListDAO.find(2)

        Log.d("result", taskListAlterada.toString())
    }

    private fun deleteTaskList(){
        val taskListDAO = TaskListDAO(TestTaskListActivity@this)

        val taskListOld = taskListDAO.find(2)

        taskListDAO.delete(taskListOld!!)

        val taskLists = taskListDAO.findAll()

        Log.d("result", taskLists.toString())
    }
}
