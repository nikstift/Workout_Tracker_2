package com.example.workouttracker2.Detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.workouttracker2.Exercise
import com.example.workouttracker2.Set
import com.example.workouttracker2.repository.exerciseRepository

class DetailViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    fun read(): Exercise {
        return savedStateHandle.get("exercise")!!
        Log.d("DetailViewModel", "\"read(): Exercise is called")
    }

    fun getLastSet(): Set? {
        val exercise = read()
        return if (exercise.sets.isNotEmpty()) exercise.sets.last() else null
    }


    fun addSetToExercise(exerciseName: String, set: Set) {
        exerciseRepository.addSetToExercise(exerciseName, set)
    }
}