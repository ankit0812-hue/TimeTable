package com.vitap.timetable.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "courses")
@Parcelize
data class Course(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    val course_code: String,
    val name: String,
    val time: Long,
    val teacher: String,
    val location: String,
    val day: String,
    val num_credits: Int
) : Parcelable


data class DayWithNumCourses(
    val day: String,
    val numCourses: Int
)