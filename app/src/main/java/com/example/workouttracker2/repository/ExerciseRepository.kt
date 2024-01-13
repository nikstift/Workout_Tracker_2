package com.example.workouttracker2.repository

import androidx.lifecycle.LiveData
import com.example.workouttracker2.Exercise
import com.example.workouttracker2.Set

class ExerciseRepository(private val exerciseDao: ExerciseDao, private val setDao: SetDao) {

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

    suspend fun addSetToExercise(exerciseId: Int, set: Set) {
        set.exerciseId = exerciseId // Legen Sie den Fremdschlüssel für das Set fest
        setDao.insertSet(set)
    }
}
