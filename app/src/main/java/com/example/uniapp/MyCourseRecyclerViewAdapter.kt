package com.example.uniapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uniapp.databinding.FragmentItemBinding

/**
 * [RecyclerView.Adapter] that can display a [Programme].
 */
class MyCourseRecyclerViewAdapter(
    private val values: ArrayList<Programme>
) : RecyclerView.Adapter<MyCourseRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.textViewProgrammeName.text = item.programmeName
        holder.textViewFaculty.text = item.faculty
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val textViewProgrammeName: TextView = binding.textviewProgrammename
        val textViewFaculty: TextView = binding.textviewFaculty
    }
}