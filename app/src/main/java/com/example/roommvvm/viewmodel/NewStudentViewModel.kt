package com.example.roommvvm.viewmodel

import android.app.Application
import android.graphics.Movie
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.roommvvm.data.AppDatabase
import com.example.roommvvm.entity.Student
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

class NewStudentViewModel(application: Application) : AndroidViewModel(application) {
    //mendeklarasikan database
    private val mDb: AppDatabase? = AppDatabase.getInstance(application)
    private val allStudent = MutableLiveData<List<Student>>()

    //membuat fungsi untuk menganmbil data pada dayabase
    fun storeMovie(title: String) {
    //mengambil data pada kolom name
        val student = Student()
        student.name = title

        GlobalScope.launch {
            mDb?.studentDao()?.insertStudent(student)
        }
    }

    fun retrieveStudent(): LiveData<List<Student>> {

        GlobalScope.launch {
            val list = mDb?.studentDao()?.getAll()
        //menjumlah berapa banyak data yang ada pada table
            Timber.i("Data yang ada sejumlah {${list?.size}}")
            allStudent.postValue(list)
        }

        return allStudent
    }
}