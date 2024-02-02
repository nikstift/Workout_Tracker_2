package com.example.workouttracker2.History

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.workouttracker2.R
import com.example.workouttracker2.Set

const private val  LOG_TAG = "HistoryListAdapter"

class HistoryListAdapter(private var setList: List<Set>): RecyclerView.Adapter<SetViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_exercise_detail, parent, false)
        Log.i(LOG_TAG, "onCreateViewHolder called")
        return SetViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return setList.size
    }

    override fun onBindViewHolder(holder: SetViewHolder, position: Int) {
        Log.i(LOG_TAG, "onBind called")
        val set = setList[position]
        Log.d(LOG_TAG, "$setList")
        holder.bind(set)
        holder.itemView.setOnClickListener {
        }
    }

    fun updateSet(newSets: List<Set>) {
        setList = newSets
        notifyDataSetChanged()
    }

    fun getSetAtPosition(position: Int): Set {
        return setList[position]
    }

}

class SetViewHolder(viewFromXmlLayout: View): RecyclerView.ViewHolder(viewFromXmlLayout) {

    private val setRepetitionsTextView: TextView =
        viewFromXmlLayout.findViewById(R.id.tvRepetitions)
    private val setWeightTextView: TextView = viewFromXmlLayout.findViewById(R.id.tvWeight)
    private val setHintTextView: TextView = viewFromXmlLayout.findViewById(R.id.tvNotes)

    fun bind(set: Set) {
        setRepetitionsTextView.text = set.repetitions.toString()
        setWeightTextView.text = set.weight.toString()
        setHintTextView.text = set.notes
    }
}
