package com.example.room.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.room.databinding.ItemRecBinding
import com.example.room.db.entity.NoteModel

class NoteAdapter(private val onClickListener: ((NoteModel) -> Unit?)? = null) :
    RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    private var list: List<NoteModel> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<NoteModel>) {
        this.list = list
        notifyDataSetChanged()
    }
    inner class ViewHolder(private val binding: ItemRecBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onClickListener?.let { it1 -> it1(list[bindingAdapterPosition]) }
            }
        }

        fun onBind(noteModel: NoteModel) {
            binding.tvTitleItem.text = noteModel.textTitle
            binding.tvDataItem.text = noteModel.textData
            binding.tvDescription.text = noteModel.textDescription
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRecBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}