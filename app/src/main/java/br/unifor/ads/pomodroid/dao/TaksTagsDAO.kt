package br.unifor.ads.pomodroid.dao

import android.content.ContentValues
import android.content.Context
import br.unifor.ads.pomodroid.entity.Tag
import br.unifor.ads.pomodroid.entity.TaksTags
import br.unifor.ads.pomodroid.entity.Task
import br.unifor.ads.pomodroid.util.notEquals

class TaksTagsDAO (val context: Context){

    private val TABLE_NAME = "tasks_tags"
    private val TASKSTAGS_TASK_FIELD = "task"
    private val TASKSTAGS_TAG_FIELD = "tag"

    private val db = PomodroidHelper(context).writableDatabase

    fun atribuirTag(task: Task, tag: Tag) :Boolean{

        val values = ContentValues()
        values.put(TASKSTAGS_TASK_FIELD, task.id)
        values.put(TASKSTAGS_TAG_FIELD, tag.id)

        val id = db.insert(TABLE_NAME, null, values)

        return id.notEquals(-1)

    }

    fun removerTag(task: Task, tag: Tag) :Boolean{
        val rowsAffected = db.delete(TABLE_NAME, "task = ? and tag = ?", arrayOf(task.id.toString(), tag.id.toString()))

        return rowsAffected.notEquals(0)
    }

    fun findTasksTag(tag: Tag) :List<Task>{

        var tasksTag = ArrayList<Task>()

        val cursor = db.query(TABLE_NAME, null, "tag = ?", arrayOf(tag.id.toString()), null, null, null)

        if (cursor.count > 0){
            cursor.moveToFirst()

            do {

                val taskId = cursor.getLong(cursor.getColumnIndex("task"))

                val taskDAO = TaskDAO(context)
                val task = taskDAO.find(taskId)

                tasksTag.add(task!!)

            }while (cursor.moveToNext())
        }

        return tasksTag

    }
}