package com.suitmedia.test1.ui.firstscreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog
import com.suitmedia.test1.databinding.ActivityMainBinding
import com.suitmedia.test1.ui.secondscreen.SecondActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAction()
    }

    private fun setAction(){
        binding.apply {
            btnCheck.setOnClickListener {
                val palindrome: String = etPalindrome.text.toString()
                Log.d("test", palindrome)
                val pDialog = SweetAlertDialog(this@MainActivity, SweetAlertDialog.WARNING_TYPE)
                pDialog.setCancelable(false)
                pDialog.setConfirmClickListener { dialog ->
                    dialog.dismiss()
                }

                if (palindrome.isEmpty()){
                    pDialog.setContentText("Field palindrome cannot empty")
                    pDialog.show()
                }

                if (isPalindrome(palindrome)){
                    pDialog.setContentText("isPalindrome")
                    pDialog.show()
                } else {
                    pDialog.setContentText("not palindrome")
                    pDialog.show()
                }

            }

            btnNext.setOnClickListener {
                val name: String = etName.text.toString().trim()
                val pDialog = SweetAlertDialog(this@MainActivity, SweetAlertDialog.WARNING_TYPE)
                pDialog.setCancelable(false)
                pDialog.setConfirmClickListener { dialog ->
                    dialog.dismiss()
                }

                if (name.isEmpty()){
                    pDialog.setContentText("Field name cannot empty")
                    pDialog.show()
                } else {
                    val intent = Intent(this@MainActivity, SecondActivity::class.java)
                    intent.putExtra(SecondActivity.NAME, name)
                    startActivity(intent)
                }

            }
        }
    }

    fun isPalindrome(text: String): Boolean {
        val cleanedText = text.replace("\\s".toRegex(), "").lowercase()

        val reversedText = cleanedText.reversed()

        return cleanedText == reversedText
    }
}