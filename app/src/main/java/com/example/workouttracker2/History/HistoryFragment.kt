package com.example.workouttracker2.History

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workouttracker2.R
import com.example.workouttracker2.Set

class HistoryFragment : Fragment(R.layout.history_fragment) {
    private lateinit var recyclerView: RecyclerView
    private lateinit var historyAdapter: HistoryListAdapter
    private val historyViewModel: HistoryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val exercise = historyViewModel.read()
        val exerciseName = view.findViewById<TextView>(R.id.exerciseDetailName)
        exerciseName.text = exercise.name
        val exerciseId = exercise.id

        // Beobachte die LiveData für Sets
        historyViewModel.getExercise(exerciseId).observe(viewLifecycleOwner, Observer { sets ->
            setupList(sets)
        })
    }

    private fun setupList(sets: List<Set>) {
        recyclerView = requireView().findViewById(R.id.rvExercisesDetail)
        recyclerView.layoutManager = LinearLayoutManager(context)
        historyAdapter = HistoryListAdapter(sets)
        recyclerView.adapter = historyAdapter
    }
}
