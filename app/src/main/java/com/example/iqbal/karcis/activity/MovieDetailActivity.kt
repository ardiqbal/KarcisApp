package com.example.iqbal.karcis.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.iqbal.karcis.R
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

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
    }
}
