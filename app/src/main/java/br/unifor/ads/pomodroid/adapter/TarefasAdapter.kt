package br.unifor.ads.pomodroid.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.unifor.ads.pomodroid.R
import br.unifor.ads.pomodroid.activity.TarefaFormEditActivity
import br.unifor.ads.pomodroid.activity.TarefasActivity
import br.unifor.ads.pomodroid.entity.Task

class TarefasAdapter(val context: Context, val tarefas: List<Task>) : RecyclerView.Adapter<TarefasAdapter.TarefaViewHolder>() {

    class TarefaViewHolder(itemView: View, val context: Context) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val name: TextView
        val description: TextView
        var id: Long

        init {

            name = itemView.findViewById(R.id.item_tarefa_name)
            description = itemView.findViewById(R.id.item_tarefa_description)
            id = 0

            itemView.setOnClickListener(this)

        }

        override fun onClick(v: View?) {
            val intent = Intent(context, TarefaFormEditActivity::class.java)
            intent.putExtra("idTarefa", id)
            context.startActivity(intent)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_tarefa, parent, false)
        return TarefaViewHolder(itemView, context)
    }

    override fun getItemCount(): Int {
        return tarefas.size
    }

    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {
        holder.name.text = tarefas[position].name
        holder.description.text = tarefas[position].description
        holder.id = tarefas[position].id!!
    }

}