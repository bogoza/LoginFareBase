package com.example.loginfarebase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.loginfarebase.databinding.FragmentLogInBinding
import com.example.loginfarebase.databinding.FragmentNicknameBinding


class NicknameFragment : Fragment() {
    private var _binding: FragmentNicknameBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNicknameBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}