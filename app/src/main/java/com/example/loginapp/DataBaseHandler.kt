package com.example.loginapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "UserDB"
        private const val DATABASE_VERSION = 1

        // Table and column names
        const val TABLE_USER = "user"
        const val COLUMN_NAME = "name"
        const val COLUMN_AGE = "age"
    }

    // Create the table
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = ("CREATE TABLE $TABLE_USER (" +
                "$COLUMN_NAME TEXT," +
                "$COLUMN_AGE INTEGER)"
                )
        db?.execSQL(CREATE_TABLE)
    }

    // Upgrade the database if needed (handle schema changes)
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        onCreate(db)
    }

    // Insert data into the user table
    fun insertUser(name: String, age: Int): Long {
        val db = this.writableDatabase
        val contentValues = android.content.ContentValues()
        contentValues.put(COLUMN_NAME, name)
        contentValues.put(COLUMN_AGE, age)

        // Insert data and return the row ID
        return db.insert(TABLE_USER, null, contentValues)
    }

}
