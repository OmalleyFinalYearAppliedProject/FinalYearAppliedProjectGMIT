package com.example.e_learning_gmit_app.sqlitedb


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.example.e_learning_gmit_app.models.calenderModelClass

class DatabaseHandler(context: Context) :
        SQLiteOpenHelper(context,
            DATABASE_NAME, null,
            DATABASE_VERSION
        ) {


    // object  holding  event  details
    companion object {
        // instance variables
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "EventDatabase"
        private val TABLE_EVENTS = "EventTable"
        private val KEY_ID = "_id"
        private val KEY_NAME = "name"
        private val KEY_DATE = "date"
    }

    // creates a  simple  evrnt SQLite db
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_EVENTS_TABLE = ("CREATE TABLE " + TABLE_EVENTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_DATE + " TEXT" + ")")
        db?.execSQL(CREATE_EVENTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_EVENTS")
        onCreate(db)
    }

    // method adds an event  to sqllite db
    fun addEvent(use: calenderModelClass): Long {
        val db = this.writableDatabase

            // append to
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, use.name)
        contentValues.put(KEY_DATE, use.date)

        // insert query when correct request
        val correct = db.insert(TABLE_EVENTS, null, contentValues)

        db.close() // Closing database connection
        return correct
    }

    //Method to read the records from database in form of ArrayList
    fun viewEvent(): ArrayList<calenderModelClass> {

        // hold events in an array
        val eventList: ArrayList<calenderModelClass> = ArrayList<calenderModelClass>()

        // Query to select all the records from the table.
        val selectQuery = "SELECT  * FROM $TABLE_EVENTS"

        val db = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)

            // break point catch errors
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        // instance variable
        var id: Int
        var name: String
        var date: String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                name = cursor.getString(cursor.getColumnIndex(KEY_NAME))
                date = cursor.getString(cursor.getColumnIndex(KEY_DATE))

                val evCal = calenderModelClass(
                    id = id,
                    name = name,
                    date = date
                )
                eventList.add(evCal)

            } while (cursor.moveToNext())
        }
        return eventList
    }


    // function to update event information
    fun updateEvent(evCal: calenderModelClass): Int {

        // instance variables
        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_NAME, evCal.name)
        contentValues.put(KEY_DATE, evCal.date)

        // Update  Row
        val correct = db.update(TABLE_EVENTS, contentValues, KEY_ID + "=" + evCal.id, null)

        // Closing database connection
        db.close()
        return correct
    }

    // function to delete an event  from the database
    fun deleteEvent(evCal: calenderModelClass): Int {

        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_ID, evCal.id) // calender Model id
        // Deleting Row meesage
        val correct = db.delete(TABLE_EVENTS, KEY_ID + "=" + evCal.id, null)

        // Closing database connection
        db.close()
        return correct
    }
}