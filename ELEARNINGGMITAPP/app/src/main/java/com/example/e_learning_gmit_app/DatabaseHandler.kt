package com.example.e_learning_gmit_app


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context) :
        SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    // object dataabse holdign user details
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "UserDatabase"

        private val TABLE_CONTACTS = "UserTable"

        private val KEY_ID = "_id"
        private val KEY_NAME = "name"
        private val KEY_EMAIL = "email"
    }

    // creates a  simple SQLite db
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT" + ")")
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_CONTACTS")
        onCreate(db)
    }

    // method adds a user to sqllite db
    fun addUser(use: userModelClass): Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, use.name)
        contentValues.put(KEY_EMAIL, use.email)

        val success = db.insert(TABLE_CONTACTS, null, contentValues)

        db.close() // Closing database connection
        return success
    }

    //Method to read the records from database in form of ArrayList
    fun viewUser(): ArrayList<userModelClass> {

        val empList: ArrayList<userModelClass> = ArrayList<userModelClass>()

        // Query to select all the records from the table.
        val selectQuery = "SELECT  * FROM $TABLE_CONTACTS"

        val db = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)

            // break point catch erros
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        // instance variable
        var id: Int
        var name: String
        var email: String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                name = cursor.getString(cursor.getColumnIndex(KEY_NAME))
                email = cursor.getString(cursor.getColumnIndex(KEY_EMAIL))

                val emp = userModelClass(id = id, name = name, email = email)
                empList.add(emp)

            } while (cursor.moveToNext())
        }
        return empList
    }


    // function to update user information
    fun updateUser(emp: userModelClass): Int {

        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_NAME, emp.name)
        contentValues.put(KEY_EMAIL, emp.email)

        // Updating Row
        val success = db.update(TABLE_CONTACTS, contentValues, KEY_ID + "=" + emp.id, null)

        // Closing database connection
        db.close()
        return success
    }

    // function to delete a record from the database
    fun deleteUser(emp: userModelClass): Int {

        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_ID, emp.id) // EmpModelClass id
        // Deleting Row meesage
        val success = db.delete(TABLE_CONTACTS, KEY_ID + "=" + emp.id, null)

        // Closing database connection
        db.close()
        return success
    }
}