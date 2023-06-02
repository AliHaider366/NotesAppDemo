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

    private val binding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = binding.root

        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        viewModel.lastRecord.observe(viewLifecycleOwner) { record ->
            // Use the last record here
            binding.titletextView.text = record?.title
            binding.descriptionTextView.text = record?.desc
        }

        // Call the method to load the last record
        viewModel.loadLastRecord()

        //Toast.makeText(context, "Data : " + note.value.toString(), Toast.LENGTH_LONG).show()


        binding.newNoteBtn.setOnClickListener { view ->
            Navigation.findNavController(view).navigate(R.id.createNoteFragment)
        }

        return view
    }

}