package com.feyyazonur.mobilliumhastarandevu.view.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.feyyazonur.mobilliumhastarandevu.*
import com.feyyazonur.mobilliumhastarandevu.databinding.FragmentListelemeBinding
import com.feyyazonur.mobilliumhastarandevu.model.Doctor

class ListelemeFragment : Fragment() {

    private var _binding: FragmentListelemeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel

    var filteredNameList = mutableListOf<Doctor>()
    var filteredGenderList = mutableListOf<Doctor>()

    private var selectedGender: String = ""

    private val retrofitService = RetrofitService.getInstance()

    val adapter = DoctorAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, MainViewModelFactory(Repository(retrofitService))).get(
            MainViewModel::class.java
        )
        viewModel.getAllDoctors()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListelemeBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this, MainViewModelFactory(Repository(retrofitService))).get(
            MainViewModel::class.java
        )

        binding.doctorsRecyclerview.adapter = adapter


        binding.kadinCheckbox.setOnCheckedChangeListener { _, isChecked ->
            selectedGender = "female"
            Log.d("--- GENDER Selected in", selectedGender)
            binding.erkekCheckbox.isClickable = !isChecked
        }
        binding.erkekCheckbox.setOnCheckedChangeListener { _, isChecked ->
            selectedGender = "male"
            Log.d("--- GENDER Selected in", selectedGender)

            binding.kadinCheckbox.isClickable = !isChecked
        }


        binding.searchClinicEdittext.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                val filteredName =
                    binding.searchClinicEdittext.text.toString().lowercase().replace(" ", "")

                viewModel.doctorsListLiveData.observe(viewLifecycleOwner, Observer { doctor ->
                    filteredNameList.clear()
                    for (i in doctor) {
                        val iFilteredName = i.fullName.lowercase().replace(" ", "")
                        val iFilteredGender = i.gender // male or female

                        if (iFilteredName.contains(filteredName) && iFilteredGender == selectedGender) { //== filteredName
                            Log.d("--- RecyclerView 123", i.fullName.lowercase().replace(" ", ""))
                            Log.d("--- GENDER İ", iFilteredGender)
                            Log.d("--- GENDER Selected", selectedGender)

                            filteredNameList.add(i)

                            adapter.setDoctorList(filteredNameList)
                            binding.doctorsRecyclerview.visibility = View.VISIBLE

                        } else {
                            /*adapter.setDoctorList(doctor)
                            Log.d("--- RecyclerView ", " GONEE")*/
                            //binding.doctorsRecyclerview.visibility = View.INVISIBLE
                        }
                    }
                    //adapter.setDoctorList(doctor)
                })
                //adapter.filterName(binding.searchClinicEdittext.text.toString())
            }
        })


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}