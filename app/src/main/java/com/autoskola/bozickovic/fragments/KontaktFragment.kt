package com.autoskola.bozickovic.fragments

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.autoskola.bozickovic.R
import com.autoskola.bozickovic.databinding.FragmentKontaktBinding
import com.autoskola.bozickovic.databinding.FragmentMainBinding


class KontaktFragment : Fragment() {

    private var _binding: FragmentKontaktBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentKontaktBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        NavigationUI.setupWithNavController(binding.toolbar, findNavController())


        binding.phoneCallBtn.setOnClickListener {
            openPhone()
        }

        binding.emailContactBtn.setOnClickListener {
            openEmail()
        }

        binding.lokacijaBtn.setOnClickListener {
            openGoogleMaps()
        }

    }

    /**
     * Function which opens google maps application with specified location
     */

    private fun openGoogleMaps() {
        val locationUri = Uri.parse("geo:44.735576, 18.088789?q=Auto škola \"Božičković")
        val mapIntent = Intent(Intent.ACTION_VIEW, locationUri)
        mapIntent.setPackage("com.google.android.apps.maps")

        try {
            startActivity(mapIntent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(requireContext(), "Greška pri otvaranju aplikacije", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Function that opens email application and opens send screen
     */
    private fun openEmail() {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:autoskolabozickovic2021@gmail.com")
        }

        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(requireContext(), "Greška pri otvaranju aplikacije", Toast.LENGTH_SHORT).show()
        }

    }

    /**
     * Function that opens phone application with specified phone number
     */
    private fun openPhone() {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            setData(Uri.parse("tel:065566600"))
        }
        startActivity(intent)
    }

}