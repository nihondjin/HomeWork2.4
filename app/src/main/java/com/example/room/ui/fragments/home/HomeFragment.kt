package com.example.room.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.App
import com.example.room.R
import com.example.room.databinding.FragmentHomeBinding
import com.example.room.db.entity.NoteModel
import com.example.room.ui.adapters.NoteAdapter
import com.example.room.utils.PreferenceHelper


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var noteAdapter: NoteAdapter
    private var list: ArrayList<NoteModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setUpClickListener()
        changeLayoutManager()
        getData()
    }

    private fun changeLayoutManager() {
        if (PreferenceHelper.isLinearLayout) {
            binding.rvListOfText.layoutManager = LinearLayoutManager(requireContext())
        } else {
            binding.rvListOfText.layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun initialize() {
        noteAdapter = NoteAdapter(this::onClickListener)
        binding.rvListOfText.apply {
            adapter = noteAdapter
        }
    }

    private fun setUpClickListener() = with(binding) {
        btnAddText.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToSecondFragment(
                    null, false
                )
            )
        }
        viewGridManager.setOnClickListener {
            PreferenceHelper.isLinearLayout = false
            rvListOfText.layoutManager = GridLayoutManager(requireContext() ,2)
            viewGridManager.isVisible = false
            viewLinearManager.isVisible = true
        }
        viewLinearManager.setOnClickListener {
            PreferenceHelper.isLinearLayout = true
            rvListOfText.layoutManager = LinearLayoutManager(requireContext())
            viewGridManager.isVisible = true
            viewLinearManager.isVisible = false
        }
    }

    private fun onClickListener(model: NoteModel) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToSecondFragment(
                model, true
            )
        )
    }

    private fun getData() {
        App.getInstance()?.noteDao()?.getAll()?.observe(viewLifecycleOwner) {
            noteAdapter.setList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        list.clear()
    }
}