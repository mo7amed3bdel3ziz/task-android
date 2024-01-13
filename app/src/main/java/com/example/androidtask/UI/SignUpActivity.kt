package com.example.androidtask.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.androidtask.R
import com.example.androidtask.UI.viewmodels.LoginViewModel
import com.example.androidtask.UI.viewmodels.SignUPViewModel
import com.example.androidtask.business.models.RegisterRequestModel
import com.example.androidtask.business.models.RequestModel
import com.example.androidtask.databinding.ActivitySignUpBinding
import com.example.androidtask.network.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val viewModel: SignUPViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up)


        val androidId: String = Settings.Secure.getString(
            contentResolver, Settings.Secure.ANDROID_ID
        )

        binding.goLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.signUpBTN.setOnClickListener {
            if (CheckAllFields()){

                lifecycleScope.launch() {
                    viewModel.registerVM(
                        RegisterRequestModel(
                            binding.nameID.text.toString(),
                            binding.emailID.text.toString(),
                            binding.passwordID.text.toString().toInt(),
                            binding.phoneID.text.toString().toInt(),
                            androidId
                            // "anas.akid7@gmail.com",
                            //  12345678,
                            //"12233454566787877"

                        )
                    ).collect {

                        when (it) {
                            is State.Loading -> {

                            }

                            is State.Success -> {
                                if (it.data.response_code ==200){
                                    Toast.makeText(applicationContext, it.data.message, Toast.LENGTH_SHORT).show()

                                }
                                else
                                    Toast.makeText(this@SignUpActivity, it.data.message, Toast.LENGTH_SHORT).show()
                                //   Log.d("VisitBranchWithoutPay", "success"+it.data.message)
                            }

                            is State.Error -> {
                                Toast.makeText(this@SignUpActivity, it.messag, Toast.LENGTH_SHORT).show()
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
        else if (binding.confirmedpasswordID.text.isNullOrEmpty()) {
            binding.confirmedpasswordID.setError("This field is required")
            return false
        }
         else if (binding.nameID.text.isNullOrEmpty()) {
            binding.nameID.setError("This field is required")
            return false
        }

        // after all validation return true.
        return true
    }
}