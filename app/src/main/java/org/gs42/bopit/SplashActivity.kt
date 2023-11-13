package org.gs42.bopit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.preference.PreferenceManager
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.widget.ImageView

class SplashActivity : AppCompatActivity() {

    val tagLog = "SplashActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var i = 0
        Log.d("a",i.toString())
        i++

        // duracion inicio
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val value = sharedPreferences.getString("splash_time", "1000")
        Log.d("a",i.toString())
        i++
        // Load the fade-in animation
        //val fadeIn = AnimationUtils.loadAnimation(applicationContext, R.anim.fadein)

        // Load the growth animation
        //val scaleUp = AnimationUtils.loadAnimation(applicationContext, R.anim.scaleup)

        val rot = AnimationUtils.loadAnimation(applicationContext, R.anim.rot)

        // Create an AnimationSet to combine both animations
        val animationSet = AnimationSet(true)
        //animationSet.addAnimation(fadeIn)
        //animationSet.addAnimation(scaleUp)
        animationSet.addAnimation(rot)
        Log.d("a",i.toString())
        i++

        val imageView = findViewById<ImageView>(R.id.logo)

        /*fadeIn.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                // Animation started
            }

            override fun onAnimationEnd(animation: Animation?) {
                // Animation ended; proceed to MainActivity
                Log.d(tagLog, "Splash animation ended. Navigating to MenuActivity.")
                val intent = Intent(this@SplashActivity, MenuActivity::class.java)
                startActivity(intent)
                finish() // Optional: Close the splash screen activity
            }

            override fun onAnimationRepeat(animation: Animation?) {
                // Animation repeated
            }
        })
        imageView.startAnimation(fadeIn)*/Log.d("a",i.toString())
        i++
        ///
        rot.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {}
            override fun onAnimationRepeat(animation: Animation?) {}
        })
        imageView.startAnimation(rot)

        val seconds = value?.toLong()
        val delayMillis = seconds!!.toLong()

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish() // Optional: Close the splash screen activity
        }, delayMillis)
    }
}