package com.example.androidtask.UI

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.androidtask.R
import com.example.androidtask.UI.viewmodels.LoginViewModel
import com.example.androidtask.business.models.RequestModel
import com.example.androidtask.databinding.ActivityLoginBinding
import com.example.androidtask.network.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.goSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }


        binding.loginBTn.setOnClickListener {
            val androidId: String = Settings.Secure.getString(
                contentResolver, Settings.Secure.ANDROID_ID
            )
            if (CheckAllFields()) {
                lifecycleScope.launch() {
                    viewModel.loginVM(
                        RequestModel(
                            binding.emailID.text.toString(),
                            binding.passwordID.text.toString(),
                            androidId


                        )
                    ).collect {

                        when (it) {
                            is State.Loading -> {

                            }

                            is State.Success -> {
                                if (it.data.response_code == 200) {
                                    Toast.makeText(
                                        applicationContext,
                                        it.data.message,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    val intent =
                                        Intent(this@LoginActivity, MainActivity::class.java)
                                            .apply {
                                                putExtra("token", it.data.data.token)
                                                putExtra("username", it.data.data.name)
                                            }
                                    startActivity(intent)
                                    finish()
                                } else
                                    Toast.makeText(
                                        this@LoginActivity,
                                        it.data.message,
                                        Toast.LENGTH_SHORT
                                    ).show()
                          //      Log.d("VisitBranchWithoutPay", "success" + it.data.message)
                            }

                            is State.Error -> {
                                Toast.makeText(this@LoginActivity, it.messag, Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }
                }
            }
        }

    }

    private fun CheckAllFields(): Boolean {
        if (binding.emailID.text.isNullOrEmpty()) {
            binding.emailID.setError("This field is required")
            return false
        } else if (binding.passwordID.text.isNullOrEmpty()) {
            binding.passwordID.setError("This field is required")
            return false
        }
        // after all validation return true.
        return true
    }
}