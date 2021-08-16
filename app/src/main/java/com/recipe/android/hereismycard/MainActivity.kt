package com.recipe.android.hereismycard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.recipe.android.hereismycard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clickEditBtn()

        binding.btnEdit.setOnClickListener { clickEditBtn() }
        binding.btnBox.setOnClickListener { clickBoxBtn() }
    }

    private fun clickEditBtn(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_main, MakeCardFragment())
            .commitAllowingStateLoss()
        binding.icEdit.visibility = View.GONE
        binding.icEditSelect.visibility = View.VISIBLE
        binding.icBox.visibility = View.VISIBLE
        binding.icBoxSelect.visibility = View.GONE
    }

    private fun clickBoxBtn(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_main, CardBoxFragment())
            .commitAllowingStateLoss()
        binding.icEdit.visibility = View.VISIBLE
        binding.icEditSelect.visibility = View.GONE
        binding.icBox.visibility = View.GONE
        binding.icBoxSelect.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}