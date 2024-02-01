package com.example.workouttracker2.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.workouttracker2.Workout

@Dao
interface WorkoutDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkout(workout: Workout)

    @Update
    fun updateWorkout(workout: Workout)

    @Delete
    fun deleteWorkout(workout: Workout)

    @Query("SELECT * FROM workouts")
    fun findAllWorkouts(): LiveData<List<Workout>>
}