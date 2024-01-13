package com.example.workouttracker2.repository

import androidx.lifecycle.LiveData
import com.example.workouttracker2.Exercise
import com.example.workouttracker2.Set
import com.example.workouttracker2.exerciseDao
import com.example.workouttracker2.setDao

var exerciseRepository = ExerciseRepository()
class ExerciseRepository() {

    fun readExerciseById(exerciseId: Int): Exercise {
        return exerciseDao.findExerciseById(exerciseId)
    }

    fun updateExercise(exercise: Exercise) {
        exerciseDao.updateExercise(exercise)
    }

    fun readAllExercises(): LiveData<List<Exercise>> {
        return exerciseDao.findAllExercises()
    }


    suspend fun addExercise(exercise: Exercise) {
        exerciseDao.insertExercise(exercise)
    }

    fun addSetToExercise(exerciseId: Int, set: Set) {
        set.exerciseId = exerciseId // Legen Sie den Fremdschlüssel für das Set fest
        setDao.insertSet(set)
    }
    fun findSetsForExercise(exerciseId: Int): LiveData<List<Set>> {
        return setDao.findSetsForExercise(exerciseId)
    }
}
