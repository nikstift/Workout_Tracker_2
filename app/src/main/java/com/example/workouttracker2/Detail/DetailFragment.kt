package com.example.workouttracker2.Detail

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.workouttracker2.Exercise
import com.example.workouttracker2.R
import com.google.android.material.textfield.TextInputEditText


class DetailFragment:Fragment(R.layout.fragment_detail) {
    private val detailViewModel : DetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("DetailFragment", "onViewCreated(): Start")

        val excercise = detailViewModel.read()

        val exerciseNameTextView = view.findViewById<TextView>(R.id.exerciseName)
        val repetitionsEditText = view.findViewById<TextInputEditText>(R.id.etRepetitions)
        val weightEditText = view.findViewById<TextInputEditText>(R.id.etWeight)
        val notesEditText = view.findViewById<TextInputEditText>(R.id.etNotes)
        val addButton = view.findViewById<Button>(R.id.button)
        val bottomNav = view.findViewById<Button>(R.id.buttonHistory)

        exerciseNameTextView.setText(excercise.name)

        val lastSet = detailViewModel.getLastSet()
        lastSet?.let {
            repetitionsEditText.setText(it.repetitions.toString())
            weightEditText.setText(it.weight.toString())
            notesEditText.setText(it.notes)
        }
        addButton.setOnClickListener {
            val repetitions = repetitionsEditText.text.toString().toIntOrNull() ?: 0
            val weight = weightEditText.text.toString().toDoubleOrNull() ?: 0.0
            val notes = notesEditText.text.toString()
            val exerciseName = exerciseNameTextView.text.toString()

            detailViewModel.addSetToExercise(exerciseName, repetitions, weight, notes)
        }

        bottomNav.setOnClickListener{
            view?.findNavController()?.navigate(DetailFragmentDirections.actionToHistoryFragment(detailViewModel.read()))
        }


    }


}