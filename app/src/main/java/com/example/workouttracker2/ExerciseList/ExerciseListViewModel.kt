package com.example.workouttracker2.ExerciseList

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.workouttracker2.Exercise
import com.example.workouttracker2.Workout
import com.example.workouttracker2.repository.exerciseRepository

class ExerciseListViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    fun readAllPush(): LiveData<List<Exercise>> {
        return exerciseRepository.readAllExercises()
    }

    fun readWorkoutExercises(workoutId: Int): LiveData<List<Exercise>>{
        return exerciseRepository.readWorkoutExercises(workoutId)
    }
    fun addExerciseToWorkout(name:String) {
        val workout = read()
        val workoutId = workout.id // Exercise-ID auslesen
        val newExercise = Exercise(name = name, workoutId = workoutId) // Set erstellen
        exerciseRepository.addExercise(newExercise)
    }

    fun read(): Workout {
        return savedStateHandle.get<Workout>("workout")!!
    }

}