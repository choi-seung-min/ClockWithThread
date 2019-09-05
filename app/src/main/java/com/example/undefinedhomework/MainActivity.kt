package com.example.undefinedhomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var hour: String
    lateinit var minute: String
    lateinit var second: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val handler = TimeHandler()
        timeCheck(handler)
    }

    fun timeCheck(handler: TimeHandler){
        val thread = Thread(Runnable {
            while (true){
                try {
                    hour = SimpleDateFormat("hh", Locale.KOREA).format(Date())
                    minute = SimpleDateFormat("mm", Locale.KOREA).format(Date())
                    second = SimpleDateFormat("ss", Locale.KOREA).format(Date())
                    val message = handler.obtainMessage() as Message
                    handler.sendMessage(message)
                    Thread.sleep(1000)
                } catch (e: InterruptedException){}
            }
        })
        thread.start()
    }

    inner class TimeHandler: Handler(){
        override fun handleMessage(msg: Message?) {
            tv_hour.text = hour
            tv_minute.text = minute
            tv_second.text = second
        }
    }
}