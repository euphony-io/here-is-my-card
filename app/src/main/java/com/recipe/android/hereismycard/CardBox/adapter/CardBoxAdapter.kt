package com.recipe.android.hereismycard.CardBox.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.recipe.android.hereismycard.databinding.ItemCardBoxBinding

class CardBoxAdapter: RecyclerView.Adapter<CardBoxAdapter.CardBoxViewHolder>() {
    inner class CardBoxViewHolder(val binding: ItemCardBoxBinding): RecyclerView.ViewHolder(binding.root){
        fun bindWithView(position: Int) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardBoxViewHolder {
        return CardBoxViewHolder(ItemCardBoxBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CardBoxViewHolder, position: Int) {
        holder.bindWithView(position)
    }

    // 데이터 받기 전 임시 카운트
    // 데이터 listen 기능 구현 후 수정해주세요.
    override fun getItemCount(): Int = 5

    fun submitList(){

    }


}