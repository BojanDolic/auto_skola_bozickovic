package com.autoskola.bozickovic.viewmodels

import androidx.lifecycle.ViewModel
import com.autoskola.bozickovic.Constants
import com.autoskola.bozickovic.entities.Pitanje
import com.autoskola.bozickovic.repository.PitanjaRepository
import com.google.android.material.checkbox.MaterialCheckBox
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    val repo: PitanjaRepository
) : ViewModel() {

    var showingResults = false
    var brojPitanja = 0
    var trenutniBrojPitanja = 0

    val odgovori: ArrayList<MaterialCheckBox> = arrayListOf()

    lateinit var pitanje: Pitanje // Trenutno pitanje
    var pitanja: List<Pitanje> = listOf()

    /**
     * Function used to check if any of the checkboxes is selected
     *
     * @return returns boolean if it is checked or not
     */
    fun isOdgovoriChecked(): Boolean {
        for (odgovorCheckBox in odgovori)
            if (odgovorCheckBox.isChecked)
                return true
        return false
    }

    fun getTeorijskaPitanja(kategorija: String, tip: String): List<Pitanje> {
        return if(tip == Constants.CONSTANT_PROBNI) // Izabran probni test
            repo.getProbniTestPitanja(kategorija, getTestLimit(kategorija))
        else repo.getTeorijskaPitanja(tip, kategorija)
    }

    private fun getTestLimit(kategorija: String): Int {
        return if(kategorija == Constants.KATEGORIJA_B || kategorija == Constants.KATEGORIJA_A)
            20
        else 30
    }

}