package br.unifor.ads.pomodroid.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.unifor.ads.pomodroid.R
import br.unifor.ads.pomodroid.dao.TaskDAO
import br.unifor.ads.pomodroid.dao.TaskListDAO
import br.unifor.ads.pomodroid.entity.Task

class TestTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_task)

        deleteTask()


    }

    private fun createTask(){
        val taskListDAO = TaskListDAO(TestTaskListActivity@this)
        val taskDAO = TaskDAO(TestTaskListActivity@this)

        val taskList = taskListDAO.find(1)
        val task = Task(null, "teste 1", "tarefa de teste", false, 5, 5, taskList!!)
        val task2 = Task(null, "teste 2", "tarefa de teste", false, 5, 5, taskList!!)

        taskDAO.insert(task)
        taskDAO.insert(task2)

        val tasks = taskDAO.findAll()

        Log.d("result", tasks.toString())
    }

    private fun updateTask(){
        val taskListDAO = TaskListDAO(TestTaskListActivity@this)
        val taskDAO = TaskDAO(TestTaskListActivity@this)

        val taskOld = taskDAO.find(2)

        val taskList = taskListDAO.find(1)

        Log.d("result", taskOld.toString())

        val task = Task(2, "teste update", "tarefa de teste", false, 5, 5, taskList!!)

        taskDAO.update(task)

        val taskAlterada = taskDAO.find(2)

        Log.d("result", taskAlterada.toString())
    }

    private fun deleteTask(){
        val taskDAO = TaskDAO(TestTaskListActivity@this)

        val task = taskDAO.find(2)

        taskDAO.delete(task!!)

        val tasks = taskDAO.findAll()

        Log.d("result", tasks.toString())
    }
}
