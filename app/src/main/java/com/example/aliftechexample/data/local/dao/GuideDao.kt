package com.example.aliftechexample.data.local.dao

import androidx.room.*
import com.example.aliftechexample.data.local.entity.Guide
import kotlinx.coroutines.flow.Flow

@Dao
abstract class GuideDao {
    @Query("select * from guide_table")
    abstract fun getGuide(): List<Guide>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(guide: Guide)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(guide: List<Guide>)

    @Query("DELETE FROM guide_table")
    abstract fun clear()

    @Transaction
    open fun update(items: List<Guide>) {
        clear()
        insert(items)
    }
}