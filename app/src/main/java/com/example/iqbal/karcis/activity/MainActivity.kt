package com.example.iqbal.karcis.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.example.iqbal.karcis.R
import android.R.menu
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.view.MenuInflater
import android.view.MenuItem


class MainActivity : AppCompatActivity() {

    private lateinit var mSharedPref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.activity_main_actions, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.action_logout){
            mSharedPref = this.getSharedPreferences(getString(R.string.login_state), Context.MODE_PRIVATE)
            with (mSharedPref.edit()) {
                putBoolean(getString(R.string.login_state), false)
                apply()
            }

            Log.i("Coba", "Main " + mSharedPref.getBoolean(getString(R.string.login_state), false))

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
