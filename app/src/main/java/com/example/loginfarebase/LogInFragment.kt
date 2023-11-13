package com.example.loginfarebase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.loginfarebase.databinding.FragmentLogInBinding
import com.example.loginfarebase.databinding.FragmentLoggedOutBinding


class LogInFragment : Fragment() {
    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigation()
    }

    private fun navigation(){
        binding.backArr.setOnClickListener {
            findNavController().navigate(R.id.loggedOutFragment)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}