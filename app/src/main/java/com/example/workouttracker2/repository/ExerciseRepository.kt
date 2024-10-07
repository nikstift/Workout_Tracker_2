package com.example.workouttracker2.repository

import androidx.lifecycle.LiveData
import com.example.workouttracker2.Exercise
import com.example.workouttracker2.Set
import com.example.workouttracker2.Workout
import com.example.workouttracker2.exerciseDao
import com.example.workouttracker2.setDao
import com.example.workouttracker2.workoutDao

var exerciseRepository = ExerciseRepository()
class ExerciseRepository() {

    fun updateExercise(exercise: Exercise) {
        exerciseDao.updateExercise(exercise)
    }

    fun readAllExercises(): LiveData<List<Exercise>> {
        return exerciseDao.findAllExercises()
    }

    fun readWorkoutExercises(workoutId:Int):LiveData<List<Exercise>>{
        return exerciseDao.findExercisesForWorkout(workoutId)
    }


    fun addExercise(exercise: Exercise) {
        exerciseDao.insertExercise(exercise)
    }

    fun addSetToExercise(set: Set) {
        setDao.insertSet(set)
    }
    fun findSetsForExercise(exerciseId: Int): LiveData<List<Set>> {
        return setDao.findSetsForExercise(exerciseId)
    }

    fun deleteSet(set: Set){
        setDao.deleteSet(set)
    }

    fun deleteExercise(exercise: Exercise){
        exerciseDao.deleteExercise(exercise)
    }

    fun readAllWorkout():LiveData<List<Workout>>{
        return workoutDao.findAllWorkouts()
    }

    fun addWorkout(workout: Workout){
        workoutDao.insertWorkout(workout)
    }

    fun deleteWorkout(workout: Workout){
        workoutDao.deleteWorkout(workout)
    }

}
