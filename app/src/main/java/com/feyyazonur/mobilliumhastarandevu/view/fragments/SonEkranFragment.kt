package com.feyyazonur.mobilliumhastarandevu.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.feyyazonur.mobilliumhastarandevu.R
import com.feyyazonur.mobilliumhastarandevu.databinding.FragmentSonEkranBinding

class SonEkranFragment : Fragment() {

    private val binding get() = _binding!!
    private var _binding: FragmentSonEkranBinding? = null

    private val args by navArgs<SonEkranFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSonEkranBinding.inflate(inflater, container, false)

        if (args.isPremium == "premium") {
            binding.lastScreenTextview.text = getString(R.string.randevu_ekrani)
        }else{
            binding.lastScreenTextview.text = getString(R.string.odeme_ekrani)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}