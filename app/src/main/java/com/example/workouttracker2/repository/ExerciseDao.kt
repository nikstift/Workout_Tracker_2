package com.example.workouttracker2.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.workouttracker2.Exercise

@Dao
interface ExerciseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExercise(exercise: Exercise)

    @Update
    fun updateExercise(exercise: Exercise)

    @Delete
    fun deleteExercise(exercise: Exercise)

    @Query("SELECT * FROM exercises")
    fun findAllExercises(): LiveData<List<Exercise>>

    @Query("SELECT * FROM exercises WHERE id = :exerciseId")
    fun findExerciseById(exerciseId: Int): Exercise
}
