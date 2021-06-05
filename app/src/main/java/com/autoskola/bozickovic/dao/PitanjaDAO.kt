package com.autoskola.bozickovic.dao

import androidx.room.Dao
import androidx.room.Query
import com.autoskola.bozickovic.entities.Pitanje

@Dao
interface PitanjaDAO {

    @Query("SELECT * FROM pitanja WHERE kategorija LIKE '%C%'")
    fun getAllPitanjaKategorijaC(): List<Pitanje>


    @Query("SELECT * FROM pitanja WHERE tip LIKE :tip AND kategorija LIKE '%' || :kategorija || '%' ORDER BY RANDOM() LIMIT 20")
    fun getTeorijskaPitanja(tip: String, kategorija: String): List<Pitanje>
}