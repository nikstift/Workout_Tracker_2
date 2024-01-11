package com.example.workouttracker2.ExerciseList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.workouttracker2.Exercise
import com.example.workouttracker2.repository.exerciseRepository

class PushWorkoutListViewModel() : ViewModel() {
    fun readAllPush(): LiveData<List<Exercise>> {
        return exerciseRepository.readAllPush()
    }


}