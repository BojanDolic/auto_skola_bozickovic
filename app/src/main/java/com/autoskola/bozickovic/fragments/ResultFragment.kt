package com.autoskola.bozickovic.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.autoskola.bozickovic.R
import com.autoskola.bozickovic.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    val args: ResultFragmentArgs by navArgs()

    var postotakOdgovora = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        NavigationUI.setupWithNavController(binding.toolbar, findNavController())

        postotakOdgovora = (args.tacnihOdgovora / args.ukupnoPitanja) * 100f

        binding.rezultatProgressBar.progress = postotakOdgovora.toInt()
        binding.rezultatProgressText.text = resources.getString(R.string.postotak_Text, postotakOdgovora.toInt())

        binding.rezultatKategorijaText.text = resources.getString(R.string.kategorija_text, args.kategorija)
        binding.rezultatPitanjaText.text = resources.getString(R.string.broj_pitanja_text, args.ukupnoPitanja)
        binding.rezultatTacnihOdgovoraText.text = resources.getString(R.string.tacnih_odgovora_text, args.tacnihOdgovora)

        if(postotakOdgovora >= 90)
            binding.rezultatProlazText.text = resources.getString(R.string.prosao_ispit_text)
        else binding.rezultatProlazText.text = resources.getString(R.string.pao_ispit_text)


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}