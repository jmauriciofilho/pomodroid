package br.unifor.ads.pomodroid.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import br.unifor.ads.pomodroid.R
import br.unifor.ads.pomodroid.dao.TaskDAO
import br.unifor.ads.pomodroid.dao.TaskListDAO
import br.unifor.ads.pomodroid.entity.Task

class TarefaFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mNameTarefa: EditText
    private lateinit var mDescriptionTarefa: EditText
    private lateinit var mEstimatePomodoroTarefa: EditText
    private lateinit var mSalvarBtnTarefa: Button

    private lateinit var taskListDAO: TaskListDAO
    private lateinit var taskDAO: TaskDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tarefa_form)

        mNameTarefa = findViewById(R.id.name_tarefa)
        mDescriptionTarefa = findViewById(R.id.description_tarefa)
        mEstimatePomodoroTarefa = findViewById(R.id.estimed_pomodoro_tarefa)

        mSalvarBtnTarefa = findViewById(R.id.salvar_btn_tarefa)
        mSalvarBtnTarefa.setOnClickListener(this)

        taskListDAO = TaskListDAO(this as MainActivity)
        taskDAO = TaskDAO(this)
    }

    override fun onClick(v: View) {
        when(v.id){

            R.id.salvar_btn_tarefa -> {
                val name = mNameTarefa.text.toString()
                val description = mDescriptionTarefa.text.toString()
                val estimatePomodoro = mEstimatePomodoroTarefa.text.toString().toInt()

                val idList = intent.getIntExtra("idList", 0)

                val list = taskListDAO.find(idList)

                Log.d("teste", list.toString())

                val task = Task(null, name, description, false, estimatePomodoro, 0, list!!)

                taskDAO.insert(task)

                val it = Intent(this, TarefasActivity::class.java)
                it.putExtra("idList", idList)
                startActivity(it)

                finish()
            }

        }
    }
}
