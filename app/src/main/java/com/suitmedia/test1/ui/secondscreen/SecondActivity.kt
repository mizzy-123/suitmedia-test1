package com.suitmedia.test1.ui.secondscreen

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.suitmedia.test1.R
import com.suitmedia.test1.databinding.ActivitySecondBinding
import com.suitmedia.test1.ui.factory.ViewModelFactory
import com.suitmedia.test1.ui.thirdscreen.ThirdActivity

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var secondViewModel: SecondViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle: Bundle? = intent.extras

        if (bundle != null){
            val name = bundle.getString(NAME)
            binding.tvUsername.text = name
        }

        initViewModel()
        setAction()
    }

    private fun setAction(){
        binding.toolBar.setNavigationOnClickListener {
            finish()
        }

        binding.btnChoose.setOnClickListener {
            val intent = Intent(this@SecondActivity, ThirdActivity::class.java)
            startActivity(intent)
        }

        secondViewModel.getUsername().observe(this@SecondActivity){
            if (it != ""){
                binding.tvSelectedName.text = it
            } else {
                binding.tvSelectedName.text = getString(R.string.selected_user_name)
            }
        }
    }

    private fun initViewModel(){
        val factory = ViewModelFactory.getInstance(this@SecondActivity.application)
        secondViewModel = ViewModelProvider(this@SecondActivity, factory).get(SecondViewModel::class.java)
    }

    companion object {
        const val NAME = "name"
    }
}