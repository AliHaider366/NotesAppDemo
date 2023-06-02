package com.example.notesappdemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.notesappdemo.R
import com.example.notesappdemo.databinding.FragmentUpdateNoteBinding
import com.example.notesappdemo.db.Note
import com.example.notesappdemo.viewmodel.NoteViewModel


class UpdateNoteFragment : Fragment() {

    private var _binding: FragmentUpdateNoteBinding? = null

    private val binding get() = _binding!!

    lateinit var viewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateNoteBinding.inflate(inflater,container,false)
        val view = binding.root

        binding.titleEdittext.setText(arguments?.getString("title").toString())
        binding.descEdittext.setText(arguments?.getString("desc").toString())
        binding.favCheckbox.isChecked = arguments?.getBoolean("fav") == true

        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        binding.updateNoteBtn.setOnClickListener{ view->

            var favnote = binding.favCheckbox.isChecked
            viewModel.update(Note(arguments?.getInt("id"),
                binding.titleEdittext.text.toString(),
                binding.descEdittext.text.toString(),
                favnote))
            Toast.makeText(context, "Note Updated", Toast.LENGTH_LONG).show()
            Navigation.findNavController(view).popBackStack()
            Navigation.findNavController(view).navigate(R.id.homeFragment)
        }

        binding.deleteNoteBtn.setOnClickListener{ view->

            var favnote = binding.favCheckbox.isChecked
            viewModel.delete(Note(arguments?.getInt("id"),
                binding.titleEdittext.text.toString(),
                binding.descEdittext.text.toString(),
                favnote))
            Toast.makeText(context, "Note Deleted", Toast.LENGTH_LONG).show()
            Navigation.findNavController(view).popBackStack()
            Navigation.findNavController(view).navigate(R.id.homeFragment)
        }
        return view
    }

}