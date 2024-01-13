package com.example.workouttracker2.ExerciseList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workouttracker2.Exercise
import com.example.workouttracker2.repository.exerciseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PushWorkoutListViewModel() : ViewModel() {
    fun readAllPush(): LiveData<List<Exercise>> {
        return exerciseRepository.readAllExercises()
    }
    fun addExercise(exercise: Exercise) {
        viewModelScope.launch(Dispatchers.IO) {
            exerciseRepository.addExercise(exercise)
        }
    }

    fun addRandomContact(){
        viewModelScope.launch(Dispatchers.IO) {
            exerciseRepository.performLongRunningOperation()
        }
    }
}