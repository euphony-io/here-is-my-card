package com.recipe.android.hereismycard.makeCard

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.recipe.android.hereismycard.CardBox.db.AppDatabase
import com.recipe.android.hereismycard.CardBox.db.Card
import com.recipe.android.hereismycard.MainActivity.Companion.bgList
import com.recipe.android.hereismycard.R
import com.recipe.android.hereismycard.databinding.FragmentMakeCardBinding
import com.recipe.android.hereismycard.makeCard.adapter.SelectCardBgAdapter
import euphony.lib.receiver.AcousticSensor
import euphony.lib.receiver.EuRxManager
import euphony.lib.transmitter.EuTxManager

class MakeCardFragment : Fragment() {
    private var _binding: FragmentMakeCardBinding? = null
    private val binding get() = _binding!!
    private var isSpeak = true
    private var speakText = ""
    private var saveBackgroundPosition = 0
    lateinit var db: AppDatabase
    lateinit var selectCardBgAdapter: SelectCardBgAdapter

    private val mTxManager: EuTxManager by lazy {
        EuTxManager()
    }

    private val mRxManager: EuRxManager by lazy {
        EuRxManager()
    }

    private val requiredPermission = arrayOf(
        android.Manifest.permission.RECORD_AUDIO
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requestAudioPermission()
        initDB()
        _binding = FragmentMakeCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectCardBgAdapter = SelectCardBgAdapter()
        selectCardBgAdapter.setItemClickListener(object : SelectCardBgAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                saveBackgroundPosition = position
                binding.layoutCard.setBackgroundResource(bgList[position])
            }
        })
        binding.rvSelectBg.apply {
            adapter = selectCardBgAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }

        binding.btnSend.setOnClickListener {
            if (isSpeak) {
                if(binding.etName.text.toString()==""){
                    Toast.makeText(requireContext(),"Enter your name",Toast.LENGTH_LONG).show()
                }else{
                    binding.btnSend.playAnimation()
                    stopListen()
                    playSpeak()
                    Toast.makeText(requireContext(), "SPEAK ON & LISTEN OFF", Toast.LENGTH_SHORT).show()
                }

            } else {
                binding.btnSend.pauseAnimation()
                playListen()
                stopSpeak()
                Toast.makeText(requireContext(), "SPEAK OFF & LISTEN ON", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun playListen() {
        mRxManager.listen()
        makeCardData()
    }

    private fun makeCardData() {
        mRxManager.acousticSensor = AcousticSensor { card ->
            Log.i("RESULT_TEXT", card)
            var name = ""
            var tel = ""
            var email = ""
            var address = ""
            var bgPosition = 0
            card.split("/").forEachIndexed { idx, split ->
                when (idx) {
                    0 -> name = split
                    1 -> tel = split
                    2 -> email = split
                    3 -> address = split
                    4 -> bgPosition = split.toInt()
                }
            }
            val cardData = Card(
                null,
                name,
                tel,
                email,
                address,
                bgPosition
            )
            Log.i(
                "CardData",
                name + " " + tel + " " + email + " " + address + " " + bgPosition.toString()
            )

            saveCardIntoCardBox(cardData)
        }
    }

    private fun saveCardIntoCardBox(card: Card) {
        Thread(Runnable {
            db.cardDao().insertCard(card)
        }).start()

        if (activity != null)
            Toast.makeText(requireActivity(), "SAVE CARD SUCCESS!", Toast.LENGTH_LONG).show()
    }

    private fun stopListen() {
        mRxManager.finish()
    }

    private fun playSpeak() {
        speakText += binding.etName.text.toString() +
                "/" + binding.etNumber.text.toString() +
                "/" + binding.etEmail.text.toString() +
                "/" + binding.etAddress.text.toString() +
                "/" + saveBackgroundPosition
        Log.i("MakeCardFragment", speakText)
        // mTxManager.setSystemVolumeMax(requireContext())
        mTxManager.euInitTransmit(speakText)
        mTxManager.process(-1)

        isSpeak = false
    }

    private fun stopSpeak() {
        speakText = ""
        mTxManager.stop()
        isSpeak = true
    }

    private fun initDB() {
        db = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java, "here-is-my-card-db"
        ).build()
    }

    private fun requestAudioPermission() {
        requestPermissions(requiredPermission, REQUEST_RECORD_AUDIO_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        val audioRecordPermissionGranted =
            requestCode == REQUEST_RECORD_AUDIO_CODE &&
                    grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED

        if (!audioRecordPermissionGranted) {
            finish()
        } else {
            playListen()
        }
    }

    private fun finish() {
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.remove(this)
            ?.commit()
    }

    override fun onPause() {
        super.onPause()
        stopSpeak()
        stopListen()
        binding.btnSend.pauseAnimation()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        private val REQUEST_RECORD_AUDIO_CODE = 100
    }
}