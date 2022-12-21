package com.example.room.ui.fragments.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.room.R
import com.example.room.databinding.FragmentOnBoardBinding
import com.example.room.ui.adapters.OnBoardViewPagerAdapter
import com.example.room.ui.model.PageModel
import com.example.room.utils.PreferenceHelper


class OnBoardFragment : Fragment() {


    private lateinit var binding: FragmentOnBoardBinding
    private val viewPagerAdapter = OnBoardViewPagerAdapter(this::onItemClick)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        setupListeners()
    }

    private fun setupListeners() {
        binding.pager.setOnClickListener {
            binding.pager.setCurrentItem(binding.pager.currentItem + 1, true)
        }
        binding.startBtn.setOnClickListener {
            if (binding.pager.currentItem == 2) {
                findNavController().navigate(R.id.homeFragment)
            } else
                binding.pager.setCurrentItem(binding.pager.currentItem + 1, true)
        }
    }

    private fun getData() {
        setupAdapter()
        changeButtonTextDependingOnPagerCurrentItem()
    }

    private fun changeButtonTextDependingOnPagerCurrentItem() {
        binding.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    2 -> binding.startBtn.text = ""
                    else -> binding.startBtn.text = "Следующий"
                }
            }
        })
    }
    private fun getBoardList(): ArrayList<PageModel> = with(binding) {

        val list: ArrayList<PageModel> = arrayListOf()
        list.add(
            PageModel(
                R.raw.boy_animation,
                "", "Очень удобный функционал ",
            )
        )
        list.add(
            PageModel(
                R.raw.pro, "", "Быстрый, качественный продукт",
            )
        )
        list.add(
            PageModel(
                R.raw.tapping,
                "Начать работу",
                "Куча функций и интересных фишек",
            )
        )
        return list
    }

    private fun setupAdapter() {
        binding.pager.adapter = viewPagerAdapter
        viewPagerAdapter.setData(getBoardList())
        binding.dotsIndicator.attachTo(binding.pager)
    }

    private fun onItemClick(position: Int) {
        when (position) {
            0 -> {
                binding.pager.setCurrentItem(1, true)
            }
            1 -> {
                binding.pager.setCurrentItem(2, true)
            }
            2 -> {
                PreferenceHelper.isShow = true
                findNavController().navigate(R.id.homeFragment)
            }
        }
    }
}
