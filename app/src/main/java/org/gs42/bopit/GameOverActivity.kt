package org.gs42.bopit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.TextView
import org.w3c.dom.Text

class GameOverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)

        var t3: TextView = findViewById(R.id.textView3)
        var puntaje = intent.extras
        if (puntaje != null) {
            val valorRecibido = puntaje.getInt("puntaje")
            if(valorRecibido>PreferenceManager.getDefaultSharedPreferences(this).getInt("maxP",0)){
                Log.d("NUEVO ALTO PÙNTAJE","EPICO")
                val editor = PreferenceManager.getDefaultSharedPreferences(this).edit()

                editor.putInt("maxP", valorRecibido)

                editor.apply()
                t3.text="NUEVO PUNTAJE MAS ALTO: "+valorRecibido


            }else{
                t3.text="JUEGO TERMINADO, PUNTAJE: "+valorRecibido+" PUNTAJE MAS ALTO: "+PreferenceManager.getDefaultSharedPreferences(this).getInt("maxP",0)
            }
        }

    }
}