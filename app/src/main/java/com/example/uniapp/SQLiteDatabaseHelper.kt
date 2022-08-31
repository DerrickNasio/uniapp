package com.example.uniapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * A helper class for SQLite Database management. It contains functions for easily creating tables,
 * together with inserting, updating, deleting, and counting the data therein.
 *
 * You create a subclass of [SQLiteDatabaseHelper] implementing
 * [onCreate] to init the database file, and
 * [onUpgrade] for database versioning and migration data.
 */
class SQLiteDatabaseHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        val query =
            ("CREATE TABLE if not exists $TABLE_NAME ($KEY_ID INTEGER PRIMARY KEY autoincrement," +
                    "$KEY_USERNAME TEXT,$KEY_PASSWORD TEXT)")
        sqLiteDatabase.execSQL(query)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        try {
            if (oldVersion < DATABASE_VERSION) {
                sqLiteDatabase.execSQL("DROP TABLE if exists $TABLE_NAME")
                onCreate(sqLiteDatabase)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun insertUser(queryValues: User) {
        val database = this.writableDatabase
        val values = ContentValues()
        values.put("username", queryValues.username)
        values.put("password", queryValues.password)
        queryValues.userId = database.insert(TABLE_NAME, null, values)
        database.close()
    }

    fun updateUserPassword(queryValues: User): Int {
        val database = this.writableDatabase
        val values = ContentValues()
        values.put("username", queryValues.username)
        values.put("password", queryValues.password)
        queryValues.userId = database.insert(TABLE_NAME, null, values)
        database.close()
        return database.update(
            TABLE_NAME,
            values,
            "userId = ?",
            arrayOf(java.lang.String.valueOf(queryValues.userId))
        )
    }

    fun getUser(username: String): User {
        val query =
            "Select $KEY_ID, $KEY_PASSWORD from $TABLE_NAME where $KEY_USERNAME ='$username'"
        val myUser = User(0, username, "")
        val database = this.readableDatabase
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                myUser.userId = cursor.getLong(0)
                myUser.password = cursor.getString(1)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return myUser
    }

    /**
     * Create a companion object to hold our static fields.
     * A companion object is an object that is common to all instances of a given class.
     */
    companion object {
        private const val DATABASE_NAME: String = "usersDatabase.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_NAME = "logins_table"

        const val KEY_ID = "id"
        const val KEY_USERNAME = "username"
        const val KEY_PASSWORD = "password"
    }
}