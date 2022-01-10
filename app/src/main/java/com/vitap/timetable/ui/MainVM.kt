package com.vitap.timetable.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vitap.timetable.data.AppDatabase
import com.vitap.timetable.data.Course
import kotlinx.coroutines.launch

class MainVM(private val app: Application): AndroidViewModel(app) {
    val dao  = AppDatabase.get(app.applicationContext).courseDao()

    val coursesOfDay = MutableLiveData<List<Course>>()
    fun getCoursesOfDay(day: String) = viewModelScope.launch {
        coursesOfDay.value = dao.getCoursesOfDay(day)
    }

    val resInsertCourse = MutableLiveData<Boolean>()
    fun insertCourse(course: Course) = viewModelScope.launch {
        dao.insertCourse(course)
        resInsertCourse.value = true
    }
}