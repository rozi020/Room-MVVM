package com.example.roommvvm.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders

import com.example.roommvvm.R
import com.example.roommvvm.viewmodel.NewStudentViewModel
import kotlinx.android.synthetic.main.fragment_new_name.*

/**
 * A simple [Fragment] subclass.
 */
class NewNameFragment : Fragment() {
    //mendeklarasikan listener
    private var listener: OnFragmentInteractionListener? = null
    //
    private lateinit var mViewModel: NewStudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = ViewModelProviders.of(this).get(NewStudentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // menentukan activity nama xml
        return inflater.inflate(R.layout.fragment_new_name, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //membuat button jika di klik memiliki aksi
        button.setOnClickListener {
            //membuat input untuk diisi
            val input = editText.text.toString().trim()
            //jika nama tidak diisi pada inputan
            if (input.isEmpty()) {
                Toast.makeText(activity, "Nama dibutuhkan", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            //jika nama terlalu panjang pada inputan
            if (input.length > 30) {
                Toast.makeText(activity, "Nama terlalu panjang", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            //memasukkan kedalam database
            mViewModel.storeMovie(input)
            //notifikasi jika data telah berhasil masuk
            Toast.makeText(activity, "$input entered", Toast.LENGTH_SHORT).show()
            listener?.goToStudentListFragment()
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
    //
    interface OnFragmentInteractionListener {
        fun goToStudentListFragment()
    }
    //
    companion object {
        @JvmStatic
        fun newInstance() = NewNameFragment()
    }
}
