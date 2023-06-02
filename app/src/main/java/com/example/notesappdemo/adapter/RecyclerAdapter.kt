package com.example.notesappdemo.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappdemo.R
import com.example.notesappdemo.db.Note


class RecyclerAdapter(var items: LiveData<MutableList<Note>>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items.value!!.get(position)
        holder.title.text = item.title
        holder.desc.text = item.desc
    }

    override fun getItemCount(): Int {
        return items.value!!.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener { v ->
                modifyNote(v,Note(
                    items.value?.get(adapterPosition)?.id,
                    title.text.toString(),
                    desc.text.toString(),
                    items.value?.get(adapterPosition)?.fav
                ))
                //Toast.makeText( v.context, "Position is $adapterPosition", Toast.LENGTH_SHORT).show()
            }
        }

        var title: TextView = view.findViewById(R.id.title)
        var desc: TextView = view.findViewById(R.id.description)

    }

    private fun modifyNote(view: View, note: Note) {
        //Toast.makeText( view.context, "Id : " + note.id.toString(), Toast.LENGTH_SHORT).show()
        val bundle = Bundle()
        bundle.putString("title", note.title)
        bundle.putString("desc", note.desc)
        bundle.putInt("id", note.id!!)
        bundle.putBoolean("fav", note.fav!!)
        Navigation.findNavController(view).navigate(R.id.updateNoteFragment, bundle)
    }
}
//diffutils