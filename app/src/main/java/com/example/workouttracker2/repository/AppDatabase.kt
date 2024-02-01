package com.example.workouttracker2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.workouttracker2.Exercise
import com.example.workouttracker2.Set
import com.example.workouttracker2.Workout
import com.example.workouttracker2.repository.ExerciseDao
import com.example.workouttracker2.repository.SetDao
import com.example.workouttracker2.repository.WorkoutDao

@Database(entities = [Workout::class,Exercise::class, Set::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun exerciseDao(): ExerciseDao
    abstract fun setDao(): SetDao
    abstract fun workoutDao(): WorkoutDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "workout_tracker_database"
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
