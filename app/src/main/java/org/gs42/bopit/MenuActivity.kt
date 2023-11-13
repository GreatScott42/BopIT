package org.gs42.bopit

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView

class MenuActivity : AppCompatActivity() {
    private var background: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        setSupportActionBar(findViewById(R.id.toolbar))

        background = MediaPlayer.create(this, R.raw.background)
        background?.start()
        background?.isLooping=true;
        background?.setVolume(1f,1f)


        var text: TextView = findViewById(R.id.textView4)
        text.text = "Puntaje mas alto: " + PreferenceManager.getDefaultSharedPreferences(this).getInt("maxP",0)

        var button: Button = findViewById(R.id.button2)

        button.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        background?.release()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.preferencias -> {
                val intentPreferences = Intent(this, PreferencesActivity::class.java)
                startActivity(intentPreferences)
                return true
            }
            R.id.acerca -> {
                val intentPreferences = Intent(this, AboutActivity::class.java)
                startActivity(intentPreferences)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}