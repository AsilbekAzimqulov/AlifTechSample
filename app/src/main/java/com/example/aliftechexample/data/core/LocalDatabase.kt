package com.example.aliftechexample.data.core

import android.content.Context
import androidx.room.Room
import com.example.aliftechexample.data.local.GuideDatabase

object LocalDatabase {

    var database: GuideDatabase? = null

    fun init(context: Context) {

        if (database == null) {
            database = Room.databaseBuilder(
                context,
                GuideDatabase::class.java,
                "guide_test"
            )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    fun getInstanceDatabase(): GuideDatabase {
        return database ?: throw IllegalStateException()
    }

}