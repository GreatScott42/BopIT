package org.gs42.bopit

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
    private var background: MediaPlayer? = null
    private lateinit var mDetector: GestureDetectorCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDetector = GestureDetectorCompat(this, MyGestureListener())

        var mediaPlayer: MediaPlayer? = MediaPlayer.create(this, R.raw.sound)
        background = MediaPlayer.create(this, R.raw.background)
        background?.start()
        background?.isLooping=true;
        background?.setVolume(1f,1f)

        //mediaPlayer?.start() // no need to call prepare(); create() does that for you

        var boton = findViewById<Button>(R.id.button)
        boton.setOnClickListener{
            mediaPlayer?.start() // no need to call prepare(); create() does that for you
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        background?.release()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        mDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }
    private class MyGestureListener : GestureDetector.SimpleOnGestureListener() {

        override fun onFling(
            event1: MotionEvent?,
            event2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            Log.d("B", "onFling: $event1 $event2")
            return true
        }
    }
}