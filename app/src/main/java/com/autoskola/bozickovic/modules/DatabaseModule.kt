package com.autoskola.bozickovic.modules

import android.content.Context
import androidx.room.Room
import com.autoskola.bozickovic.dao.PitanjaDAO
import com.autoskola.bozickovic.database.PitanjaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): PitanjaDatabase {
        return Room.databaseBuilder(
            context,
            PitanjaDatabase::class.java,
            "autoskola_bozickovic_baza.db"
        )
            .allowMainThreadQueries()
            .createFromAsset("database/autoskola_baza.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun providePitanjaDao(db: PitanjaDatabase): PitanjaDAO {
        return db.wordDao()
    }

}