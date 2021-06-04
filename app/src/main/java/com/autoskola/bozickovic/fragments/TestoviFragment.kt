package com.autoskola.bozickovic.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.autoskola.bozickovic.R
import com.autoskola.bozickovic.databinding.FragmentMainBinding
import com.autoskola.bozickovic.databinding.FragmentTestoviBinding


class TestoviFragment : Fragment() {

    private var _binding: FragmentTestoviBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTestoviBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.kategorijaAContainer.setOnClickListener {
            findNavController().navigate(TestoviFragmentDirections.actionTestoviFragmentToSelectTestFragment("A"))
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}