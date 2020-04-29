package com.example.roommvvm.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.roommvvm.R
import com.example.roommvvm.data.AppDatabase
import com.example.roommvvm.helper.StudentRecyclerAdapter
import com.example.roommvvm.viewmodel.NewStudentViewModel
import kotlinx.android.synthetic.main.fragment_name_list.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class NameListFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null
    private lateinit var mViewModel: NewStudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        mViewModel = ViewModelProviders.of(this).get(NewStudentViewModel::class.java)
        mViewModel.retrieveStudent().observe(this, Observer {
            Timber.i("menerima perubahan data ${it.size}")
            //mengambil nama recycler view yang ada pada xml
            rvList.adapter = StudentRecyclerAdapter(it)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // menentukan activity nama xml
        return inflater.inflate(R.layout.fragment_name_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //memberikan jenis layout yang diinginkan
        rvList.layoutManager = LinearLayoutManager(activity)
        //menambahkan fungsi add yang ketika di klik melakukan aksi
        btnAdd.setOnClickListener {

            val dao =  AppDatabase.getInstance(this.context!!)?.studentDao()
            GlobalScope.launch {
                //mengambil semua data
                dao?.getAll()
            }
        //menuju ke fragmen untuk menambahkan data
            listener?.goToNewNameFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    //menghilangkan fragment/listener
    override fun onDetach() {
        super.onDetach()
        listener = null
    }
    //fungsi untuk intent
    interface OnFragmentInteractionListener {
        fun goToNewNameFragment()
    }
    //
    companion object {
        @JvmStatic
        fun newInstance() = NameListFragment()
    }

}
