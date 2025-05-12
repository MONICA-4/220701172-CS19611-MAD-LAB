package com.example.eventbookingapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class BookingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        val eventName = intent.getStringExtra("event_name") ?: ""
        val costPerPerson = intent.getIntExtra("event_cost", 0)

        val eventTitle = findViewById<TextView>(R.id.eventTitle)
        val numPeople = findViewById<EditText>(R.id.numPeople)
        val dateInput = findViewById<EditText>(R.id.dateInput)
        val timeInput = findViewById<EditText>(R.id.timeInput)
        val bookBtn = findViewById<Button>(R.id.bookNowBtn)

        eventTitle.text = eventName

        bookBtn.setOnClickListener {
            val people = numPeople.text.toString().toIntOrNull() ?: 1
            val selectedDate = dateInput.text.toString()
            val selectedTime = timeInput.text.toString()
            val total = people * costPerPerson

            val db = DBHelper(this)
            db.insertBooking(eventName, people, selectedDate, selectedTime, total)

            val intent = Intent(this, InvoiceActivity::class.java)
            intent.putExtra("event", eventName)
            intent.putExtra("people", people)
            intent.putExtra("date", selectedDate)
            intent.putExtra("time", selectedTime)
            intent.putExtra("total", total)
            startActivity(intent)
        }
    }
}
