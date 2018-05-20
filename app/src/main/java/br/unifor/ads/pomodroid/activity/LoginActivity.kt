package br.unifor.ads.pomodroid.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import br.unifor.ads.pomodroid.R
import br.unifor.ads.pomodroid.dao.UserDAO

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mEmailLogin: EditText
    private lateinit var mSenhaLogin: EditText
    private lateinit var mEntrarBtn: Button
    private lateinit var mLoginRegister: TextView
    private lateinit var userDAO: UserDAO

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mEmailLogin = findViewById(R.id.email_login)
        mSenhaLogin = findViewById(R.id.senha_login)

        mEntrarBtn = findViewById(R.id.entrar_btn)
        mEntrarBtn.setOnClickListener(this)

        mLoginRegister = findViewById(R.id.login_textview_register)
        mLoginRegister.setOnClickListener(this)

        userDAO = UserDAO(this)
    }

    override fun onClick(view: View) {

        when(view.id){

            R.id.login_textview_register ->{

                val it = Intent(this, RegisterFormActivity::class.java)
                startActivity(it)

            }

            R.id.entrar_btn ->{

                val users = userDAO.findAll()

                Log.d("usuários:", users.toString())

                for (user in users){
                    if (user.email == mEmailLogin.text.toString()){
                        if (user.password == mSenhaLogin.text.toString()){
                            val it = Intent(this, MainActivity::class.java)
                            startActivity(it)

                            finish()
                        }
                    }


                }

                val toast = Toast.makeText(LoginActivity@this, "Dados do usuário incorretos.", Toast.LENGTH_SHORT)
                toast.show()

            }

        }

    }

}
