package com.example.notesappdemo.fragment

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.notesappdemo.R
import com.example.notesappdemo.databinding.FragmentCreateNoteBinding
import com.example.notesappdemo.db.Note
import com.example.notesappdemo.viewmodel.NoteViewModel

class CreateNoteFragment : Fragment() {

    private var _binding: FragmentCreateNoteBinding? = null

    private val binding get() = _binding!!

    lateinit var viewModel:NoteViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCreateNoteBinding.inflate(inflater,container,false)
        val view = binding.root

        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        binding.createNoteBtn.setOnClickListener{ view->
            val noteTitle = binding.titleEdittext.text.toString()
            val noteDesc = binding.descEdittext.text.toString()
            var favnote = binding.favCheckbox.isChecked

            viewModel.insertNote(Note(null, noteTitle, noteDesc,favnote))
            Toast.makeText(context, "Note Added", Toast.LENGTH_LONG).show()
            Navigation.findNavController(view).popBackStack()
            Navigation.findNavController(view).navigate(R.id.homeFragment)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
