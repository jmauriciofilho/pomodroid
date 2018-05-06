package br.unifor.ads.pomodroid.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.unifor.ads.pomodroid.R
import br.unifor.ads.pomodroid.dao.TagDAO
import br.unifor.ads.pomodroid.dao.TaksTagsDAO
import br.unifor.ads.pomodroid.dao.TaskDAO

class TestTasksTagsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_tasks_tags)

        removeTag()

    }

    private fun atribuirTag(){
        val tasksDAO = TaskDAO(TestTasksTagsActivity@this)
        val tagDAO = TagDAO(TestTasksTagsActivity@this)
        val taksTagsDAO = TaksTagsDAO(TestTasksTagsActivity@this)

        val task = tasksDAO.find(1)
        val task2 = tasksDAO.find(4)
        val tag = tagDAO.find(1)

        taksTagsDAO.atribuirTag(task!!, tag!!)
        taksTagsDAO.atribuirTag(task2!!, tag!!)

        val tasksTag = taksTagsDAO.findTasksTag(tag)

        Log.d("result", tasksTag.toString())
    }

    private fun removeTag(){
        val tasksDAO = TaskDAO(TestTasksTagsActivity@this)
        val tagDAO = TagDAO(TestTasksTagsActivity@this)
        val taksTagsDAO = TaksTagsDAO(TestTasksTagsActivity@this)

        val task2 = tasksDAO.find(4)
        val tag = tagDAO.find(1)

        taksTagsDAO.removerTag(task2!!, tag!!)

        val tasksTag = taksTagsDAO.findTasksTag(tag)

        Log.d("result", tasksTag.toString())
    }
}
