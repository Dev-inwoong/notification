package kr.ac.tuk.notificationstudty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var main_btn_start : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        main_btn_start = findViewById(R.id.main_btn_start)

        main_btn_start.setOnClickListener {
            val it = Intent(this, MyService::class.java)
            startForegroundService(it)
        }



    }
}