package com.example.androidtask.presentation.login
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
import com.example.androidtask.data.remote.models.RequestModel
import com.example.androidtask.databinding.FragmentLogginBinding
import com.example.androidtask.presentation.login.LogginFragmentDirections
import com.example.androidtask.presentation.login.LoginViewModel
import com.example.androidtask.utils.network.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LogginFragment : Fragment() {
    private lateinit var binding: FragmentLogginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.goSignup.setOnClickListener {
            findNavController().navigate(R.id.action_logginFragment_to_signUpFragment)
        }

        binding.loginBTn.setOnClickListener {
            val androidId: String = Settings.Secure.getString(
                requireContext().contentResolver, Settings.Secure.ANDROID_ID
            )
            if (checkAllFields()) {
                lifecycleScope.launch {
                    viewModel.loginVM(
                        RequestModel(
                            binding.emailID.text.toString(),
                            binding.passwordID.text.toString(),
                            androidId
                        )
                    ).collect { state ->
                        when (state) {
                            is State.Loading -> { /* Handle loading state */ }
                            is State.Success -> {
                                val responseData = state.data
                                if (responseData.response_code == 200) {
                                    Toast.makeText(
                                        requireContext(),
                                        responseData.message,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    val action = LogginFragmentDirections
                                        .actionLogginFragmentToMainFragment(
                                            token = responseData.data.token,
                                            username = responseData.data.name
                                        )
                                    findNavController().navigate(action)
                                } else {
                                    Toast.makeText(
                                        requireContext(),
                                        responseData.message,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                            is State.Error -> {
                                Toast.makeText(
                                    requireContext(),
                                    state.messag,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun checkAllFields(): Boolean {
        if (binding.emailID.text.isNullOrEmpty()) {
            binding.emailID.error = "This field is required"
            return false
        } else if (binding.passwordID.text.isNullOrEmpty()) {
            binding.passwordID.error = "This field is required"
            return false
        }
        return true
    }
}
