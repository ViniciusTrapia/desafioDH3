package com.example.dh3teste

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dh3teste.Model.Result
import com.example.dh3teste.databinding.RvCardItemBinding

class MarvelViewHolder(
    val binding: RvCardItemBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Result, onClick: (Result) -> Unit) = with(binding){
        Glide.with(itemView.context)
            .load(item.thumbnail?.path)
            .placeholder(R.drawable.ic_b)
            .into(ivCardItem)
        tvCardItem.text = "#${item.issueNumber?.toInt()}"

        itemView.setOnClickListener {
            onClick(item)
        }
    }
}