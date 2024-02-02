package com.example.workouttracker2.WorkoutList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workouttracker2.R
import com.example.workouttracker2.Workout
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton


class WorkoutListFragment : Fragment(R.layout.fragment_workout_list) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var workoutAdapter: WorkoutListAdapter
    private val workoutListViewModel: WorkoutListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
        setupSwipeToDelete()

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


    private fun setupSwipeToDelete() {
        val swipeToDeleteCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false // Bewegungen werden nicht unterstützt
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val workoutToDelete = workoutAdapter.getWorkoutAtPosition(position)

                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Löschen bestätigen")
                    .setMessage("Möchten Sie dieses Workout wirklich löschen?")
                    .setNegativeButton("Abbrechen") { dialog, _ ->
                        workoutAdapter.notifyItemChanged(position)
                        dialog.dismiss()
                    }
                    .setPositiveButton("Löschen") { dialog, _ ->
                        workoutListViewModel.deleteWorkout(workoutToDelete)
                        dialog.dismiss()
                    }
                    .show()
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun showWorkoutNameDialog() {
        // Initialisierung des MaterialAlertDialogBuilder
        val builder = MaterialAlertDialogBuilder(requireContext())
        builder.setTitle("Neues Workout hinzufügen")

        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.custom_dialog, null)
        val input = dialogView.findViewById<EditText>(R.id.dialog_custom_input)

        builder.setView(dialogView)

        // Hinzufügen der Aktionstasten und ihrer Logik
        builder.setPositiveButton("Hinzufügen") { dialog, _ ->
            val workoutName = input.text.toString().trim()
            if (workoutName.isNotEmpty()) {
                val newWorkout = Workout(name = workoutName)
                workoutListViewModel.addWorkout(newWorkout)
                dialog.dismiss()
            } else {
                Toast.makeText(requireContext(), "Bitte ein Workout eingeben", Toast.LENGTH_SHORT).show()
            }
        }

        builder.setNegativeButton("Abbrechen") { dialog, _ ->
            dialog.cancel()
        }

        // Anzeigen des Dialogs
        builder.show()
    }

}
