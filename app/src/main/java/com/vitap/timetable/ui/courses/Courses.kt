package com.vitap.timetable.ui.courses

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.vitap.timetable.R
import com.vitap.timetable.databinding.CoursesBinding
import com.vitap.timetable.ui.MainVM

class Courses: Fragment(R.layout.courses) {
    private val args: CoursesArgs by navArgs()
    private val viewModel: MainVM by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val courseAdapter = CourseAdapter { course ->

        }

        val binding = CoursesBinding.bind(view)
        binding.run {
            courses.run {
                adapter = courseAdapter
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
            }

            btnAdd.setOnClickListener {
                findNavController().navigate(CoursesDirections.toAddCourse(args.day))
            }

            viewModel.getCoursesOfDay(args.day)
            viewModel.coursesOfDay.observe(viewLifecycleOwner) {
                if (it.isEmpty()) {
                    nothing.visibility = View.VISIBLE
                    courses.visibility = View.GONE
                } else {
                    nothing.visibility = View.GONE
                    courses.visibility = View.VISIBLE
                    courseAdapter.submitList(it)
                }
            }
        }


    }
}