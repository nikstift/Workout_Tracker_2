package com.example.workouttracker2

import android.app.Application
import com.example.workouttracker2.database.AppDatabase
import com.example.workouttracker2.repository.ExerciseDao
import com.example.workouttracker2.repository.SetDao
import com.example.workouttracker2.repository.WorkoutDao

class MyAppApplication : Application() {
    lateinit var exerciseDao: ExerciseDao
    lateinit var setDao: SetDao
    lateinit var workoutDao: WorkoutDao

    override fun onCreate() {
        super.onCreate()

        val myAppDatabase = AppDatabase.getDatabase(applicationContext)
        exerciseDao = myAppDatabase.exerciseDao()
        setDao = myAppDatabase.setDao()
        workoutDao = myAppDatabase.workoutDao()

    }
}