package com.diego.kotlin.loginapirest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.diego.kotlin.loginapirest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.swType.setOnCheckedChangeListener{ btn, checked ->
            btn.text = if (checked) getString(R.string.main_type_login)
            else getString(R.string.main_type_register)

            mBinding.btnLogin.text = btn.text
        }

        mBinding.btnLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {
         updateUI(":)")
    }

    private fun updateUI(result: String) {
        mBinding.tvResult.visibility = View.VISIBLE
        mBinding.tvResult.text = result
    }

}