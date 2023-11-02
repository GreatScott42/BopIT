package org.gs42.bopit

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorListener
import android.hardware.SensorManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Button
import androidx.core.view.MotionEventCompat
import android.view.GestureDetector
import android.widget.RelativeLayout
import androidx.core.view.GestureDetectorCompat

class MainActivity : AppCompatActivity(), SensorEventListener, SensorListener {

    private lateinit var mDetector: GestureDetectorCompat
    var l: RelativeLayout? = null



    @SuppressLint("MissingInflatedId", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
        l = findViewById(R.layout.activity_main)
        l?.setBackgroundColor(R.color.black)
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

    @SuppressLint("ResourceType")
    override fun onSensorChanged(event: SensorEvent?) {

        if(event?.sensor?.type==Sensor.TYPE_ACCELEROMETER){

            if (event.values[0].toInt() >6){
                Log.d("asdasda",event.values[0].toString())


            }
            /*else{
                l?.setBackgroundColor(R.color.black)
                Log.d("blaaaaaaaaack",event.values[1].toString())
            }*/
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(sensor: Int, values: FloatArray?) {

    }

    override fun onAccuracyChanged(sensor: Int, accuracy: Int) {

    }


}