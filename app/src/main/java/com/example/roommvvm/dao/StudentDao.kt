package com.example.roommvvm.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.roommvvm.entity.Student

@Dao
interface StudentDao {
    //query untuk memanggil semua data pada tabel
    @Query("Select * from student")
    fun getAll(): List<Student>

    //query untuk insert data pada tabel
    @Insert
    fun insertStudent(item: Student)
}