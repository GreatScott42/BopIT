package org.gs42.bopit

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Button
import androidx.core.view.MotionEventCompat
import android.view.GestureDetector
import androidx.core.view.GestureDetectorCompat

class MainActivity : AppCompatActivity() {

    private lateinit var mDetector: GestureDetectorCompat


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDetector = GestureDetectorCompat(this, MyGestureListener(this))
        //mediaPlayer?.start() // no need to call prepare(); create() does that for you
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        mDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }
    private class MyGestureListener(private val context: Context) : GestureDetector.SimpleOnGestureListener() {

        override fun onFling(
            event1: MotionEvent?,
            event2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            //Log.d("B", "onFling: $event1 $event2")
            val intent = Intent(context, SoundsActivity::class.java)
            context.startActivity(intent)

            return true
        }
    }

}