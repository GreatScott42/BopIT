package org.gs42.bopit

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mediaPlayer: MediaPlayer? = MediaPlayer.create(this, R.raw.sound)
        mediaPlayer?.start() // no need to call prepare(); create() does that for you

        var boton = findViewById<Button>(R.id.button)
        boton.setOnClickListener{
            mediaPlayer?.start() // no need to call prepare(); create() does that for you
        }
    }
}