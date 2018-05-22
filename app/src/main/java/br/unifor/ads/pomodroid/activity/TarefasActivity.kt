package br.unifor.ads.pomodroid.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import br.unifor.ads.pomodroid.R
import br.unifor.ads.pomodroid.adapter.TarefasAdapter
import br.unifor.ads.pomodroid.dao.TaskDAO
import br.unifor.ads.pomodroid.entity.Task

class TarefasActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mTasks: RecyclerView

    private lateinit var mRecyclerViewAdapter: RecyclerView.Adapter<*>
    private lateinit var mRecyclerViewLayoutManager: LinearLayoutManager
    private lateinit var mSalvarbtn: FloatingActionButton

    private lateinit var taskDAO: TaskDAO
    private lateinit var tasks: MutableList<Task>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tarefas)

        mSalvarbtn = findViewById(R.id.main_recycler_tarefa_add)
        mSalvarbtn.setOnClickListener(this)

        taskDAO = TaskDAO(this)
        val idList = intent.getIntExtra("idList", 0)
        tasks = taskDAO.findListTask(idList).toMutableList()

        mRecyclerViewAdapter = TarefasAdapter(this, tasks)
        mRecyclerViewLayoutManager = LinearLayoutManager(this)

        mTasks = findViewById<RecyclerView>(R.id.main_recycler_tarefas).apply {
            setHasFixedSize(false)
            layoutManager = mRecyclerViewLayoutManager
            adapter = mRecyclerViewAdapter
        }
    }

    override fun onStart() {
        val idList = intent.getIntExtra("idList", 0)

        super.onStart()
        tasks.clear()
        tasks.addAll(0, taskDAO.findListTask(idList))
        mTasks.adapter.notifyDataSetChanged()
    }

    override fun onClick(v: View?) {
        val idList = intent.getIntExtra("idList", 0)

        val intent = Intent(this, TarefaFormActivity::class.java)
        intent.putExtra("idList", idList)
        startActivity(intent)
    }
}
