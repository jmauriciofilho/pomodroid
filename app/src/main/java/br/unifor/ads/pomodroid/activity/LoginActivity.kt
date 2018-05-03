package br.unifor.ads.pomodroid.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import br.unifor.ads.pomodroid.R

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mLoginRegister: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mLoginRegister = findViewById(R.id.login_textview_register)
        mLoginRegister.setOnClickListener(this)
    }

    override fun onClick(view: View) {

        when(view.id){

            R.id.login_textview_register ->{

                val it = Intent(this, RegisterFormActivity::class.java)
                startActivity(it)

            }

        }

    }

}
