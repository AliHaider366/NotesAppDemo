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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavFragment : Fragment() {

    lateinit var viewModel: NoteViewModel
    private lateinit var note_list : LiveData<MutableList<Note>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        note_list = viewModel.getAllFavNotes()

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