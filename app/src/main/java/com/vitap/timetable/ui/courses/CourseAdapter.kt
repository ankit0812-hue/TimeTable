package com.vitap.timetable.ui.courses


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vitap.timetable.data.Course
import com.vitap.timetable.databinding.ItemCourseBinding
import com.vitap.timetable.databinding.ItemDayBinding

class CourseAdapter(
    val onItemClick: (course: Course) -> Unit
): ListAdapter<Course, CourseAdapter.ViewHolder>(DiffCallback()) {
    class DiffCallback: DiffUtil.ItemCallback<Course>() {
        override fun areItemsTheSame(old: Course, new: Course) = old.id == new.id
        override fun areContentsTheSame(old: Course, new: Course) = old == new
    }

    inner class ViewHolder(private val binding: ItemCourseBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(course: Course) = binding.run {
            root.setOnClickListener { onItemClick(course) }

            courseCode.text = course.course_code

            name.text = course.name
            location.text = course.location
            credits.text = "${course.num_credits} credits"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        ItemCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))
}