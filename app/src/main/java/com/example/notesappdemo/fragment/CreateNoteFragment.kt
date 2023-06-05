package com.example.notesappdemo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notesappdemo.R
import com.example.notesappdemo.databinding.FragmentCreateNoteBinding
import com.example.notesappdemo.model.Note
import com.example.notesappdemo.viewmodel.NoteViewModel

class CreateNoteFragment : Fragment() {

    private val binding by lazy {
        FragmentCreateNoteBinding.inflate(layoutInflater)
    }
    lateinit var viewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = binding.root
        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        binding.createNoteBtn.setOnClickListener { view ->
            viewModel.insertNote(
                Note(
                    null,
                    binding.titleEdittext.text.toString(),
                    binding.descEdittext.text.toString(),
                    binding.favCheckbox.isChecked
                )
            )
            Toast.makeText(context, "Note Added", Toast.LENGTH_LONG).show()
            findNavController().popBackStack()
            findNavController().navigate(R.id.homeFragment)
        }
        return view
    }
}