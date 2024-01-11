package com.example.workouttracker2.ExerciseList

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workouttracker2.R

class PushWorkoutListFragment : Fragment(R.layout.fragment_excercise_list) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var exerciseAdapter: ExerciseListAdapter
    private val pushWorkoutViewModel: PushWorkoutListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val workoutName = arguments?.let { PushWorkoutListFragmentArgs.fromBundle(it).workoutName }
        view.findViewById<TextView>(R.id.workoutName).text = workoutName
        setupList()
    }
    private fun setupList() {
        // Initialisierung des RecyclerView und des Adapters
        recyclerView = requireView().findViewById(R.id.rvExercises)
        recyclerView.layoutManager = LinearLayoutManager(context)
        exerciseAdapter = ExerciseListAdapter(listOf())
        recyclerView.adapter = exerciseAdapter

        // Beobachten der LiveData aus dem ViewModel
        pushWorkoutViewModel.readAllPush().observe(viewLifecycleOwner, { exercises ->
            exerciseAdapter.updateExercises(exercises)
        })
    }
}
