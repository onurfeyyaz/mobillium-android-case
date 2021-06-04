package com.feyyazonur.mobilliumhastarandevu

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.feyyazonur.mobilliumhastarandevu.databinding.RowItemBinding
import com.feyyazonur.mobilliumhastarandevu.model.Doctor

class DoctorAdapter : RecyclerView.Adapter<DoctorAdapterViewHolder>() {

    var doctors = mutableListOf<Doctor>()

    fun setDoctorList(doctors: MutableList<Doctor>) {
        this.doctors = doctors.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowItemBinding.inflate(inflater, parent, false)
        return DoctorAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DoctorAdapterViewHolder, position: Int) {
        val doctor = doctors[position]
        Log.d("---Doctor Bind View---", doctors[position].toString())
        holder.binding.doctorNameTextview.text = doctor.fullName
        Log.d("---Doctor FullName---", doctor.fullName)
        Glide.with(holder.itemView.context).load(doctor.image.url).circleCrop().into(holder.binding.doctorImageview)
    }

    override fun getItemCount() = doctors.size

}