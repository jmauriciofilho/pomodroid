package br.unifor.ads.pomodroid.activity

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import br.unifor.ads.pomodroid.R
import br.unifor.ads.pomodroid.dao.UserDAO
import br.unifor.ads.pomodroid.entity.User

import kotlinx.android.synthetic.main.activity_user_form.*

class UserFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mUserFormName: EditText
    private lateinit var mUserFormEmail: EditText
    private lateinit var mUserFormPassword: EditText
    private lateinit var mUserFormButton: Button

    private lateinit var mUserDAO: UserDAO


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_form)

        mUserDAO = UserDAO(this)

        mUserFormName = findViewById(R.id.activity_user_form_name)
        mUserFormEmail = findViewById(R.id.activity_user_form_email)
        mUserFormPassword = findViewById(R.id.activity_user_form_password)

        mUserFormButton = findViewById(R.id.activity_user_form_button)
        mUserFormButton.setOnClickListener(this)

    }

    override fun onClick(v: View) {

        when (v.id) {

            R.id.activity_user_form_button -> {

                var userFormName = true
                var userFormEmail = true
                var userFormPassword = true

                if (mUserFormName.text.isEmpty()) {
                    mUserFormName.error = "Campo obrigatório"
                    userFormName = false
                }

                if (mUserFormEmail.text.isEmpty()) {
                    mUserFormEmail.error = "Campo obrigatório"
                    userFormEmail = false
                }

                if (mUserFormPassword.text.isEmpty()) {
                    mUserFormPassword.error = "Campo obrigatório"
                    userFormPassword = false
                }


                if (userFormName && userFormEmail && userFormPassword) {

                    val user = User(null,
                            mUserFormName.text.toString(),
                            mUserFormEmail.text.toString(),
                            mUserFormPassword.text.toString())

                    val isInsert = mUserDAO.insert(user)

                    if(isInsert){

                        Toast.makeText(this, "Usuário inserido com sucesso", Toast.LENGTH_SHORT).show()
                        finish()

                    }


                }

            }

        }


    }

}
