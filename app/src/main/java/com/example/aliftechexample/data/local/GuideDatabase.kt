package com.example.aliftechexample.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.aliftechexample.data.local.dao.GuideDao
import com.example.aliftechexample.data.local.entity.Guide

@Database(
    entities = [Guide::class],
    version = 1
)
abstract class GuideDatabase : RoomDatabase() {
    abstract fun getGuideDao(): GuideDao
}