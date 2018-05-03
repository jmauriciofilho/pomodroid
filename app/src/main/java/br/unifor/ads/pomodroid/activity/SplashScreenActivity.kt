package br.unifor.ads.pomodroid.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import br.unifor.ads.pomodroid.R

class SplashScreenActivity : AppCompatActivity() {

    val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        handler.postDelayed({

            val it = Intent(SplashScreenActivity@this ,LoginActivity::class.java )
            startActivity(it)
            finish()

        }, 3000);

    }

}
