package com.example.loginfarebase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.loginfarebase.databinding.FragmentRegistrationBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class RegistrationFragment : Fragment() {

    private var _binding:FragmentRegistrationBinding? = null
    private val binding get() = _binding!!
    private var firebaseAuth: FirebaseAuth? = null
    var email : String = ""
    var pass : String = ""
    var nickname : String = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)


        firebaseAuth = FirebaseAuth.getInstance()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigation()
        signUp()
    }

    private fun navigation(){
        binding.backArr.setOnClickListener {
            findNavController().navigate(R.id.loggedOutFragment)
        }
    }

    private fun signUp(){

        binding.nextBtn.setOnClickListener {
            email = binding.edEmail.text.toString()
            pass = binding.edRegistrationPass.text.toString()


            if (binding.edEmail.text.toString().isNotEmpty() && binding.edRegistrationPass.text.toString().isNotEmpty()){

                binding.edRegistrationPass.visibility = View.GONE
                binding.nextBtn.visibility = View.GONE
                binding.btnReg.visibility = View.VISIBLE
                binding.edEmail.visibility = View.GONE
                binding.edNickname.visibility = View.VISIBLE
            }else{
                Toast.makeText(requireContext(),"error",Toast.LENGTH_LONG).show()
            }


        }
        binding.btnReg.setOnClickListener {
            nickname = binding.edNickname.text.toString()
            if (binding.edNickname.text.toString().isNotEmpty()) {
                firebaseAuth?.createUserWithEmailAndPassword(email, pass)
                    ?.addOnCompleteListener {
                        if (it.isSuccessful) {
                            val user = FirebaseAuth.getInstance().currentUser
                            if (user != null) {
                                val userData = hashMapOf(
                                    "Email" to email,
                                    "Password" to pass,
                                    "Nickname" to nickname
                                )
                                FirebaseFirestore.getInstance().collection("user")
                                    .document(user.uid).set(userData).addOnSuccessListener {
                                        Toast.makeText(
                                            requireContext(),
                                            "success",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }.addOnFailureListener {
                                        Toast.makeText(
                                            requireContext(),
                                            "Fail $it",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                            }
                        } else {
                            Toast.makeText(
                                requireContext(),
                                it.exception.toString(),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}