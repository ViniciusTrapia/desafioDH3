package com.example.dh3teste

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.example.dh3teste.Model.Result
import com.example.dh3teste.databinding.RvCardItemBinding

class MarvelAdapter (
    private val onClick: (Result?) -> Unit
): PagedListAdapter<Result, MarvelViewHolder>(Result.DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvCardItemBinding.inflate(layoutInflater, parent, false)
        return MarvelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MarvelViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, onClick) }
    }
}