package com.example.workouttracker2

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "exercises",
    foreignKeys = [
        ForeignKey(
            entity = Workout::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("workoutId"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)

class Exercise(
    @PrimaryKey(autoGenerate = true)
    var id: Int =0,
    var workoutId: Int,
    var name: String
) : Serializable

