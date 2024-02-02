package com.example.workouttracker2.ExerciseList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workouttracker2.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ExerciseListFragment : Fragment(R.layout.fragment_excercise_list) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var exerciseAdapter: ExerciseListAdapter
    private val exerciseListViewModel: ExerciseListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val workout = exerciseListViewModel.read()
        view.findViewById<TextView>(R.id.workoutName).text = workout.name
        setupList()
        val fabButton = view.findViewById<FloatingActionButton>(R.id.addExerciseButton)
        fabButton.setOnClickListener {
            showExerciseNameDialog()
        }
    }
    private fun setupList() {
        // Initialisierung des RecyclerView und des Adapters
        recyclerView = requireView().findViewById(R.id.rvExercises)
        recyclerView.layoutManager = LinearLayoutManager(context)
        exerciseAdapter = ExerciseListAdapter(listOf())
        recyclerView.adapter = exerciseAdapter

        // Beobachten der LiveData aus dem ViewModel
        exerciseListViewModel.readWorkoutExercises(exerciseListViewModel.read().id).observe(viewLifecycleOwner, { exercises ->
            exerciseAdapter.updateExercises(exercises)
        })

    }




    private fun showExerciseNameDialog() {
        val builder = MaterialAlertDialogBuilder(requireContext())
        builder.setTitle("Neue Übung hinzufügen")


        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.custom_dialog, null)
        val input = dialogView.findViewById<EditText>(R.id.dialog_custom_input)
        builder.setView(dialogView)

        builder.setPositiveButton("Hinzufügen") { dialog, _ ->
            val exerciseName = input.text.toString().trim()
            if (exerciseName.isNotEmpty()) {
                exerciseListViewModel.addExerciseToWorkout(exerciseName)
                dialog.dismiss()
            } else {
                Toast.makeText(requireContext(), "Bitte einen Übungsnamen eingeben", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("Abbrechen") { dialog, _ ->
            dialog.cancel()
        }
        builder.show()
    }
}
