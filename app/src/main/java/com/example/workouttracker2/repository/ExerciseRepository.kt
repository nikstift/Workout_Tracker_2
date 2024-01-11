package com.example.workouttracker2.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.workouttracker2.Exercise
import com.example.workouttracker2.Set
import com.example.workouttracker2.createPushExercises

var exerciseRepository = ExerciseRepository()

class ExerciseRepository {
    private val exercises: MutableLiveData<List<Exercise>> = MutableLiveData(
        createPushExercises(1)
    )

    fun readAllPush(): LiveData<List<Exercise>> {
        return exercises
    }

    fun createExercise(exercise: Exercise) {
        val currentExercises = exercises.value?.toMutableList() ?: mutableListOf()
        currentExercises.add(exercise)
        exercises.value = currentExercises
    }



    fun addSetToExercise(exerciseName: String, newSet: Set) {
        val currentExercises = exercises.value ?: return

        val updatedExercises = currentExercises.map { exercise ->
            if (exercise.name == exerciseName) {
                // Aktualisiere die Sets der gefundenen Übung
                val updatedSets = exercise.sets.toMutableList()
                updatedSets.add(newSet)
                exercise.sets = updatedSets
                exercise
            } else {
                exercise
            }
        }

        exercises.value = updatedExercises
    }
}
