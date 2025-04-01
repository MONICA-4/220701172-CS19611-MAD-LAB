package com.example.myapplication

import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val SMS_PERMISSION_CODE=101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val etPhoneNumber: EditText= findViewById(R.id.etPhoneNumber)
        val etMessage: EditText= findViewById(R.id.etMessage)
        val btSend : Button = findViewById(R.id.btSend)

        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
        {

            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.SEND_SMS), SMS_PERMISSION_CODE)
        }
        btSend.setOnClickListener {
            val phoneNumber=etPhoneNumber.text.toString()
            val message= etMessage.text.toString()
            val smsManager= SmsManager.getDefault()

            smsManager.sendTextMessage(phoneNumber,null,message,null,null)
            Toast.makeText(this,"sms sent successfully", Toast.LENGTH_LONG).show()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
