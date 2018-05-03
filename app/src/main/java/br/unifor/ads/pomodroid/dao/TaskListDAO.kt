package br.unifor.ads.pomodroid.dao

import android.content.ContentValues
import android.content.Context
import br.unifor.ads.pomodroid.entity.TaskList
import br.unifor.ads.pomodroid.util.notEquals

class TaskListDAO(val context: Context){

    private val TABLE_NAME = "taskLists"
    private val TASKLIST_NAME_FIELD = "name"
    private val TASKLIST_DESCRIPTION_FIELD = "description"
    private val TASKLIST_USER_FIELD = "user"

    private val db = PomodroidHelper(context).writableDatabase

    fun insert(taskList: TaskList):Boolean{

        val values = ContentValues()
        values.put(TASKLIST_NAME_FIELD, taskList.name)
        values.put(TASKLIST_DESCRIPTION_FIELD, taskList.description)
        values.put(TASKLIST_USER_FIELD, taskList.user.id)

        val id = db.insert(TABLE_NAME, null, values )

        return id.notEquals(-1)

    }

    fun update(taskList: TaskList):Boolean{

        val values = ContentValues()
        values.put(TASKLIST_NAME_FIELD, taskList.name)
        values.put(TASKLIST_DESCRIPTION_FIELD, taskList.description)
        values.put(TASKLIST_USER_FIELD, taskList.user.id)

        val rowsAffected = db.update(TABLE_NAME, values, "_id = ?", arrayOf(taskList.id.toString()))

        return rowsAffected.notEquals(0)

    }

    fun delete(taskList: TaskList):Boolean{

        val rowsAffected = db.delete(TABLE_NAME, "_id = ?", arrayOf(taskList.id.toString()))
        return rowsAffected.notEquals(0)
    }

    fun find(id: Int):TaskList?{

        var taskList:TaskList? = null
        val cursor = db.query(TABLE_NAME, null,"_id = ?", arrayOf(id.toString()), null, null, null)

        if(cursor.count > 0){

            cursor.moveToFirst()
            val id = cursor.getInt(cursor.getColumnIndex("_id"))
            val name = cursor.getString(cursor.getColumnIndex("name"))
            val description = cursor.getString(cursor.getColumnIndex("description"))

            val userId = cursor.getInt(cursor.getColumnIndex("user"))
            val userDAO = UserDAO(context)
            val user = userDAO.find(userId)

            taskList = TaskList(id, name, description, user!!)

        }

        return taskList

    }

    fun findAll():List<TaskList>{

        var taskLists = ArrayList<TaskList>()
        val cursor = db.query(TABLE_NAME, null,null, null, null, null, "_id ASC")

        if(cursor.count > 0){

            cursor.moveToFirst()

            do {

                val id = cursor.getInt(cursor.getColumnIndex("_id"))
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val description = cursor.getString(cursor.getColumnIndex("description"))

                val userId = cursor.getInt(cursor.getColumnIndex("user"))
                val userDAO = UserDAO(context)
                val user = userDAO.find(userId)

                taskLists.add(TaskList(id, name, description, user!!))

            } while (cursor.moveToNext())

        }

        return taskLists

    }
}