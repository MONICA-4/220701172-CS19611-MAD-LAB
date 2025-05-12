package com.example.eventbookingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val events = listOf(
            Event(1, "Concert", R.drawable.concert, 500),
            Event(2, "Workshop", R.drawable.workshop, 300),
            Event(3, "Exhibition", R.drawable.exhibition, 20),
            Event(4, "match", R.drawable.match, 700),

        )

        adapter = EventAdapter(events) { selectedEvent ->
            val intent = Intent(this, BookingActivity::class.java)
            intent.putExtra("event_name", selectedEvent.name)
            intent.putExtra("event_cost", selectedEvent.costPerPerson)
            startActivity(intent)
        }

        recyclerView.adapter = adapter

        // New: Handle View Bookings button
        val viewBookingsBtn = findViewById<Button>(R.id.viewBookingsBtn)
        viewBookingsBtn.setOnClickListener {
            val intent = Intent(this, ViewBookingsActivity::class.java)
            startActivity(intent)
        }
    }
}
