package com.example.workouttracker2.Grid

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.workouttracker2.R

class WorkoutListFragment : Fragment(R.layout.fragment_workout_list)
{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnPushWorkout = view.findViewById<Button>(R.id.btn_push_workout)
        btnPushWorkout.setOnClickListener {

            findNavController().navigate(WorkoutListFragmentDirections.actionToPushWorkoutListFragment("Push Workout"))
        }
    }
}
