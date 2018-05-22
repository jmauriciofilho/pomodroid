package br.unifor.ads.pomodroid.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import br.unifor.ads.pomodroid.R
import br.unifor.ads.pomodroid.dao.TaskListDAO
import br.unifor.ads.pomodroid.dao.UserDAO
import br.unifor.ads.pomodroid.entity.TaskList

class TaskListFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mEditTestName: EditText
    private lateinit var mEditTestDescription: EditText
    private lateinit var mSalvarListBtn: Button

    private lateinit var taskListDAO: TaskListDAO
    private lateinit var userDAO: UserDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list_form)

        mEditTestName = findViewById(R.id.edit_text_name_list)
        mEditTestDescription = findViewById(R.id.edit_text_description_list)

        mSalvarListBtn = findViewById(R.id.salvar_list_btn)
        mSalvarListBtn.setOnClickListener(this)

        taskListDAO = TaskListDAO(this)
        userDAO = UserDAO(this)
    }

    override fun onClick(v: View) {

        when (v.id){

            R.id.salvar_list_btn -> {

                val name = mEditTestName.text.toString()
                val description = mEditTestDescription.text.toString()

                val sharedPreferences = getSharedPreferences("dadosUser", 0)
                val id = sharedPreferences.getLong("userId", 1)

                val user = userDAO.find(id)

                val taskList = TaskList(null, name, description, user!!)

                taskListDAO.insert(taskList)

                val it = Intent(this, MainActivity::class.java)
                startActivity(it)

                finish()

            }

        }
    }
}
