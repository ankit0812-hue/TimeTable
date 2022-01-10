package com.vitap.timetable.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Course::class], version = 1, exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun courseDao(): CourseDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun get(context: Context) = INSTANCE ?: synchronized(this) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext, AppDatabase::class.java, "timetable_db"
            ).build()
            INSTANCE!!
        }

        fun erase(context: Context) {
            context.deleteDatabase("timetable_db")
            INSTANCE = null
        }
    }
}