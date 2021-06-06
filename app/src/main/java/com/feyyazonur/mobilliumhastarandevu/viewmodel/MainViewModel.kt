package com.feyyazonur.mobilliumhastarandevu.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.feyyazonur.mobilliumhastarandevu.model.Doctor
import com.feyyazonur.mobilliumhastarandevu.model.DoctorsResponse
import com.feyyazonur.mobilliumhastarandevu.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {
    val doctorsList = ArrayList<Doctor>()
    val doctorsListLiveData = MutableLiveData<MutableList<Doctor>>()


    fun getAllDoctors() {
        val response = repository.getAllDoctors()
        response.enqueue(object : Callback<DoctorsResponse> {
            override fun onResponse(
                call: Call<DoctorsResponse>,
                response: Response<DoctorsResponse>
            ) {
                if (response.code() == 200) {
                    val doctorResponse = response.body()!!
                    for (doc in doctorResponse.doctors) {
                        doctorsList.add(doc)
                        doctorsListLiveData.value = doctorsList
                    }
                }
            }

            override fun onFailure(call: Call<DoctorsResponse>, t: Throwable) {
                Log.d("--- Failure ---", t.message ?: "başaramadık abi")
            }

        })
    }
}