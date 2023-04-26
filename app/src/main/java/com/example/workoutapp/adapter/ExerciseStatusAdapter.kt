package com.example.workoutapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.R
import com.example.workoutapp.databinding.ItemExerciseBinding
import com.example.workoutapp.model.ExerciseModel

class ExerciseStatusAdapter(private val items:ArrayList<ExerciseModel>):RecyclerView.Adapter<ExerciseStatusAdapter.MyViewHolder>() {

    class MyViewHolder(binding:ItemExerciseBinding):RecyclerView.ViewHolder(binding.root){
        val tvItem = binding.tvItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemExerciseBinding.inflate(LayoutInflater.from(parent.context),
            parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val model: ExerciseModel = items[position]
        holder.tvItem.text = model.getId().toString()

        when {
            model.getIsSelected() -> {
                holder.tvItem.background = ContextCompat.getDrawable(
                    holder.itemView.context,
                    R.drawable.selected_baclground
                )
                holder.tvItem.setTextColor(Color.parseColor("#212121"))
            }
            model.getIsCompleted() -> {
                holder.tvItem.background = ContextCompat.getDrawable(
                    holder.itemView.context,
                    R.drawable.color_accent_background
                )
                holder.tvItem.setTextColor(Color.parseColor("#FFFFFF"))
            }
            else -> {
                holder.tvItem.background = ContextCompat.getDrawable(
                    holder.itemView.context,
                    R.drawable.gray_background
                )
                holder.tvItem.setTextColor(Color.parseColor("#212121"))
            }
        }
    }
    override fun getItemCount(): Int {
        return items.size
    }
}