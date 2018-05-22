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

class TarefaFormEditActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mNameTarefa: EditText
    private lateinit var mDescriptionTarefa: EditText
    private lateinit var mEstimatePomodoroTarefa: EditText
    private lateinit var mSalvarBtnTarefa: Button

    private lateinit var taskDAO: TaskDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tarefa_form_edit)

        mNameTarefa = findViewById(R.id.name_tarefa_edit)
        mDescriptionTarefa = findViewById(R.id.description_tarefa_edit)
        mEstimatePomodoroTarefa = findViewById(R.id.estimed_pomodoro_tarefa_edit)

        mSalvarBtnTarefa = findViewById(R.id.salvar_btn_tarefa_edit)
        mSalvarBtnTarefa.setOnClickListener(this)

        taskDAO = TaskDAO(this)

        getTarefas()
    }

    private fun getTarefas() {
        val idTarefa = intent.getLongExtra("idTarefa", 0)

        val task = taskDAO.find(idTarefa)
        mNameTarefa.setText(task!!.name)
        mDescriptionTarefa.setText(task.description)
        mEstimatePomodoroTarefa.setText(task.estimatedPomodoro.toString())
    }

    override fun onClick(v: View) {
        when(v.id){

            R.id.salvar_btn_tarefa_edit -> {
                val name = mNameTarefa.text.toString()
                val description = mDescriptionTarefa.text.toString()
                val estimatePomodoro = mEstimatePomodoroTarefa.text.toString().toInt()

                val idTarefa = intent.getLongExtra("idTarefa", 0)
                val taskOld = taskDAO.find(idTarefa)

                val task = Task(idTarefa, name, description, false, estimatePomodoro, 0)

                taskDAO.update(task)

                val it = Intent(this, TarefasActivity::class.java)
                it.putExtra("idList", taskOld!!.taskList!!.id)
                startActivity(it)

                finish()
            }

        }
    }
}
