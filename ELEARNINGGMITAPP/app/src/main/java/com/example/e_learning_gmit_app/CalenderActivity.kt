package com.example.e_learning_gmit_app

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.dialog_update.*

class CalenderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calender)


        // method call adds user record to list
        btnAdd.setOnClickListener { view ->

            //call to add record to stack
            addRecord()
        }
        // method call to start data list in calender activity
        setupListofDataIntoRecyclerView()



        val actionbar = supportActionBar
        actionbar!!.title = "Dashboard"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)


    }
    // returns user to the last visited
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupListofDataIntoRecyclerView() {

        if (getItemsList().size > 0) {

            rvItemsList.visibility = View.VISIBLE
            tvNoRecordsAvailable.visibility = View.GONE

            rvItemsList.layoutManager = LinearLayoutManager(this)

            val itemAdapter = ItemAdapter(this, getItemsList())

            rvItemsList.adapter = itemAdapter
        } else {

            rvItemsList.visibility = View.GONE
            tvNoRecordsAvailable.visibility = View.VISIBLE
        }
    }

    /**
     * getItemsList()  used to get the Items List from the database table.
     */
    private fun getItemsList(): ArrayList<userModelClass> {
        //creating the instance of DatabaseHandler class
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        //calling the viewUser method of DatabaseHandler class to read the records
        val empList: ArrayList<userModelClass> = databaseHandler.viewUser()

        return empList
    }

    //Method for saving the user records in database
    private fun addRecord() {

        val name = etName.text.toString()
        val email = etEmailId.text.toString()

        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        if (!name.isEmpty() && !email.isEmpty()) {
            val status =
                databaseHandler.addUser(userModelClass(0, name, email))
            if (status > -1) {
                Toast.makeText(applicationContext, "Record saved", Toast.LENGTH_LONG).show()
                etName.text.clear()
                etEmailId.text.clear()

                setupListofDataIntoRecyclerView()
            }
        } else {
            Toast.makeText(
                applicationContext,
                "Event Name or Event Date  cannot be blank",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    /**
     * Method is used to show the Custom Dialog.
     */
    fun updateRecordDialog(userModelClass: userModelClass) {


        val updateDialog = Dialog(this, R.style.Theme_Dialog)
        updateDialog.setCancelable(false)


        updateDialog.setContentView(R.layout.dialog_update)

        updateDialog.etUpdateName.setText(userModelClass.name)
        updateDialog.etUpdateEmailId.setText(userModelClass.email)

        updateDialog.tvUpdate.setOnClickListener(View.OnClickListener {


            // user details cast to strings
            val name = updateDialog.etUpdateName.text.toString()
            val email = updateDialog.etUpdateEmailId.text.toString()

            val databaseHandler: DatabaseHandler = DatabaseHandler(this)

            if (!name.isEmpty() && !email.isEmpty()) {
                val status =
                    databaseHandler.updateUser(userModelClass(userModelClass.id, name, email))
                if (status > -1) {
                    Toast.makeText(applicationContext, "Record Updated.", Toast.LENGTH_LONG).show()

                    setupListofDataIntoRecyclerView()

                    updateDialog.dismiss() // Dialog will be dismissed
                }
            } else {

                //event message displayd to user screen
                Toast.makeText(
                    applicationContext,
                    "Event Name or Event Date cannot be blank",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
        updateDialog.tvCancel.setOnClickListener(View.OnClickListener {
            updateDialog.dismiss()
        })
        //Start the dialog and display it on screen.
        updateDialog.show()
    }

    /**
     * Method is used to show the Alert Dialog.
     */
    fun deleteRecordAlertDialog(empModelClass: userModelClass) {
        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
        builder.setTitle("Delete Event")

        //set message for alert dialog
        builder.setMessage("Are you sure you want to delete ${empModelClass.name}.")
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        builder.setPositiveButton("Yes") { dialogInterface, which ->

            //creating the instance of DatabaseHandler class
            val databaseHandler: DatabaseHandler = DatabaseHandler(this)
            //calling the deleteUser method of DatabaseHandler class to delete record


            val status = databaseHandler.deleteUser(userModelClass(empModelClass.id, "", ""))
            if (status > -1) {
                // event message displayed to user when successful
                Toast.makeText(
                    applicationContext,
                    "Event deleted successfully.",
                    Toast.LENGTH_LONG
                ).show()
                // displays list in a view in application
                setupListofDataIntoRecyclerView()
            }

            dialogInterface.dismiss() // Dialog will be dismissed
        }

        //performing negative action
        builder.setNegativeButton("No") { dialogInterface, which ->
            dialogInterface.dismiss() // Dialog will be dismissed
        }

        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties

        alertDialog.setCancelable(false) // Will not allow user to cancel after clicking on remaining screen area.
        alertDialog.show()  // show the dialog to UI
    }
}