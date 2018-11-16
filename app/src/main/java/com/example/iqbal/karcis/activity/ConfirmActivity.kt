package com.example.iqbal.karcis.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.iqbal.karcis.R
import kotlinx.android.synthetic.main.activity_confirm.*

class ConfirmActivity : AppCompatActivity() {

    private lateinit var mSharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm)

        mSharedPref = this.getSharedPreferences(getString(R.string.order), Context.MODE_PRIVATE)

        setTitle("Payment Method")

        btn_confirm.setOnClickListener {
            mSharedPref.edit().remove(getString(R.string.order_count)).apply()
            mSharedPref.edit().remove(getString(R.string.order_city)).apply()
            mSharedPref.edit().remove(getString(R.string.order_title)).apply()
            mSharedPref.edit().remove(getString(R.string.order_cinema)).apply()
            mSharedPref.edit().remove(getString(R.string.order_date)).apply()
            mSharedPref.edit().remove(getString(R.string.order_time)).apply()

            val intent = Intent(this, ETicketActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
