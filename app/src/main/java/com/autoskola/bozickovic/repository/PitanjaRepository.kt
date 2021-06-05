package com.autoskola.bozickovic.repository

import com.autoskola.bozickovic.dao.PitanjaDAO
import com.autoskola.bozickovic.entities.Pitanje
import javax.inject.Inject

class PitanjaRepository @Inject constructor(
    val pitanjaDao: PitanjaDAO
) {

    fun getTeorijskaPitanja(tip: String, kategorija: String): List<Pitanje> {
        return pitanjaDao.getTeorijskaPitanja(tip, kategorija)
    }



}