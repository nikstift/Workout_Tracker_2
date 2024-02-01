package com.example.workouttracker2.Grid

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.PackageManagerCompat.LOG_TAG
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.workouttracker2.Exercise
import com.example.workouttracker2.ExerciseList.ExerciseViewHolder
import com.example.workouttracker2.ExerciseList.PushWorkoutListFragmentDirections
import com.example.workouttracker2.R
import com.example.workouttracker2.Workout

class WorkoutListAdapter (private var workoutList: List<Workout>): RecyclerView.Adapter<WorkoutViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_exercise, parent, false)
        return WorkoutViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return workoutList.size
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        val workout = workoutList[position]
        holder.bind(workout)
        holder.itemView.setOnClickListener {
            val navController = it.findNavController()
            navController.navigate(
                PushWorkoutListFragmentDirections.actionToExerciseDetailFragment(
                    workout
                )
            )
        }
    }

    fun updateExercises(newWorkut: List<Workout>) {
        workoutList = newWorkut
        notifyDataSetChanged()
    }

}

class WorkoutViewHolder(viewFromXmlLayout: View): RecyclerView.ViewHolder(viewFromXmlLayout) {

    private val workoutTextView: TextView =
        viewFromXmlLayout.findViewById(R.id.tvListContentWorkout)

    fun bind(workout: Workout) {
        workoutTextView.text = workout.name
    }
}
