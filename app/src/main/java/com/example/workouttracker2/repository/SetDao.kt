package com.example.workouttracker2.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.workouttracker2.Set

@Dao
interface SetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSet(set: Set)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSets(sets: List<Set>)

    @Update
    fun updateSet(set: Set)

    @Delete
    fun deleteSet(set: Set)

    @Query("SELECT * FROM sets WHERE exerciseId = :exerciseId")
    fun findSetsForExercise(exerciseId: Int): List<Set>
}
