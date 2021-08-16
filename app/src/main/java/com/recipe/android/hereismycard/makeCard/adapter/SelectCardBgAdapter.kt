package com.recipe.android.hereismycard.makeCard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.recipe.android.hereismycard.MainActivity.Companion.bgList
import com.recipe.android.hereismycard.R
import com.recipe.android.hereismycard.databinding.ItemCardBgBinding
import com.recipe.android.hereismycard.makeCard.`interface`.SelectBgInterface

class SelectCardBgAdapter(val selectView: SelectBgInterface) : RecyclerView.Adapter<SelectCardBgAdapter.SelectBgViewHolder>() {
    inner class SelectBgViewHolder(val binding: ItemCardBgBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bindWithView(position: Int) {
                binding.cardBg.setBackgroundResource(bgList[position])
                itemView.setOnClickListener { selectView.selectBg(position) }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectBgViewHolder {
        return SelectBgViewHolder(ItemCardBgBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SelectBgViewHolder, position: Int) {
        holder.bindWithView(position)
    }

    override fun getItemCount(): Int = 5
}