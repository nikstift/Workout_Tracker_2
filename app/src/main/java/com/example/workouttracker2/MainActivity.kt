package com.example.workouttracker2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.workouttracker2.database.AppDatabase
import com.example.workouttracker2.repository.ExerciseDao
import com.example.workouttracker2.repository.SetDao

class MainActivity : AppCompatActivity() {

    private lateinit var exerciseDao: ExerciseDao
    private lateinit var setDao: SetDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialisierung der Datenbank
        val appDatabase = AppDatabase.getDatabase(this)

        // Initialisierung der DAOs
        exerciseDao = appDatabase.exerciseDao()
        setDao = appDatabase.setDao()
    }
}