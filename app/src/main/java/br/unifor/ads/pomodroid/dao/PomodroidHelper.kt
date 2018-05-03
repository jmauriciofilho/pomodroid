package br.unifor.ads.pomodroid.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class PomodroidHelper(val context: Context) :
        SQLiteOpenHelper(context, "pomodroid_db", null, 1) {

    val CREATE_FILE_PATH = "database/create.sql"
    val UPDATE_FILE_PATH = "database/update.sql"

    override fun onCreate(db: SQLiteDatabase) {
        execSqlStatementsFromFile(CREATE_FILE_PATH, db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        execSqlStatementsFromFile(UPDATE_FILE_PATH, db)
        onCreate(db)
    }

    fun execSqlStatementsFromFile(path:String, db: SQLiteDatabase){
        context.assets.open(path)
                .reader()
                .readLines()
                .forEach { sql ->
                    db.execSQL(sql)
                }
    }

}