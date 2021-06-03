package com.feyyazonur.mobilliumhastarandevu.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.feyyazonur.mobilliumhastarandevu.*
import com.feyyazonur.mobilliumhastarandevu.databinding.FragmentListelemeBinding

class ListelemeFragment : Fragment() {

    private var _binding: FragmentListelemeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel

    private val retrofitService = RetrofitService.getInstance()

    val adapter = DoctorAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListelemeBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this, MainViewModelFactory(Repository(retrofitService))).get(MainViewModel::class.java)

        binding.doctorsRecyclerview.adapter = adapter

        viewModel.doctorsList.observe(viewLifecycleOwner, Observer { doctor ->
            Log.d("Listele Fragment", "onCreateView: $doctor")
            adapter.setDoctorList(doctor)
        })

        viewModel.getAllDoctors()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}