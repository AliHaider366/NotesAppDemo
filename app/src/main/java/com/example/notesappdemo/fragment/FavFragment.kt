package com.example.notesappdemo.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesappdemo.R
import com.example.notesappdemo.adapter.RecyclerAdapter
import com.example.notesappdemo.databinding.FragmentFavBinding
import com.example.notesappdemo.model.Note
import com.example.notesappdemo.viewmodel.NoteViewModel


class FavFragment : Fragment(), RecyclerAdapter.CallBackInterface {

    private lateinit var viewModel: NoteViewModel

    //private lateinit var noteList: MutableList<Note>

    private lateinit var adapter: RecyclerAdapter

    private val binding by lazy {
        FragmentFavBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        viewModel.initFavNotes()
        //noteList = mutableListOf()

        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = RecyclerAdapter(mutableListOf(), this@FavFragment)
        binding.recyclerView.adapter = adapter

        viewModel.getAllFavNotes().observe(viewLifecycleOwner) { list ->
            if (list.isNotEmpty()) {
                //noteList.addAll(list)
                adapter.updateList(list)
                //adapter.notifyDataSetChanged()
            }
        }
    }

    override fun updateCallBackFunc(view: View, note: Note) {
        val bundle = Bundle()
        bundle.putString("title", note.title)
        bundle.putString("desc", note.desc)
        bundle.putInt("id", note.id!!)
        bundle.putBoolean("fav", note.fav!!)
        findNavController().navigate(R.id.updateNoteFragment, bundle)
    }

}