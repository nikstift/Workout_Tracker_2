package com.example.workouttracker2

import java.io.Serializable

class Exercise(
    var name: String,
    var sets: MutableList<Set> = mutableListOf()
) : Serializable

fun createPushExercises(exerciseCount: Int): List<Exercise> =
    (1..exerciseCount).map { exerciseNum ->
        Exercise("Push $exerciseNum").apply {
            sets.add(Set(10, 0.1 * exerciseNum, "Set 1 of Push $exerciseNum"))
            sets.add(Set(12, 0.2 * exerciseNum, "Set 2 of Push $exerciseNum"))
        }
    }
