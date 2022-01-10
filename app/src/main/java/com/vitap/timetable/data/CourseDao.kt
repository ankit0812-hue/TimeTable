package com.vitap.timetable.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CourseDao {
    @Insert
    suspend fun insertCourse(course: Course)

    @Update
    suspend fun updateCourse(course: Course)

    @Delete
    suspend fun deleteCourse(course: Course)

    @Query("SELECT * FROM courses WHERE day = :day")
    suspend fun getCoursesOfDay(day: String): List<Course>
}