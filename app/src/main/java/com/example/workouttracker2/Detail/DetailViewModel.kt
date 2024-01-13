package com.example.workouttracker2.Detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.workouttracker2.Exercise
import com.example.workouttracker2.Set
import com.example.workouttracker2.repository.exerciseRepository

class DetailViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    fun read(): Exercise {
        return savedStateHandle.get<Exercise>("exercise")!!
    }

    fun addSetToExercise(repetitions: Int, weight: Double, notes: String) {
        val exercise = read()
        val exerciseId = exercise.id // Exercise-ID auslesen
        val newSet = Set(exerciseId = exerciseId, repetitions = repetitions, weight = weight, notes =  notes) // Set erstellen

        // Start a coroutine to add the set to the exercise
        exerciseRepository.addSetToExercise(exerciseId, newSet)
    }
}
