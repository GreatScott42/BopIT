package org.gs42.bopit

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class SoundsActivity : AppCompatActivity() {
    private var background: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sounds)


        var win: MediaPlayer? = MediaPlayer.create(this, R.raw.win)
        var gameover: MediaPlayer? = MediaPlayer.create(this, R.raw.gameover)
        background = MediaPlayer.create(this, R.raw.background)
        background?.start()
        background?.isLooping=true;
        background?.setVolume(1f,1f)

        var boton = findViewById<Button>(R.id.button)
        boton.setOnClickListener{
            win?.start() // no need to call prepare(); create() does that for you
        }
        var boton2 = findViewById<Button>(R.id.button3)
        boton2.setOnClickListener{
            gameover?.start() // no need to call prepare(); create() does that for you
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        background?.release()
    }
}