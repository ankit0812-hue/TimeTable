package com.vitap.timetable.ui.days


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vitap.timetable.databinding.ItemDayBinding

class DaysAdapter(
    val onItemClick: (day: String) -> Unit
): ListAdapter<String, DaysAdapter.ViewHolder>(DiffCallback()) {
    class DiffCallback: DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(old: String, new: String) = old == new
        override fun areContentsTheSame(old: String, new: String) = old == new
    }

    inner class ViewHolder(private val binding: ItemDayBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(dayName: String) = binding.run {
            root.setOnClickListener { onItemClick(dayName) }
            day.text = dayName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        ItemDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))
}