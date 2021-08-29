package com.recipe.android.hereismycard.CardBox

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.recipe.android.hereismycard.CardBox.adapter.CardBoxAdapter
import com.recipe.android.hereismycard.CardBox.db.AppDatabase
import com.recipe.android.hereismycard.CardBox.db.Card
import com.recipe.android.hereismycard.databinding.FragmentCardBoxBinding
import java.util.*

class CardBoxFragment : Fragment() {
    private var _binding: FragmentCardBoxBinding? = null
    private val binding get() = _binding!!
    private var cardList = ArrayList<Card>()
    lateinit var cardBoxAdapter: CardBoxAdapter
    lateinit var db : AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardBoxBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initDB()
        getAllCards()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun initDB() {
        db = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java, "here-is-my-card-db"
        ).build()
    }

    private fun initRecyclerView() {
        cardBoxAdapter = CardBoxAdapter(cardList)
        binding.rvCardBox.apply {
            adapter = cardBoxAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }

        cardBoxAdapter.setItemClickListener(object : CardBoxAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                showDialogForDelete(position)
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getAllCards() {
        java.lang.Thread(Runnable {
            val cards = db.cardDao().getCardAll()

            for(card in cards) {
                cardList.add(
                    com.recipe.android.hereismycard.CardBox.db.Card(
                        card.uid,
                        card.name,
                        card.tel,
                        card.email,
                        card.address,
                        card.bg
                    )
                )

                activity?.runOnUiThread(java.lang.Runnable{
                    cardBoxAdapter.cardList = cardList
                    cardBoxAdapter.notifyDataSetChanged()
                })
            }
        }).start()
    }

    private fun showDialogForDelete(position: Int) {
        val builder = AlertDialog.Builder(requireContext())
            .setTitle("Delete Card")
            .setMessage("Are you sure you want to delete the business card?")

        val listener = object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                when (which) {
                    DialogInterface.BUTTON_POSITIVE ->
                        deleteCard(position)
                    DialogInterface.BUTTON_NEGATIVE ->
                        return
                }
            }
        }

        builder.apply {
            setPositiveButton("Yes", listener)
            setNegativeButton("No", listener)
            show()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun deleteCard(position: Int) {
        java.lang.Thread(java.lang.Runnable {
            db.cardDao().deleteCard(cardList[position])

            activity?.runOnUiThread(java.lang.Runnable {
                cardList.removeAt(position)
                cardBoxAdapter.cardList = cardList
                cardBoxAdapter.notifyDataSetChanged()
            })
        }).start()
    }
}