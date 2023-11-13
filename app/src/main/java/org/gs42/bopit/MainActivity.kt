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
import android.os.Handler
import android.os.Looper
import android.preference.PreferenceManager
import android.util.Log
import android.view.MotionEvent
import android.widget.Button
import androidx.core.view.MotionEventCompat
import android.view.GestureDetector
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.view.GestureDetectorCompat

object vars {
    var puntaje = 0
    var tarea = 0
    var tiempoRespuesta = "5000"

}

class MainActivity : AppCompatActivity(), SensorEventListener, SensorListener {

    private lateinit var mDetector: GestureDetectorCompat
    var l: RelativeLayout? = null
    var t: TextView? = null
    var t2: TextView?=null


    @SuppressLint("MissingInflatedId", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        vars.puntaje = 0
        vars.tiempoRespuesta =
            PreferenceManager.getDefaultSharedPreferences(this).getString("wait_time", "5000").toString()

        t = findViewById(R.id.textView)
        t2 = findViewById(R.id.textView2)
        t2?.text="puntaje: "+vars.puntaje.toString()
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
        l = findViewById(R.layout.activity_main)
        l?.setBackgroundColor(R.color.black)
        mDetector = GestureDetectorCompat(this, MyGestureListener(this))

        t?.text = (0..5).random().toString()
        Log.d("this", "wtf");
        //mediaPlayer?.start() // no need to call prepare(); create() does that for you
        var dif = PreferenceManager.getDefaultSharedPreferences(this@MainActivity).getString("difficult","10")?.toInt()
        //handler
        val handler = Handler(Looper.getMainLooper())
        val runnable = object : Runnable {
            override fun run() {

                if(vars.puntaje/ dif!! ==1){

                    vars.tiempoRespuesta="4000"
                }else if(vars.puntaje/ dif!! ==2){
                    vars.tiempoRespuesta="3000"
                }else if(vars.puntaje/dif==3){
                    vars.tiempoRespuesta="1000"
                }
                vars.tarea = (0..2).random()
                if(vars.tarea==0){
                    t?.text = "Desliza"
                }else if(vars.tarea==1){
                    t?.text = "Agita"
                }else if(vars.tarea==2){
                    t?.text = "Toca dos veces"
                }
                t2?.text="puntaje: "+vars.puntaje.toString()
                if (vars.tiempoRespuesta != null) {
                    handler.postDelayed(this, vars.tiempoRespuesta.toLong())
                }
            }
        }
        handler.post(runnable)

    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        mDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }
    class MyGestureListener(private val context: Context) : GestureDetector.SimpleOnGestureListener() {
        var win: MediaPlayer? = MediaPlayer.create(context, R.raw.win)
        fun gameOver(){
            val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
            Log.d("highScore",sharedPref.getInt("maxP",0).toString()+"PuntajeActual: "+vars.puntaje)
            if(sharedPref.getInt("maxP",0)<vars.puntaje){
                Log.d("NUEVO ALTO PÙNTAJE","EPICO")

            }
            val intent = Intent(context, GameOverActivity::class.java)
            intent.putExtra("puntaje", vars.puntaje)
            context.startActivity(intent)

        }


        override fun onDoubleTap(e: MotionEvent): Boolean {

            if(vars.tarea==2){
                vars.puntaje = vars.puntaje.plus(1)

                win?.start()
            }else if(vars.tarea!=2){
                var gameover: MediaPlayer? = MediaPlayer.create(context, R.raw.gameover)
                gameover?.start()
                //val intent = Intent(context, GameOverActivity::class.java)
                gameOver()
                //context.startActivity(intent)
            }


            return true
        }

        override fun onFling(
            event1: MotionEvent?,
            event2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {

            if(vars.tarea==0){
                vars.puntaje = vars.puntaje.plus(1)

                win?.start() // no need to call prepare(); create() does that for you
                //Log.d("B", "PUNTJAE+1")
            }else if(vars.tarea!=0){
                var gameover: MediaPlayer? = MediaPlayer.create(context, R.raw.gameover)
                gameover?.start()
                //val intent = Intent(context, GameOverActivity::class.java)
                gameOver()
                //context.startActivity(intent)
            }

            //val intent = Intent(context, SoundsActivity::class.java)
            //context.startActivity(intent)

            return true
        }
    }
    fun gameOver(){
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        Log.d("highScore",sharedPref.getInt("maxP",0).toString()+"PuntajeActual: "+vars.puntaje)
        if(sharedPref.getInt("maxP",0)<vars.puntaje){
            Log.d("NUEVO ALTO PÙNTAJE","EPICO")

        }
        val intent = Intent(this, GameOverActivity::class.java)
        intent.putExtra("puntaje", vars.puntaje)
        startActivity(intent)

    }

    @SuppressLint("ResourceType")
    override fun onSensorChanged(event: SensorEvent?) {
        var win: MediaPlayer? = MediaPlayer.create(this, R.raw.win)

        if(event?.sensor?.type==Sensor.TYPE_ACCELEROMETER){

            //var t: TextView = findViewById(R.id.textView)
            //t.text=event.values[0].toString()

            if (event.values[0].toInt() >3 || event.values[0].toInt() < -3){
                if(vars.tarea==1){
                    vars.puntaje = vars.puntaje.plus(1)
                    //cambio de dificultdad

                    win?.start() // no need to call prepare(); create() does that for you
                }
                if(vars.tarea!=1){
                    var gameover: MediaPlayer? = MediaPlayer.create(this, R.raw.gameover)
                    gameover?.start()
                    gameOver()
                    finish()
                    //val intent = Intent(this, GameOverActivity::class.java)
                    //startActivity(intent)
                }
                Log.d("asdasda",event.values[0].toString())
                //var t: TextView = findViewById(R.id.textView)
                //t?.text ="AGITANDO"

            }else{
                //var t: TextView = findViewById(R.id.textView)
                //t?.text="CALMADO"
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