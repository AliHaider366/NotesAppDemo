package com.example.notesappdemo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.notesappdemo.R
import com.example.notesappdemo.databinding.FragmentHomeBinding
import com.example.notesappdemo.viewmodel.NoteViewModel


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        viewModel.lastRecord.observe(viewLifecycleOwner) { record ->
            // Use the last record here
            binding.titletextView.setText(record?.title)
            binding.descriptionTextView.setText(record?.desc)
        }

        // Call the method to load the last record
        viewModel.loadLastRecord()

        //Toast.makeText(context, "Data : " + note.value.toString(), Toast.LENGTH_LONG).show()




        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        val view = binding.root
        binding.newNoteBtn.setOnClickListener{ view->
            Navigation.findNavController(view).navigate(R.id.createNoteFragment)
        }

        return view
    }

}