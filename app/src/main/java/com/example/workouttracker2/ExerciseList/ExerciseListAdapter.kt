package com.example.workouttracker2.ExerciseList

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.workouttracker2.Exercise
import com.example.workouttracker2.R

const private val  LOG_TAG = "ExerciseListAdapter"

class ExerciseListAdapter(private var exerciseList: List<Exercise>): RecyclerView.Adapter<ExerciseViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_exercise, parent, false)
        Log.i(LOG_TAG, "onCreateViewHolder called")
        return ExerciseViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        Log.i(LOG_TAG, "onBind called")
        val exercise = exerciseList[position]
        holder.bind(exercise)
        holder.itemView.setOnClickListener {
            val navController = it.findNavController()
            navController.navigate(
                PushWorkoutListFragmentDirections.actionToExerciseDetailFragment(
                    exercise
                )
            )
        }
    }

    fun updateExercises(newExercises: List<Exercise>) {
        exerciseList = newExercises
        notifyDataSetChanged()
    }

}

class ExerciseViewHolder(viewFromXmlLayout: View): RecyclerView.ViewHolder(viewFromXmlLayout) {

    private val exerciseTextView: TextView = viewFromXmlLayout.findViewById(R.id.tvListContentExercise)

    fun bind(exercise: Exercise) {
        exerciseTextView.text=exercise.name
    }

}
