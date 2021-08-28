package com.recipe.android.hereismycard.CardBox.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.recipe.android.hereismycard.CardBox.db.Card
import com.recipe.android.hereismycard.MainActivity
import com.recipe.android.hereismycard.databinding.ItemCardBoxBinding

class CardBoxAdapter(
    var cardList: ArrayList<Card>
) : RecyclerView.Adapter<CardBoxAdapter.CardBoxViewHolder>() {
    private lateinit var itemClickListener : OnItemClickListener
    inner class CardBoxViewHolder(val binding: ItemCardBoxBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindWithView(card: Card) {
            binding.etName.text = card.name
            binding.etTel.text = card.tel
            binding.etEmail.text = card.email
            binding.etAddress.text = card.address
            binding.cardBg.setBackgroundResource(MainActivity.bgList[card.bg])
        }
    }
    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardBoxViewHolder {
        return CardBoxViewHolder(
            ItemCardBoxBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CardBoxViewHolder, position: Int) {
        holder.bindWithView(cardList[position])
        holder.itemView.setOnLongClickListener{
            itemClickListener.onClick(it, position)
            true
        }
    }

    override fun getItemCount(): Int = cardList.size
}