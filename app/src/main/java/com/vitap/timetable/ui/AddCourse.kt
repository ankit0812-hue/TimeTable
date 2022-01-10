package com.vitap.timetable.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.vitap.timetable.R
import com.vitap.timetable.data.Course
import com.vitap.timetable.databinding.AddCourseBinding
import java.util.*

class AddCourse: Fragment(R.layout.add_course) {
    private val args: AddCourseArgs by navArgs()
    private val viewModel: MainVM by activityViewModels()

    var timeVal: Long = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AddCourseBinding.bind(view).run {
            btnSubmit.setOnClickListener {
                viewModel.insertCourse(Course(
                    name = name.text.toString(),
                    course_code = courseCode.text.toString(),
                    teacher = teacher.text.toString(),
                    location = location.text.toString(),
                    day = args.day,
                    time = timeVal,
                    num_credits = numCredits.text.toString().toInt()
                ))
            }

            time.setOnFocusChangeListener { _, focus ->
                if (!focus) return@setOnFocusChangeListener

                val timePicker = MaterialTimePicker.Builder().run {
                    setTimeFormat(TimeFormat.CLOCK_12H)
                    setTitleText("Course Time")
                    build()
                }

                timePicker.show(childFragmentManager, "Time picker")
                timePicker.addOnPositiveButtonClickListener {
                    val cal = Calendar.getInstance()
                    cal.set(Calendar.HOUR, timePicker.hour)
                    cal.set(Calendar.MINUTE, timePicker.minute)
                    cal.set(Calendar.SECOND, 0)

                    timeVal = cal.timeInMillis
                }
            }
        }

        viewModel.resInsertCourse.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Course Inserted successfully!", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }
    }
}