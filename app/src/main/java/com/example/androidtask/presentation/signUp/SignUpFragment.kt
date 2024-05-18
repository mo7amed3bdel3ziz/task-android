package com.example.androidtask.presentation.signUp

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.androidtask.R
import com.example.androidtask.databinding.FragmentSignUpBinding
import com.example.androidtask.data.remote.models.RegisterRequestModel
import com.example.androidtask.presentation.home.ui.MainActivity
import com.example.androidtask.utils.network.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SignUPViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val androidId: String = Settings.Secure.getString(
            requireContext().contentResolver, Settings.Secure.ANDROID_ID
        )

        binding.goLogin.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }

        binding.signUpBTN.setOnClickListener {
            if (checkAllFields()) {
                lifecycleScope.launch {
                    viewModel.registerVM(
                        RegisterRequestModel(
                            binding.nameID.text.toString(),
                            binding.emailID.text.toString(),
                            binding.passwordID.text.toString(),
                            binding.phoneID.text.toString(),
                            androidId
                        )
                    ).collect {
                        when (it) {
                            is State.Loading -> {
                                // Show loading state if needed
                            }

                            is State.Success -> {
                                if (it.data.response_code == 200) {
                                    Toast.makeText(requireContext(), it.data.message, Toast.LENGTH_SHORT).show()
                                    val intent = Intent(requireContext(), MainActivity::class.java).apply {
                                        putExtra("token", it.data.data.token)
                                        putExtra("username", it.data.data.name)
                                    }
                                    startActivity(intent)
                                    requireActivity().finish()
                                } else {
                                    Toast.makeText(requireContext(), it.data.message, Toast.LENGTH_SHORT).show()
                                }
                            }

                            is State.Error -> {
                                Toast.makeText(requireContext(), it.messag, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun checkAllFields(): Boolean {
        return when {
            binding.emailID.text.isNullOrEmpty() -> {
                binding.emailID.error = "This field is required"
                false
            }
            binding.passwordID.text.isNullOrEmpty() -> {
                binding.passwordID.error = "This field is required"
                false
            }
            binding.confirmedPasswordID.text.isNullOrEmpty() -> {
                binding.confirmedPasswordID.error = "This field is required"
                false
            }
            binding.nameID.text.isNullOrEmpty() -> {
                binding.nameID.error = "This field is required"
                false
            }
            else -> true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
