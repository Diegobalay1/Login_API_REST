package com.diego.kotlin.loginapirest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.cursosant.android.stores.LoginApplication
import com.diego.kotlin.loginapirest.databinding.ActivityMainBinding
import org.json.JSONObject

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
        val typeMethod = if (mBinding.swType.isChecked) Constants.LOGIN_PATH else Constants.REGISTER_PATH

        val url = Constants.BASE_URL + Constants.API_PATH + typeMethod

        val jsonParams = JSONObject()

        val jsonObjectRequest = object : JsonObjectRequest(Request.Method.POST, url, jsonParams, {
            updateUI(":)")
        }, {
            it.printStackTrace()
            if (it.networkResponse.statusCode == 400) {
                updateUI(getString(R.string.main_error_server))
            }
        }){
            override fun getHeaders(): MutableMap<String, String> {
                val params = HashMap<String, String>()

                params["Content-Type"] = "application/json"
                return params
            }
        }
        LoginApplication.reqResAPI.addToRequestQueue(jsonObjectRequest)
    }

    private fun updateUI(result: String) {
        mBinding.tvResult.visibility = View.VISIBLE
        mBinding.tvResult.text = result
    }

}