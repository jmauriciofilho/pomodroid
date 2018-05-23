package br.unifor.ads.pomodroid.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.PopupMenu
import android.widget.TextView
import br.unifor.ads.pomodroid.R
import br.unifor.ads.pomodroid.activity.MainActivity
import br.unifor.ads.pomodroid.activity.TarefasActivity
import br.unifor.ads.pomodroid.activity.TaskListFormEditActivity
import br.unifor.ads.pomodroid.dao.TaskListDAO
import br.unifor.ads.pomodroid.entity.TaskList

class ListsTasksAdapter(val context: MainActivity, val lists: List<TaskList>) : RecyclerView.Adapter<ListsTasksAdapter.TaskViewHolder>() {

    class TaskViewHolder(itemView: View, val context: Context) : RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener {

        val name: TextView
        val description: TextView
        var id: Int
        val option: TextView
        val taskListDAO: TaskListDAO

        init {

            name = itemView.findViewById(R.id.item_list_task_name)
            description = itemView.findViewById(R.id.item_list_task_description)

            option = itemView.findViewById(R.id.option_list_task)

            id = 0

            taskListDAO = TaskListDAO(context as MainActivity)

            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)

        }

        override fun onClick(v: View?) {
            val intent = Intent(context, TarefasActivity::class.java)
            intent.putExtra("idList", id)
            context.startActivity(intent)
        }

        override fun onLongClick(v: View): Boolean {
            val intent = Intent(context, TaskListFormEditActivity::class.java)
            intent.putExtra("idList", id)
            context.startActivity(intent)

            return true
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {

        val itemView = LayoutInflater.from(context).inflate(R.layout.item_list_task, parent, false)
        return TaskViewHolder(itemView, context)

    }

    override fun getItemCount(): Int {
        return lists.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {

        holder.name.text = lists[position].name
        holder.description.text = lists[position].description
        holder.id = lists[position].id!!

        holder.option.setOnClickListener(View.OnClickListener {
            //will show popup menu here
            val popup = PopupMenu(context, holder.option)
            //inflating menu from xml resource
            popup.inflate(R.menu.menu_option_list_tasks)
            //adding click listener
            popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
                override fun onMenuItemClick(item: MenuItem): Boolean {
                    when (item.getItemId()) {
                        R.id.editar_menu -> {

                            val intent = Intent(context, TaskListFormEditActivity::class.java)
                            intent.putExtra("idList", holder.id)
                            context.startActivity(intent)

                        }
                        R.id.excluir_menu -> {

                            val taskList = TaskList(holder.id, holder.name.text.toString(), holder.description.text.toString())
                            holder.taskListDAO.delete(taskList)
                            val it = Intent(context, MainActivity::class.java)
                            context.startActivity(it)

                        }
                    }
                    return false
                }
            })
            //displaying the popup
            popup.show()
        })
    }

}