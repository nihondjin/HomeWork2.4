package com.example.room.ui.fragments.home

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.App
import com.example.room.databinding.FragmentSecondBinding
import com.example.room.db.entity.NoteModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private lateinit var model: NoteModel
    private val argsNavigation by navArgs<SecondFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeButton()
        setUpClickListener()
        getData()
    }

    private fun changeButton() {
        if (argsNavigation.isUpdate) {
            binding.txtReady.text = "Обновить"
        } else {
            binding.txtReady.text = "Готово"
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setUpClickListener() = with(binding) {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd MMMM")
        val formatted = current.format(formatter)
        binding.txtDayMonth.text = formatted

        btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
        txtReady.setOnClickListener {
            if (!argsNavigation.isUpdate) {
                val clockInfo = binding.txtTime.text.toString()
                val date = "$formatted $clockInfo"
                val et = etTitle.text.toString()
                App.getInstance()?.noteDao()?.insertNote(NoteModel(et, date, clockInfo))
                findNavController().navigateUp()
            } else {
                val clockInfo = binding.txtTime.text.toString()
                val etDescription = binding.etTitle.text.toString()
                argsNavigation.model?.textDescription = etDescription
                argsNavigation.model?.textData = "$formatted $clockInfo"
                model = argsNavigation.model!!
                App.getInstance()?.noteDao()?.upDateUser(model)
                findNavController().navigateUp()
            }
        }
        etTitle.addTextChangedListener {
            txtReady.isVisible = etTitle.text.isNotEmpty()
        }
    }

    private fun getData() {
        if (argsNavigation.isUpdate) {
            binding.etTitle.setText(argsNavigation.model?.textDescription)
        }
    }
}