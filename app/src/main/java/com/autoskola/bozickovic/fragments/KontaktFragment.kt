package com.autoskola.bozickovic.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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


    }

    /**
     * Function that opens email application and opens send screen
     */
    fun openEmail() {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            setType("message/rfc822")
                setData(Uri.parse("mailto:autoskolabozickovic2021@gmail.com"))
        }
        startActivity(Intent.createChooser(intent, "Izaberite aplikaciju"))
    }

    /**
     * Function that opens phone application with specified phone number
     */
    fun openPhone() {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            setData(Uri.parse("tel:065566600"))
        }
        startActivity(intent)
    }

}