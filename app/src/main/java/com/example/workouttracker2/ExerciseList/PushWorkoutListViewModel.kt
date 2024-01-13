package com.example.workouttracker2.ExerciseList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workouttracker2.Exercise
import com.example.workouttracker2.repository.exerciseRepository
import kotlinx.coroutines.launch

class PushWorkoutListViewModel() : ViewModel() {
    fun readAllPush(): LiveData<List<Exercise>> {
        return exerciseRepository.readAllExercises()
    }
    fun addExercise(exercise: Exercise) {
        viewModelScope.launch {
            exerciseRepository.addExercise(exercise)
        }
    }
}