package br.unifor.ads.pomodroid.dao

import android.content.ContentValues
import android.content.Context
import br.unifor.ads.pomodroid.entity.Task
import br.unifor.ads.pomodroid.entity.TaskList
import br.unifor.ads.pomodroid.util.notEquals

class TaskDAO(val context : Context){

    private val TABLE_NAME = "tasks"
    private val TASK_NAME_FIELD = "name"
    private val TASK_DESCRIPTION_FIELD = "description"
    private val TASK_FINISHED_FIELD = "finished"
    private val TASK_ESTIMATEPOMODORO_FIELD = "estimatedPomodoro"
    private val TASK_TOTALPOMODORO_FIELD = "totalPomodoro"
    private val TASK_TASKLIST_FIELD = "taskList"

    private val db = PomodroidHelper(context).writableDatabase

    fun insert(task: Task):Boolean{

        val values = ContentValues()
        values.put(TASK_NAME_FIELD, task.name)
        values.put(TASK_DESCRIPTION_FIELD, task.description)
        values.put(TASK_FINISHED_FIELD, task.finished)
        values.put(TASK_ESTIMATEPOMODORO_FIELD, task.estimatedPomodoro)
        values.put(TASK_TOTALPOMODORO_FIELD, task.totalPomodoro)

        val id = db.insert(TABLE_NAME, null, values )

        return id.notEquals(-1)

    }

    fun update(task: Task):Boolean{

        val values = ContentValues()
        values.put(TASK_NAME_FIELD, task.name)
        values.put(TASK_DESCRIPTION_FIELD, task.description)
        values.put(TASK_FINISHED_FIELD, task.finished)
        values.put(TASK_ESTIMATEPOMODORO_FIELD, task.estimatedPomodoro)
        values.put(TASK_TOTALPOMODORO_FIELD, task.totalPomodoro)

        val rowsAffected = db.update(TABLE_NAME, values, "_id = ?", arrayOf(task.id.toString()))

        return rowsAffected.notEquals(0)

    }

    fun delete(task: Task):Boolean{

        val rowsAffected = db.delete(TABLE_NAME, "_id = ?", arrayOf(task.id.toString()))
        return rowsAffected.notEquals(0)
    }

    fun find(id: Long):Task?{

        var task:Task? = null
        val cursor = db.query(TABLE_NAME, null,"_id = ?", arrayOf(id.toString()), null, null, null)

        if(cursor.count > 0){

            cursor.moveToFirst()
            val id = cursor.getLong(cursor.getColumnIndex("_id"))
            val name = cursor.getString(cursor.getColumnIndex("name"))
            val description = cursor.getString(cursor.getColumnIndex("description"))
            val finished = cursor.getInt(cursor.getColumnIndex("finished")) == 1
            val estimatePomodoro = cursor.getInt(cursor.getColumnIndex("estimatedPomodoro"))
            val totalPomodoro = cursor.getInt(cursor.getColumnIndex("totalPomodoro"))

            val taskListId = cursor.getInt(cursor.getColumnIndex("taskList"))
            val taskListDAO = TaskListDAO(context)
            val taskList = taskListDAO.find(taskListId)

            task = Task(id, name, description, finished, estimatePomodoro, totalPomodoro, taskList!!)

        }

        return task

    }

    fun findAll():List<Task>{

        var tasks = ArrayList<Task>()
        val cursor = db.query(TABLE_NAME, null,null, null, null, null, "_id ASC")

        if(cursor.count > 0){

            cursor.moveToFirst()

            do {

                val id = cursor.getLong(cursor.getColumnIndex("_id"))
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val description = cursor.getString(cursor.getColumnIndex("description"))
                val finished = cursor.getInt(cursor.getColumnIndex("finished")) == 1
                val estimatePomodoro = cursor.getInt(cursor.getColumnIndex("estimatedPomodoro"))
                val totalPomodoro = cursor.getInt(cursor.getColumnIndex("totalPomodoro"))

                val taskListId = cursor.getInt(cursor.getColumnIndex("taskList"))
                val taskListDAO = TaskListDAO(context)
                val taskList = taskListDAO.find(taskListId)

                tasks.add(Task(id, name, description, finished, estimatePomodoro, totalPomodoro, taskList!!))

            } while (cursor.moveToNext())

        }

        return tasks

    }
}