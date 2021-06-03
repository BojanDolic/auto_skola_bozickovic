package com.autoskola.bozickovic.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.autoskola.bozickovic.R
import com.autoskola.bozickovic.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        NavigationUI.setupWithNavController(binding.toolbar, findNavController())

        binding.kontaktBtn.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToKontaktFragment())
        }

        binding.onamaBtn.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToAboutFragment())
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}