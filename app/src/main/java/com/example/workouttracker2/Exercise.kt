package com.example.workouttracker2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
class Exercise(
    @PrimaryKey(autoGenerate = true)
    var id: Int =0,
    var name: String
)

