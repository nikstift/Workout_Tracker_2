package com.example.workouttracker2

import android.app.Application
import com.example.workouttracker2.database.AppDatabase
import com.example.workouttracker2.repository.ExerciseDao
import com.example.workouttracker2.repository.SetDao
import com.example.workouttracker2.repository.WorkoutDao
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MyAppApplication : Application() {
    lateinit var exerciseDao: ExerciseDao
    lateinit var setDao: SetDao
    lateinit var workoutDao: WorkoutDao
    lateinit var firestoreDb: FirebaseFirestore

    override fun onCreate() {
        super.onCreate()

        // initalise local DB
        val myAppDatabase = AppDatabase.getDatabase(applicationContext)
        exerciseDao = myAppDatabase.exerciseDao()
        setDao = myAppDatabase.setDao()
        workoutDao = myAppDatabase.workoutDao()

        //initialise firestore
        firestoreDb = Firebase.firestore

    }
}