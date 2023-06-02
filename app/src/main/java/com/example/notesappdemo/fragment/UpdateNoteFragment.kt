package com.example.notesappdemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notesappdemo.R
import com.example.notesappdemo.databinding.FragmentUpdateNoteBinding
import com.example.notesappdemo.model.Note
import com.example.notesappdemo.viewmodel.NoteViewModel


class UpdateNoteFragment : Fragment() {


    private val binding by lazy {
        FragmentUpdateNoteBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = binding.root

        binding.titleEdittext.setText(arguments?.getString("title").toString())
        binding.descEdittext.setText(arguments?.getString("desc").toString())
        binding.favCheckbox.isChecked = arguments?.getBoolean("fav") == true

        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        binding.updateNoteBtn.setOnClickListener { view ->

            viewModel.update(
                Note(
                    arguments?.getInt("id"),
                    binding.titleEdittext.text.toString(),
                    binding.descEdittext.text.toString(),
                    binding.favCheckbox.isChecked
                )
            )
            Toast.makeText(context, "Note Updated", Toast.LENGTH_LONG).show()
            findNavController().popBackStack()
            findNavController().navigate(R.id.homeFragment)
        }

        binding.deleteNoteBtn.setOnClickListener { view ->

            viewModel.delete(
                Note(
                    arguments?.getInt("id"),
                    binding.titleEdittext.text.toString(),
                    binding.descEdittext.text.toString(),
                    binding.favCheckbox.isChecked
                )
            )
            Toast.makeText(context, "Note Deleted", Toast.LENGTH_LONG).show()
            findNavController().popBackStack()
            findNavController().navigate(R.id.homeFragment)
        }
        return view
    }

}