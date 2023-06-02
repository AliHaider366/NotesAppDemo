package com.example.notesappdemo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappdemo.R
import com.example.notesappdemo.adapter.RecyclerAdapter
import com.example.notesappdemo.db.Note
import com.example.notesappdemo.viewmodel.NoteViewModel

class SavedFragment : Fragment() {

    lateinit var viewModel: NoteViewModel
    private lateinit var note_list : LiveData<MutableList<Note>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        note_list = viewModel.getAllNotes()

        var recycler_view = view.findViewById<RecyclerView>(R.id.recyclerView)
        recycler_view.apply{
            note_list.observe(viewLifecycleOwner) { list ->
                val size = list.size
                if(size>0){
                    layoutManager = LinearLayoutManager(activity)
                    adapter = RecyclerAdapter(note_list)
                }
            }
        }
    }


}