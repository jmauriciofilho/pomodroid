package br.unifor.ads.pomodroid.dao

import android.content.ContentValues
import android.content.Context
import br.unifor.ads.pomodroid.entity.Tag
import br.unifor.ads.pomodroid.util.notEquals

class TagDAO(context: Context) {

    private val TABLE_NAME = "tags"
    private val TAG_NAME_FIELD = "name"
    private val TAG_COLOR_FIELD = "color"

    private val db = PomodroidHelper(context).writableDatabase

    fun insert(tag: Tag): Boolean {

        val values = ContentValues()
        values.put(TAG_NAME_FIELD, tag.name)
        values.put(TAG_COLOR_FIELD, tag.color)

        val id = db.insert(TABLE_NAME, null, values)

        return id.notEquals(-1)

    }

    fun update(tag: Tag): Boolean {

        val values = ContentValues()
        values.put(TAG_NAME_FIELD, tag.name)
        values.put(TAG_COLOR_FIELD, tag.color)

        val rowsAffected = db.update(TABLE_NAME, values, "_id = ?", arrayOf(tag.id.toString()))

        return rowsAffected.notEquals(0)

    }

    fun delete(tag: Tag): Boolean {

        val rowsAffected = db.delete(TABLE_NAME, "_id = ?", arrayOf(tag.id.toString()))
        return rowsAffected.notEquals(0)
    }

    fun find(id: Int): Tag? {

        var tag: Tag? = null
        val cursor = db.query(TABLE_NAME, null, "_id = ?", arrayOf(id.toString()), null, null, null)

        if (cursor.count > 0) {

            cursor.moveToFirst()
            val id = cursor.getInt(cursor.getColumnIndex("_id"))
            val name = cursor.getString(cursor.getColumnIndex("name"))
            val color = cursor.getInt(cursor.getColumnIndex("color"))

            tag = Tag(id, name, color)

        }

        return tag

    }

    fun findAll(): List<Tag> {

        var tags = ArrayList<Tag>()
        val cursor = db.query(TABLE_NAME, null, null, null, null, null, "_id ASC")

        if (cursor.count > 0) {

            cursor.moveToFirst()

            do {

                val id = cursor.getInt(cursor.getColumnIndex("_id"))
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val color = cursor.getInt(cursor.getColumnIndex("color"))

                tags.add(Tag(id, name, color))

            } while (cursor.moveToNext())

        }

        return tags

    }
}