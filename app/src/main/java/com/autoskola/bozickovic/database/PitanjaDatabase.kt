package com.autoskola.bozickovic.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.autoskola.bozickovic.converters.RoomConverters
import com.autoskola.bozickovic.dao.PitanjaDAO
import com.autoskola.bozickovic.entities.Pitanje

@Database(entities = [Pitanje::class], version = 1, exportSchema = false)
@TypeConverters(RoomConverters::class)
abstract class PitanjaDatabase : RoomDatabase() {

    abstract fun wordDao(): PitanjaDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: PitanjaDatabase? = null

        fun getDatabase(context: Context): PitanjaDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PitanjaDatabase::class.java,
                    "autoskola_bozickovic_baza.db"
                ).createFromAsset("database/autoskola_baza.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()

                INSTANCE = instance
                // return instance
                instance
            }
        }
    }


}