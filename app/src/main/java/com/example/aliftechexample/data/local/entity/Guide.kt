package com.example.aliftechexample.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "guide_table")
data class Guide(
    val url: String,
    val startDate: String,
    val endDate: String,
    val name: String,
    val icon: String,
    val objType: String,
    val loginRequired: Boolean,
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0
) {
}