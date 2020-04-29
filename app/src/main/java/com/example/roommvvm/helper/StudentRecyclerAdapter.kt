package com.example.roommvvm.helper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roommvvm.R
import com.example.roommvvm.entity.Student

//membuat fungsi untuk mewadahi data yang akan di tampilkan pada listview
class StudentRecyclerAdapter(private val myDataset: List<Student>):
        RecyclerView.Adapter<StudentRecyclerAdapter.StudentViewHolder>() {
    //mewadahi nama dengan menggunakan recyclerview
    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tvName)
    }
    //memanggil layout yang digunakan untuk recyclerview
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)

        return StudentViewHolder(v)
    }
    //fungsi untuk menjumlah banyak data pada table
    override fun getItemCount(): Int {
        return myDataset.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.tvName.text = myDataset[position].name
    }
}