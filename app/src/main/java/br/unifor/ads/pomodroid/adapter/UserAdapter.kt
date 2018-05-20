package br.unifor.ads.pomodroid.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.unifor.ads.pomodroid.R
import br.unifor.ads.pomodroid.entity.User

class UserAdapter(val context: Context, val users: List<User>) : RecyclerView.Adapter<UserAdapter.TaskViewHolder>() {

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name: TextView
        val email: TextView

        init {

            name = itemView.findViewById(R.id.item_user_name)
            email = itemView.findViewById(R.id.item_user_email)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {

        val itemView = LayoutInflater.from(context).inflate(R.layout.item_user_list, parent, false)
        return TaskViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {

        holder.name.text = users[position].name
        holder.email.text = users[position].email

    }

}