package com.example.undefinedhomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var hour: String
    lateinit var minute: String
    lateinit var second: String

    lateinit var lpHour: ConstraintLayout.LayoutParams
    lateinit var lpMinute: ConstraintLayout.LayoutParams
    lateinit var lpSecond: ConstraintLayout.LayoutParams

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lpHour = tv_hour.layoutParams as ConstraintLayout.LayoutParams
        lpMinute = tv_minute.layoutParams as ConstraintLayout.LayoutParams
        lpSecond = tv_second.layoutParams as ConstraintLayout.LayoutParams

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
                    lpHour.circleAngle = 360/12*hour.toFloat()
                    lpMinute.circleAngle = 360/60*minute.toFloat()
                    lpSecond.circleAngle = 360/60*second.toFloat()
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
            tv_hour.layoutParams = lpHour
            tv_minute.layoutParams = lpMinute
            tv_second.layoutParams = lpSecond
        }
    }
}