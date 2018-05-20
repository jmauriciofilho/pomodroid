package br.unifor.ads.pomodroid.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import br.unifor.ads.pomodroid.entity.User
import br.unifor.ads.pomodroid.util.notEquals

class UserDAO(val context:Context) {

    private val TABLE_NAME = "users"
    private val USER_NAME_FIELD = "name"
    private val USER_EMAIL_FIELD = "email"
    private val USER_PASSWORD_FIELD = "password"

    private val db = PomodroidHelper(context).writableDatabase

    fun insert(user: User):Boolean{

        val values = ContentValues()
        values.put(USER_NAME_FIELD, user.name)
        values.put(USER_EMAIL_FIELD, user.email)
        values.put(USER_PASSWORD_FIELD, user.password)
        values.put("_id", user.id)

        val id = db.insert(TABLE_NAME, null, values )

        return id.notEquals(-1)

    }

    fun update(user: User):Boolean{

        val values = ContentValues()
        values.put(USER_NAME_FIELD, user.name)
        values.put(USER_EMAIL_FIELD, user.email)
        values.put(USER_PASSWORD_FIELD, user.password)
        values.put("_id", user.id)

        val rowsAffected = db.update(TABLE_NAME, values, "_id = ?", arrayOf(user.id.toString()))

        return rowsAffected.notEquals(0)

    }

    fun delete(user: User):Boolean{

        val rowsAffected = db.delete(TABLE_NAME, "_id = ?", arrayOf(user.id.toString()))
        return rowsAffected.notEquals(0)
    }

    fun find(id: Int):User?{

        var user:User? = null
        val cursor = db.query(TABLE_NAME, null,"_id = ?", arrayOf(id.toString()), null, null, null)

        if(cursor.count > 0){

            cursor.moveToFirst()
            val id = cursor.getLong(cursor.getColumnIndex("_id"))
            val name = cursor.getString(cursor.getColumnIndex("name"))
            val email = cursor.getString(cursor.getColumnIndex("email"))
            val password = cursor.getString(cursor.getColumnIndex("password"))

            user = User(id, name, email, password)

        }

        return user

    }

    fun findByEmail(email:String):User?{

        var user:User? = null
        val cursor = db.query(TABLE_NAME, null,"email = ?", arrayOf(email), null, null, null)

        if(cursor.count > 0){

            cursor.moveToFirst()
            val id = cursor.getLong(cursor.getColumnIndex("_id"))
            val name = cursor.getString(cursor.getColumnIndex("name"))
            val email = cursor.getString(cursor.getColumnIndex("email"))
            val password = cursor.getString(cursor.getColumnIndex("password"))

            user = User(id, name, email, password)

        }

        return user

    }

    fun findAll():List<User>{

        var users = ArrayList<User>()
        val cursor = db.query(TABLE_NAME, null,null, null, null, null, "_id ASC")

        if(cursor.count > 0){

            cursor.moveToFirst()

            do {

                val id = cursor.getLong(cursor.getColumnIndex("_id"))
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val email = cursor.getString(cursor.getColumnIndex("email"))
                val password = cursor.getString(cursor.getColumnIndex("password"))

                users.add(User(id, name, email, password))

            } while (cursor.moveToNext())

        }

        return users

    }

}