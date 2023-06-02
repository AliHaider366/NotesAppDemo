package com.example.notesappdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappdemo.R
import com.example.notesappdemo.adapter.diifutil.AdapterDiffUtil
import com.example.notesappdemo.model.Note


class RecyclerAdapter(var items: MutableList<Note>, val callBack: CallBackInterface) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    interface CallBackInterface {
        fun updateCallBackFunc(view: View, note: Note)
    }
    fun updateList(list : MutableList<Note>){
        val diffResult = DiffUtil.calculateDiff(AdapterDiffUtil(items,list))
        items = list
        diffResult.dispatchUpdatesTo(this)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.title.text = item.title
        holder.desc.text = item.desc
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener { v ->
                callBack.updateCallBackFunc(
                    v, Note(
                        items[adapterPosition].id,
                        title.text.toString(),
                        desc.text.toString(),
                        items[adapterPosition].fav
                    )
                )
            }
        }

        var title: TextView = view.findViewById(R.id.title)
        var desc: TextView = view.findViewById(R.id.description)

    }

}
//diffutils