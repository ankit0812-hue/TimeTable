package com.vitap.timetable.ui.days

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vitap.timetable.R
import com.vitap.timetable.databinding.DaysBinding
import com.vitap.timetable.ui.MainVM

class Days : Fragment(R.layout.days) {
    private val viewModel: MainVM by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val daysAdapter = DaysAdapter { day ->
            findNavController().navigate(DaysDirections.toCourses(day))
        }.apply {
            submitList(
                listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
            )
        }

        val binding = DaysBinding.bind(view)
        binding.root.run {
            adapter = daysAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}