package br.unifor.ads.pomodroid.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.unifor.ads.pomodroid.R
import br.unifor.ads.pomodroid.dao.UserDAO
import br.unifor.ads.pomodroid.entity.User

class TesteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teste)


        deleteUser()


    }

    private fun createUser(){
        val userDAO = UserDAO(TesteActivity@this)

        val user = User(null, "Juana", "juana@gmail.com", "default")
        val user2 = User(null, "Paulo", "paulo@gmail.com", "default")

        userDAO.insert(user)
        userDAO.insert(user2)

        val users = userDAO.findAll()

        Log.d("result", users.toString())
    }

    private fun updateUser(){
        val userDAO = UserDAO(TesteActivity@this)

        val userOld = userDAO.find(3)

        Log.d("usuário antigo:", userOld.toString())

        val user = User(3, "Paulo Pereira", "paulo@gmail.com", "default")

        userDAO.update(user)

        val userAtualizado = userDAO.find(3)

        Log.d("usuario atualizado:", userAtualizado.toString())
    }

    private fun deleteUser(){
        val userDAO = UserDAO(TesteActivity@this)

        val users = userDAO.findAll()

        Log.d("result", users.toString())

        val user = userDAO.find(3)

        userDAO.delete(user!!)

        val users2 = userDAO.findAll()

        Log.d("result", users2.toString())
    }
}
