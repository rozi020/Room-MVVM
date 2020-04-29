package com.example.roommvvm.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student (
    //membuat isi dari tabel 'student-database' yang berisi kolom primary key dengan nama dan tipe data 'id' int auto increment
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    //membuat kolom name dengan tipe data string
    @ColumnInfo var name: String = ""
)