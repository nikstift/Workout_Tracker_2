package com.example.workouttracker2.Detail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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


        exerciseNameTextView.setText(excercise.name)

        val lastSet = detailViewModel.getLastSet()
        lastSet?.let {
            repetitionsEditText.setText(it.repetitions.toString())
            weightEditText.setText(it.weight.toString())
            notesEditText.setText(it.notes)
        }
    }
}