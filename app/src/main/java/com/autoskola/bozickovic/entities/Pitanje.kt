package com.autoskola.bozickovic.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pitanja")
data class Pitanje(
    @ColumnInfo(name = "pitanje_id")
    @PrimaryKey(autoGenerate = true)
    val pitanjeId: Long = 0,

    @ColumnInfo(name = "tekst_pitanja")
    val tekstPitanja: String = "",

    @ColumnInfo(name = "vise_odgovora")
    val hasViseOdgovora: Boolean = false,

    @ColumnInfo(name = "odgovori")
    val odgovori: List<String> = listOf(),

    @ColumnInfo(name = "tacni_odgovori")
    val tacniOdgovori: List<String> = listOf(),

    @ColumnInfo(name = "slika_pitanja")
    val slikaPitanja: String = "",

    @ColumnInfo(name = "kategorija")
    val kategorija: String = "",

    @ColumnInfo(name = "tip")
    val tipPitanja: String = ""
)
