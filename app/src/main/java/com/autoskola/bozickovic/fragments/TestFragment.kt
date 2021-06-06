package com.autoskola.bozickovic.fragments

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import coil.load
import com.autoskola.bozickovic.R
import com.autoskola.bozickovic.dao.PitanjaDAO
import com.autoskola.bozickovic.databinding.OdgovorCheckboxBinding
import com.autoskola.bozickovic.databinding.TestFragmentBinding
import com.autoskola.bozickovic.entities.Pitanje
import com.autoskola.bozickovic.viewmodels.TestViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TestFragment : Fragment() {

    private var _binding: TestFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: TestViewModel

    val args: TestFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TestFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(TestViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        NavigationUI.setupWithNavController(binding.toolbar, findNavController())

        viewModel.pitanja = viewModel.getTeorijskaPitanja(args.kategorija, args.tip)
        viewModel.brojPitanja = viewModel.pitanja.size

        loadPitanje() // Initial load

        binding.provjeriOdgovoreBtn.setOnClickListener {
            if (!viewModel.showingResults)
                provjeriOdgovore(viewModel.pitanje)
            else nextQuestion()
        }

    }

    fun provjeriOdgovore(pitanje: Pitanje) {
        if (viewModel.isOdgovoriChecked()) {
            checkAnswers(pitanje)
        } else Toast.makeText(
            requireContext(),
            "Morate izabrati odgovor",
            Toast.LENGTH_SHORT
        ).show()
    }

    /**
     * Function checks for correct answers
     * @param pitanje Question object
     */
    fun checkAnswers(pitanje: Pitanje) {
        var correctAnswer = true

        viewModel.odgovori.forEachIndexed { index, materialCheckBox ->
            if (pitanje.tacniOdgovori.contains(index) && !materialCheckBox.isChecked
                || !pitanje.tacniOdgovori.contains(index) && materialCheckBox.isChecked
            ) {
                correctAnswer = false
                return@forEachIndexed
            }
        }

        // Add points if question is answered correctly
        if(correctAnswer)
            viewModel.tacnihOdgovora += viewModel.getPointsBasedOnPitanje(viewModel.pitanje.tipPitanja)

        showAnswers(pitanje)

    }

    /**
     * Function used to load pitanje
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    fun loadPitanje() {
        if (viewModel.trenutniBrojPitanja == viewModel.brojPitanja) {

            findNavController().navigate(
                TestFragmentDirections.actionTestFragmentToResultFragment(
                    viewModel.tacnihOdgovora,
                    viewModel.brojPitanja,
                    args.kategorija
                )
            )
        }
        else {
            viewModel.pitanje = viewModel.pitanja[viewModel.trenutniBrojPitanja]

            if(viewModel.pitanje.hasViseOdgovora)
                binding.pitanjeTekstPitanja.text = "${viewModel.pitanje.tekstPitanja}\n[VIŠE ODGOVORA]"
            else binding.pitanjeTekstPitanja.text = viewModel.pitanje.tekstPitanja

            viewModel.pitanje.slikaPitanja.let { slika ->
                if (slika.isNotEmpty()) {
                    binding.pitanjeSlika.isVisible = true
                    val drawableId = resources.getIdentifier(
                        viewModel.pitanje.slikaPitanja,
                        "drawable",
                        requireContext().packageName
                    )
                    binding.pitanjeSlika.load(
                        resources.getDrawable(
                            drawableId,
                            requireContext().theme
                        )
                    )
                } else binding.pitanjeSlika.isVisible = false
            }

            populateOdgovore(viewModel.pitanje)
        }
    }

    /**
     * Function used to get data for next question
     */
    fun nextQuestion() {

        viewModel.showingResults = false

        viewModel.trenutniBrojPitanja++
        binding.pitanjeOdgovoriInflater.removeAllViews()
        binding.provjeriOdgovoreBtn.text = getString(R.string.provjeri_odgovor_text)
        loadPitanje()
    }

    /**
     * Function used to show correct answers
     * @param pitanje Question object
     */
    fun showAnswers(pitanje: Pitanje) {

        viewModel.showingResults = true

        viewModel.odgovori.forEach {
            it.isEnabled = false
        }

        pitanje.tacniOdgovori.forEachIndexed { _, int ->
            viewModel.odgovori[int].setBackgroundColor(
                resources.getColor(
                    R.color.green_alpha50,
                    requireContext().theme
                )
            )
        }

        viewModel.odgovori.clear()
        binding.provjeriOdgovoreBtn.text = getString(R.string.nastavi_text)


    }

    fun populateOdgovore(pitanje: Pitanje) {
        for (odgovor in pitanje.odgovori) {

            val odgovorBind = OdgovorCheckboxBinding.inflate(
                layoutInflater,
                binding.pitanjeOdgovoriInflater,
                false
            )

            odgovorBind.odgovor.text = odgovor
            viewModel.odgovori.add(odgovorBind.odgovor)

            binding.pitanjeOdgovoriInflater.addView(odgovorBind.root)

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}