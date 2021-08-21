package com.recipe.android.hereismycard.makeCard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.recipe.android.hereismycard.MainActivity.Companion.bgList
import com.recipe.android.hereismycard.R
import com.recipe.android.hereismycard.databinding.ItemCardBgBinding

class SelectCardBgAdapter() : RecyclerView.Adapter<SelectCardBgAdapter.SelectBgViewHolder>() {
    private lateinit var itemClickListener : OnItemClickListener

    inner class SelectBgViewHolder(val binding: ItemCardBgBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bindWithView(position: Int) {
                binding.cardBg.setBackgroundResource(bgList[position])
            }
    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectBgViewHolder {
        return SelectBgViewHolder(ItemCardBgBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SelectBgViewHolder, position: Int) {
        holder.bindWithView(position)
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    override fun getItemCount(): Int = 5
}