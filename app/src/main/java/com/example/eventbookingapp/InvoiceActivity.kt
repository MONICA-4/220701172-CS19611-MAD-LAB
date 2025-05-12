package com.example.eventbookingapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class InvoiceActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invoice)

        val event = intent.getStringExtra("event")
        val people = intent.getIntExtra("people", 0)
        val date = intent.getStringExtra("date")
        val time = intent.getStringExtra("time")
        val total = intent.getIntExtra("total", 0)

        val invoiceText = findViewById<TextView>(R.id.invoiceText)
        invoiceText.text = """
            ✅ Booking Successful!
            
            Event: $event
            People: $people
            Date: $date
            Time: $time
            Total Cost: Rs. $total
        """.trimIndent()

        val backBtn = findViewById<Button>(R.id.backHomeBtn)
        backBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }
}
