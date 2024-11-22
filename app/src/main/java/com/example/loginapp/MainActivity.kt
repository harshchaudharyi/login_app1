package com.example.loginapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


    class MainActivity : AppCompatActivity() {

        private lateinit var dbHelper: DatabaseHelper
        private lateinit var nameinput: EditText
        private lateinit var ageinput: EditText
        private lateinit var btn1: Button

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            // Initialize views
            nameinput = findViewById(R.id.nameinput)
            ageinput = findViewById(R.id.ageinput)
            btn1 = findViewById(R.id.btn1)

            // Initialize DatabaseHelper
            dbHelper = DatabaseHelper(this)

            // Handle the submit button click
            btn1.setOnClickListener {
                val name = nameinput.text.toString()
                val ageString = ageinput.text.toString()

                if (name.isNotEmpty() && ageString.isNotEmpty()) {
                    val age = ageString.toIntOrNull()

                    if (age != null) {
                        // Insert user data into database
                        val result = dbHelper.insertUser(name, age)

                        if (result != -1L) {
                            // Successfully inserted the data
                            Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Error saving data", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, "Please enter a valid age", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Name and Age cannot be empty", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

