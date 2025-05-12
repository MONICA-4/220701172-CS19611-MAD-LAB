package com.example.eventbookingapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ViewBookingsActivity : AppCompatActivity() {

    private lateinit var bookingRecyclerView: RecyclerView
    private lateinit var bookingAdapter: BookingAdapter
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_bookings)

        dbHelper = DBHelper(this)
        bookingRecyclerView = findViewById(R.id.bookingRecyclerView)

        val bookings = dbHelper.getAllBookings()

        bookingAdapter = BookingAdapter(bookings) { booking ->
            deleteBooking(booking)
        }

        bookingRecyclerView.layoutManager = LinearLayoutManager(this)
        bookingRecyclerView.adapter = bookingAdapter
    }

    private fun deleteBooking(booking: Booking) {
        val isDeleted = dbHelper.deleteBooking(booking.id)
        if (isDeleted) {
            val updatedBookings = dbHelper.getAllBookings()
            bookingAdapter.updateData(updatedBookings)
            Toast.makeText(this, "Booking deleted successfully!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Failed to delete booking.", Toast.LENGTH_SHORT).show()
        }
    }
}
