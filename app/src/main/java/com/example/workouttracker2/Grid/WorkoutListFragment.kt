package com.example.workouttracker2.Grid

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workouttracker2.R
import com.example.workouttracker2.Workout
import com.google.android.material.floatingactionbutton.FloatingActionButton


class WorkoutListFragment : Fragment(R.layout.fragment_workout_list) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var workoutAdapter: WorkoutListAdapter
    private val workoutListViewModel: WorkoutListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
        val fabButton = view.findViewById<FloatingActionButton>(R.id.addWorkoutButton)
        fabButton.setOnClickListener {
            showWorkoutNameDialog()
        }
    }
    private fun setupList() {
        // Initialisierung des RecyclerView und des Adapters
        recyclerView = requireView().findViewById(R.id.rvWorkouts)
        recyclerView.layoutManager = LinearLayoutManager(context)
        workoutAdapter = WorkoutListAdapter(listOf())
        recyclerView.adapter = workoutAdapter

        // Beobachten der LiveData aus dem ViewModel
        workoutListViewModel.readAllWorkout().observe(viewLifecycleOwner, { workout ->
            workoutAdapter.updateWorkout(workout)
        })

    }




    private fun showWorkoutNameDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Neues Workout hinzufügen")

        val input = EditText(requireContext())
        input.hint = "Workout eingeben"
        builder.setView(input)
        builder.setPositiveButton("Hinzufügen") { dialog, _ ->
            val workoutName = input.text.toString().trim()
            val newWorkout = Workout(name = workoutName)
            if (workoutName.isNotEmpty()) {
                workoutListViewModel.addWorkout(newWorkout)
                dialog.dismiss()
            } else {
                Toast.makeText(requireContext(), "Bitte eine Workout eingeben", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("Abbrechen") { dialog, _ ->
            dialog.cancel()
        }
        builder.show()
    }
}
