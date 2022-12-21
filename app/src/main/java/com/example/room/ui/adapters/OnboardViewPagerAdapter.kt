package com.example.room.ui.adapters
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.room.databinding.ItemOnBoardBinding
import com.example.room.ui.model.PageModel

class OnBoardViewPagerAdapter(val onItemClick: (position: Int) -> Unit) :
    RecyclerView.Adapter<OnBoardViewPagerAdapter.ViewPagerViewHolder>() {
    var list: ArrayList<PageModel> = arrayListOf()

    inner class ViewPagerViewHolder(val binding: ItemOnBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: PageModel) {
            binding.tvStart.text = model.title
            binding.txView.text = model.description
            binding.lottieAnimation.setAnimation(model.image)
            binding.tvStart.setOnClickListener {
                onItemClick(layoutPosition)
            }
        }
    }
    fun setData(list: ArrayList<PageModel>) {
        this.list = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val binding =
            ItemOnBoardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}