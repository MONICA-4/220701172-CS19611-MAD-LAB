package com.example.eventbookingapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

data class Booking(
    val id: Int,
    val event: String,
    val people: Int,
    val date: String,
    val time: String,
    val total: Int
)

class DBHelper(context: Context) : SQLiteOpenHelper(context, "Events.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE bookings (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "event TEXT, people INTEGER, date TEXT, time TEXT, total INTEGER)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS bookings")
        onCreate(db)
    }

    fun insertBooking(event: String, people: Int, date: String, time: String, total: Int) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("event", event)
            put("people", people)
            put("date", date)
            put("time", time)
            put("total", total)
        }
        db.insert("bookings", null, values)
    }

    fun getAllBookings(): List<Booking> {
        val bookingList = mutableListOf<Booking>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM bookings", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                val event = cursor.getString(cursor.getColumnIndexOrThrow("event"))
                val people = cursor.getInt(cursor.getColumnIndexOrThrow("people"))
                val date = cursor.getString(cursor.getColumnIndexOrThrow("date"))
                val time = cursor.getString(cursor.getColumnIndexOrThrow("time"))
                val total = cursor.getInt(cursor.getColumnIndexOrThrow("total"))

                bookingList.add(Booking(id, event, people, date, time, total))
            } while (cursor.moveToNext())
        }

        cursor.close()
        return bookingList
    }

    fun deleteBooking(id: Int): Boolean {
        val db = writableDatabase
        val result = db.delete("bookings", "id = ?", arrayOf(id.toString()))
        return result > 0
    }
}
