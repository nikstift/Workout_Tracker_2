package com.example.workouttracker2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.workouttracker2.repository.ExerciseDao
import com.example.workouttracker2.repository.SetDao
import com.example.workouttracker2.repository.WorkoutDao
import com.google.android.material.bottomnavigation.BottomNavigationView


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


        val navView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        navView.setupWithNavController(navController)
    }
}