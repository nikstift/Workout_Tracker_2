package com.example.workouttracker2.History

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.workouttracker2.Exercise
import com.example.workouttracker2.Set
import com.example.workouttracker2.repository.exerciseRepository

class HistoryViewModel (private val savedStateHandle: SavedStateHandle) : ViewModel() {
    fun read(): Exercise {
        return savedStateHandle.get("exercise")!!
    }

    fun getExercise(exerciseId: Int): LiveData<List<Set>> {
        return exerciseRepository.findSetsForExercise(exerciseId)
    }
}