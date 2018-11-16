package com.example.iqbal.karcis.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.iqbal.karcis.R
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var mSharedPref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val intent = intent

        val mTitle = intent.getStringExtra("item_name")
        title = mTitle

        val mImage = intent.getIntExtra("item_image", 0)
        image.setImageResource(mImage)

        val mSynopsis = intent.getStringExtra("item_overview")
        content.text = mSynopsis

        val mType = intent.getBooleanExtra("item_type", false)

        if(mType){
            btn_order.visibility = View.VISIBLE
        }else
            btn_order.visibility = View.GONE

        btn_order.setOnClickListener {
            mSharedPref = this@MovieDetailActivity.getSharedPreferences(getString(R.string.order), Context.MODE_PRIVATE)
            with (mSharedPref.edit()) {
                putString(getString(R.string.order_title), mTitle)
                apply()
            }

            val intent = Intent(this, OrderActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
