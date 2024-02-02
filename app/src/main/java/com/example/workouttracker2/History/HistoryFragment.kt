package com.example.workouttracker2.History

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workouttracker2.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class HistoryFragment : Fragment(R.layout.history_fragment) {
    private lateinit var recyclerView: RecyclerView
    private lateinit var historyAdapter: HistoryListAdapter
    private val historyViewModel: HistoryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val exercise = historyViewModel.read()
        val exerciseName = view.findViewById<TextView>(R.id.exerciseDetailName)
        exerciseName.text = exercise.name
        setupList()
        setupSwipeToDelete()

        // Beobachte die LiveData für Sets
        historyViewModel.getSetsForExercise(exercise.id).observe(viewLifecycleOwner, Observer { sets ->
            historyAdapter.updateSet(sets)
        })
    }

    private fun setupList() {
        recyclerView = requireView().findViewById(R.id.rvExercisesDetail)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val dividerItemDecoration = DividerItemDecoration(recyclerView.context,
            (recyclerView.layoutManager as LinearLayoutManager).orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)

        historyAdapter = HistoryListAdapter(emptyList())
        recyclerView.adapter = historyAdapter
    }

    private fun setupSwipeToDelete() {
        val swipeToDeleteCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false // Bewegungen werden nicht unterstützt
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val setToDelete = historyAdapter.getSetAtPosition(position)

                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Löschen bestätigen")
                    .setMessage("Möchten Sie dieses Set wirklich löschen?")
                    .setNegativeButton("Abbrechen") { dialog, _ ->
                        historyAdapter.notifyItemChanged(position)
                        dialog.dismiss()
                    }
                    .setPositiveButton("Löschen") { dialog, _ ->
                        historyViewModel.deleteSet(setToDelete)
                        dialog.dismiss()
                    }
                    .show()
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}
