package br.unifor.ads.pomodroid.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import br.unifor.ads.pomodroid.R
import br.unifor.ads.pomodroid.dao.UserDAO
import br.unifor.ads.pomodroid.entity.User

class RegisterFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mNameEditText: EditText
    private lateinit var mEmailEditText: EditText
    private lateinit var mSenhaEditText: EditText
    private lateinit var mSalvarBtn: Button
    private lateinit var userDAO: UserDAO

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_form)

        mNameEditText = findViewById(R.id.edit_text_name)
        mEmailEditText = findViewById(R.id.edit_text_email)
        mSenhaEditText = findViewById(R.id.edit_text_senha)

        mSalvarBtn = findViewById(R.id.salvar_btn)
        mSalvarBtn.setOnClickListener(this)

        userDAO = UserDAO(this)
    }

    override fun onClick(v: View) {

        when(v.id){

            R.id.salvar_btn -> {
                val name = mNameEditText.text.toString()
                val email = mEmailEditText.text.toString()
                val password = mSenhaEditText.text.toString()

                val user = User(null, name, email, password)
                userDAO.insert(user)

                val it = Intent(this, LoginActivity::class.java)
                startActivity(it)

                finish()
            }

        }

    }
}
