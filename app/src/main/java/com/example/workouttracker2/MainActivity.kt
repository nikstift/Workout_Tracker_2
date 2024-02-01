package com.example.workouttracker2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.workouttracker2.repository.ExerciseDao
import com.example.workouttracker2.repository.SetDao
import com.example.workouttracker2.repository.WorkoutDao


lateinit var exerciseDao: ExerciseDao
lateinit var setDao: SetDao
lateinit var workoutDao: WorkoutDao

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myAppApplication = application as MyAppApplication
        exerciseDao = myAppApplication.exerciseDao
        setDao = myAppApplication.setDao
        workoutDao = myAppApplication.workoutDao
    }
}