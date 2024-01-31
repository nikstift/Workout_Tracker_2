package com.example.workouttracker2

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "workouts")
class Workout (
    @PrimaryKey(autoGenerate = true)
    var id: Int =0,
    var name: String
)
