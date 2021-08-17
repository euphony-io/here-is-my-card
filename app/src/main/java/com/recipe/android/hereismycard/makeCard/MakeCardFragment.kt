package com.recipe.android.hereismycard.makeCard

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.recipe.android.hereismycard.MainActivity.Companion.bgList
import com.recipe.android.hereismycard.R
import com.recipe.android.hereismycard.databinding.FragmentMakeCardBinding
import com.recipe.android.hereismycard.makeCard.`interface`.SelectBgInterface
import com.recipe.android.hereismycard.makeCard.adapter.SelectCardBgAdapter

class MakeCardFragment : Fragment(), SelectBgInterface {
    private var _binding: FragmentMakeCardBinding? = null
    private val binding get() = _binding!!

    lateinit var selectCardBgAdapter: SelectCardBgAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMakeCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectCardBgAdapter = SelectCardBgAdapter(this)
        binding.rvSelectBg.apply {
            adapter = selectCardBgAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }

        binding.btnSend.setOnClickListener {
            binding.btnSend.playAnimation()

            // start speaker


        }
    }

    override fun onPause() {
        super.onPause()

        // finish speaker



        binding.btnSend.pauseAnimation()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun selectBg(position: Int) {
        binding.layoutCard.setBackgroundResource(bgList[position])
    }
}