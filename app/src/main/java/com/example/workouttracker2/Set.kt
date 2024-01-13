package com.example.workouttracker2

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "sets",
    foreignKeys = [
        ForeignKey(
            entity = Exercise::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("exerciseId"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Set (
    @PrimaryKey(autoGenerate = true)
    var setId: Int = 0,
    var exerciseId: Int,
    var repetitions: Int,
    var weight: Double,
    var notes: String
)
