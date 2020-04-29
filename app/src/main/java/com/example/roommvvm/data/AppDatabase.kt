package com.example.roommvvm.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roommvvm.dao.StudentDao
import com.example.roommvvm.entity.Student

@Database(entities = arrayOf(Student::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    //membuat fungsi untuk memanggil ulang student dao
    abstract fun studentDao(): StudentDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    //fungsi untuk membuat nama database
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "student-database")
                        .build()
                }
            }
            //mengembalikan fungsi INTANCE
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}