package com.example.workouttracker2.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.workouttracker2.Exercise
import com.example.workouttracker2.Set
import com.example.workouttracker2.createPushExercises

var exerciseRepository = ExerciseRepository()

class ExerciseRepository {
    private val exercises: MutableLiveData<List<Exercise>> = MutableLiveData(
        createPushExercises(50)
    )

    fun readAllPush(): LiveData<List<Exercise>> {
        return exercises
    }


    fun addSetToExercise(exerciseName: String, newSet: Set) {
        val currentList = exercises.value ?: emptyList()
        val updatedList = currentList + createPushExercises(1)
        exercises.value = updatedList
    }
}
