package com.recipe.android.hereismycard.CardBox

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.recipe.android.hereismycard.CardBox.adapter.CardBoxAdapter
import com.recipe.android.hereismycard.databinding.FragmentCardBoxBinding

class CardBoxFragment : Fragment() {
    private var _binding: FragmentCardBoxBinding? = null
    private val binding get() = _binding!!

    lateinit var cardBoxAdapter: CardBoxAdapter

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

        cardBoxAdapter = CardBoxAdapter()
        binding.rvCardBox.apply {
            adapter = cardBoxAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}