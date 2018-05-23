package br.unifor.ads.pomodroid.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import br.unifor.ads.pomodroid.R
import br.unifor.ads.pomodroid.dao.TaskListDAO
import br.unifor.ads.pomodroid.entity.TaskList

class TaskListFormEditActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mEditTestName: EditText
    private lateinit var mEditTestDescription: EditText
    private lateinit var mSalvarListBtn: Button

    private lateinit var taskListDAO: TaskListDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list_form_edit)

        mEditTestName = findViewById(R.id.edit_text_name_list_editar)
        mEditTestDescription = findViewById(R.id.edit_text_description_list_editar)

        mSalvarListBtn = findViewById(R.id.salvar_list_btn_editar)
        mSalvarListBtn.setOnClickListener(this)

        taskListDAO = TaskListDAO(this as MainActivity)

        getListTasks()
    }

    private fun getListTasks(){
        val idList = intent.getIntExtra("idList", 0)
        val list = taskListDAO.find(idList)

        mEditTestName.setText(list!!.name)
        mEditTestDescription.setText(list.description)
    }

    override fun onClick(v: View) {

        when (v.id){

            R.id.salvar_list_btn_editar -> {

                val name = mEditTestName.text.toString()
                val description = mEditTestDescription.text.toString()

                val idList = intent.getIntExtra("idList", 0)

                val taskList = TaskList(idList, name, description)

                taskListDAO.update(taskList)

                val it = Intent(this, MainActivity::class.java)
                startActivity(it)

                finish()

            }

        }
    }
}
