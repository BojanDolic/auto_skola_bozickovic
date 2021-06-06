package com.autoskola.bozickovic.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.autoskola.bozickovic.Constants
import com.autoskola.bozickovic.R
import com.autoskola.bozickovic.database.PitanjaDatabase
import com.autoskola.bozickovic.databinding.FragmentSelectTestBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectTestFragment : Fragment() {

    private var _binding: FragmentSelectTestBinding? = null
    private val binding get() = _binding!!

    val args: SelectTestFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSelectTestBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        NavigationUI.setupWithNavController(binding.toolbar, findNavController())

        binding.testTeorijeContainer.setOnClickListener {
            findNavController().navigate(
                SelectTestFragmentDirections.actionSelectTestFragmentToTestFragment(
                    args.kategorija,
                    Constants.CONSTANT_TEORIJA
                )
            )
        }

        binding.testZnakovaContainer.setOnClickListener {
            findNavController().navigate(
                SelectTestFragmentDirections.actionSelectTestFragmentToTestFragment(
                    args.kategorija,
                    Constants.CONSTANT_ZNAK
                )
            )
        }

        binding.testRaskrniceContainer.setOnClickListener {
            findNavController().navigate(
                SelectTestFragmentDirections.actionSelectTestFragmentToTestFragment(
                    args.kategorija,
                    Constants.CONSTANT_RASKRSNICA
                )
            )
        }

        binding.testProbniContainer.setOnClickListener {
            findNavController().navigate(
                SelectTestFragmentDirections.actionSelectTestFragmentToTestFragment(
                    args.kategorija,
                    Constants.CONSTANT_PROBNI
                )
            )
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}