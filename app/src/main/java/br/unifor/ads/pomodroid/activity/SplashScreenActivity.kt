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

            val sharedPreferences = getSharedPreferences("dadosUser", 0)
            val is_loged = sharedPreferences.getBoolean("is_loged", false)


            if (is_loged){
                val it = Intent(SplashScreenActivity@this, MainActivity::class.java)
                startActivity(it)
                finish()
            } else {
                val it = Intent(SplashScreenActivity@this ,LoginActivity::class.java )
                startActivity(it)
                finish()
            }


        }, 3000)

    }

}
