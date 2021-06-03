package com.feyyazonur.mobilliumhastarandevu

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.feyyazonur.mobilliumhastarandevu.model.Doctor
import com.feyyazonur.mobilliumhastarandevu.model.Doctors
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {
    val doctorsList = MutableLiveData<List<Doctor>>()

    fun getAllDoctors() {
        val response = repository.getAllDoctors()
        response.enqueue(object : Callback<Doctors> {
            override fun onResponse(call: Call<Doctors>, response: Response<Doctors>) {
                doctorsList.postValue(response.body()?.doctors)
                Log.d("--- Doctor List ---", response.body().toString())
            }

            override fun onFailure(call: Call<Doctors>, t: Throwable) {
                Log.d("--- Failure ---", t.message ?: "HOHOHO")
            }

        })
    }
}