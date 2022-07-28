package com.sadataljony.app.android.tripledes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sadataljony.app.android.tripledes.databinding.ActivityMainBinding
import com.sadataljony.app.android.tripledes.utils.EncryptionDecryption

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        clickListener()
    }

    private fun clickListener() {

        binding.btnEncrypt.setOnClickListener {
            try {
                binding.etEncryptText.setText(
                    EncryptionDecryption().encryptUsing3DES(
                        binding.etPlainText.text.toString().trim(),
                        16,
                        binding.etKey.text.toString().trim()
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
            binding.etPlainText.setText("")
        }

        binding.btnDecrypt.setOnClickListener {
            try {
                binding.etPlainText.setText(
                    EncryptionDecryption().decryptUsing3DES(
                        binding.etEncryptText.text.toString().trim(),
                        binding.etKey.text.toString().trim()
                    )
                )
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
            binding.etEncryptText.setText("")
        }

    }
}