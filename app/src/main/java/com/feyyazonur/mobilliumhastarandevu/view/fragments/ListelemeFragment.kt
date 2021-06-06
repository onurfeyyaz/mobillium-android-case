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
import com.feyyazonur.mobilliumhastarandevu.*
import com.feyyazonur.mobilliumhastarandevu.databinding.FragmentListelemeBinding
import com.feyyazonur.mobilliumhastarandevu.model.Doctor

class ListelemeFragment : Fragment() {

    private var _binding: FragmentListelemeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel

    var filteredNameList = mutableListOf<Doctor>()

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
            if (isChecked) {
                selectedGender = "female"
                Log.d("--- GENDER Selected in", selectedGender)
            } else {
                selectedGender = ""
            }
            binding.erkekCheckbox.isClickable = !isChecked

        }
        binding.erkekCheckbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectedGender = "male"
                Log.d("--- GENDER Selected in", selectedGender)
            } else {
                selectedGender = ""
            }
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

                    if ((binding.searchClinicEdittext.text.isEmpty() || binding.searchClinicEdittext.text.isNullOrBlank()) && selectedGender == "") {
                        adapter.setDoctorList(doctor)
                        setVisibility(true)
                        /*binding.doctorsRecyclerview.visibility = View.VISIBLE
                        binding.notFoundTextview.visibility = View.GONE
                        binding.notFoundImage.visibility = View.GONE*/
                    } else {
                        filteredNameList.clear()
                        for (i in doctor) {
                            val iFilteredName = i.fullName.lowercase().replace(" ", "")
                            val iFilteredGender = i.gender // male or female

                            if (iFilteredName.contains(filteredName) && iFilteredGender == selectedGender) { //== filteredName
                                filteredNameList.add(i)

                            }
                        }

                        if (filteredNameList.isEmpty()) {
                            setVisibility(false)
                            /*adapter.setDoctorList(doctor)
                            binding.doctorsRecyclerview.visibility = View.GONE
                            binding.notFoundTextview.visibility = View.VISIBLE
                            binding.notFoundImage.visibility = View.VISIBLE*/
                        } else {
                            adapter.setDoctorList(filteredNameList)
                            setVisibility(true)
                            /*binding.doctorsRecyclerview.visibility = View.VISIBLE
                            binding.notFoundTextview.visibility = View.GONE
                            binding.notFoundImage.visibility = View.GONE*/
                        }
                    }
                })
            }
        })


        return binding.root
    }

    //gone/invisible -> 0 (false), visible -> 1 (true)
    fun setVisibility(recyclerViewVisibility: Boolean) {
        when (recyclerViewVisibility) {
            true -> {
                binding.doctorsRecyclerview.visibility = View.VISIBLE
                binding.notFoundTextview.visibility = View.GONE
                binding.notFoundImage.visibility = View.GONE
            }
            false -> {
                binding.doctorsRecyclerview.visibility = View.GONE
                binding.notFoundTextview.visibility = View.VISIBLE
                binding.notFoundImage.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}