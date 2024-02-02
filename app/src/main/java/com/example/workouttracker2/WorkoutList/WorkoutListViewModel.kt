package com.example.workouttracker2.WorkoutList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workouttracker2.Workout
import com.example.workouttracker2.repository.exerciseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkoutListViewModel(): ViewModel() {
    fun readAllWorkout(): LiveData<List<Workout>> {
        return exerciseRepository.readAllWorkout()
    }
    fun addWorkout(workout: Workout) {
        viewModelScope.launch(Dispatchers.IO) {
            exerciseRepository.addWorkout(workout)
        }
    }

    fun deleteWorkout (workout: Workout){
        exerciseRepository.deleteWorkout(workout)
    }
}