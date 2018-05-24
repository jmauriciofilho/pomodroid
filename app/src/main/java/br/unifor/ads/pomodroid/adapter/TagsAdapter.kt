package br.unifor.ads.pomodroid.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.unifor.ads.pomodroid.R
import br.unifor.ads.pomodroid.activity.TagFormEditActivity
import br.unifor.ads.pomodroid.entity.Tag

/**
 * Created by josemauricio on 22/05/2018.
 */
class TagsAdapter(val context: Context, val tags: List<Tag>) : RecyclerView.Adapter<TagsAdapter.TagViewHolder>() {

    class TagViewHolder(itemView: View, val context: Context) : RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener {

        val name: TextView
        var id: Int

        init {

            name = itemView.findViewById(R.id.item_tag_name)
            id = 0

            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)

        }

        override fun onClick(v: View?) {
//            val intent = Intent(context, TagActivity::class.java)
//            intent.putExtra("idTag", id)
//            context.startActivity(intent)
        }

        override fun onLongClick(v: View?): Boolean {
            val intent = Intent(context, TagFormEditActivity::class.java)
            intent.putExtra("idTag", id)
            context.startActivity(intent)
            return true
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_tag, parent, false)
        return TagViewHolder(itemView, context)
    }

    override fun getItemCount(): Int {
        return tags.size
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.name.text = tags[position].name
        holder.id = tags[position].id!!
    }

}