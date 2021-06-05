package com.feyyazonur.mobilliumhastarandevu.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.feyyazonur.mobilliumhastarandevu.R
import com.feyyazonur.mobilliumhastarandevu.databinding.FragmentDetayBinding


class DetayFragment : Fragment() {

    private var _binding: FragmentDetayBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<DetayFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetayBinding.inflate(inflater, container, false)

        binding.doctorNameDetail.text = args.doctorArg.fullName

        val imageURL = args.doctorArg.image.toString()

        Glide.with(requireActivity()).load(extractUrl(imageURL)).circleCrop()
            .into(binding.imageViewDetail)

        if (args.doctorArg.userStatus == "premium") {
            binding.premiumDetail.visibility = View.VISIBLE
            binding.randevuTextviewDetail.text = getString(R.string.randevu_al)
        } else {
            binding.premiumDetail.visibility = View.INVISIBLE
            binding.randevuTextviewDetail.text = getString(R.string.premium_paket_al)
        }

        binding.cardViewButtonDetail.setOnClickListener {
            val action =
                DetayFragmentDirections.actionDetayFragmentToSonEkranFragment(args.doctorArg.userStatus)
            findNavController().navigate(action)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun extractUrl(input: String): String {
        return input
            .replace("Image(url=", "")
            .dropLast(1)
    }

}
