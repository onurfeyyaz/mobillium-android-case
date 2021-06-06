package com.feyyazonur.mobilliumhastarandevu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.feyyazonur.mobilliumhastarandevu.databinding.RowItemBinding
import com.feyyazonur.mobilliumhastarandevu.model.Doctor
import com.feyyazonur.mobilliumhastarandevu.view.fragments.ListelemeFragmentDirections

class DoctorAdapter : RecyclerView.Adapter<DoctorAdapterViewHolder>() {

    var doctors = mutableListOf<Doctor>()

    var filteredName: String = ""
    var fullNameOfDoctor: String = ""
    var userStatus: String = ""
    var gender: String = ""
    var imageUrl: String = ""

    fun setDoctorList(doctors: MutableList<Doctor>) {
        this.doctors = doctors.toMutableList()
        notifyDataSetChanged()
    }

    fun filterName(filteredName: String) {
        this.filteredName = filteredName
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowItemBinding.inflate(inflater, parent, false)
        return DoctorAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DoctorAdapterViewHolder, position: Int) {
        when (position) {
            0 -> holder.binding.cardView.setBackgroundResource(R.drawable.recyclerview_bg_top)
            (doctors.size - 1) -> {
                holder.binding.cardView.setBackgroundResource(R.drawable.recyclerview_bg_bottom)
                holder.binding.viewDivider.visibility = View.GONE
            }
        }
        val doctor = doctors[position]

        fullNameOfDoctor = doctor.fullName
        userStatus = doctor.userStatus
        gender = doctor.gender
        imageUrl = doctor.image.url
        val filteredNameToLower: String = filteredName.lowercase().replace(" ", "")

        //Log.d("--- DoctorAdapter Fullt", fullNameOfDoctor.lowercase().replace(" ", ""))

        /*if (fullNameOfDoctor.lowercase().replace(" ", "").contains(filteredNameToLower)) {
            holder.binding.doctorNameTextview.text = fullNameOfDoctor

            Glide.with(holder.itemView.context).load(imageUrl).circleCrop()
                .into(holder.binding.doctorImageview)
        }else {
            Log.d("--- DoctorAdapter ELSE", "olmadı beee")
        }*/
        holder.binding.doctorNameTextview.text = fullNameOfDoctor

        Glide.with(holder.itemView.context).load(imageUrl).circleCrop()
            .into(holder.binding.doctorImageview)
        holder.binding.cardView.setOnClickListener {
            val action = ListelemeFragmentDirections.actionListelemeFragmentToDetayFragment(doctor)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = doctors.size

}