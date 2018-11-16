package com.example.iqbal.karcis.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.example.iqbal.karcis.R
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.example.iqbal.karcis.adapter.MovieAdapter
import com.example.iqbal.karcis.model.Movie
import kotlinx.android.synthetic.main.list_coming_soon.*
import kotlinx.android.synthetic.main.list_now_playing.*


class MainActivity : AppCompatActivity() {

    private lateinit var mSharedPref: SharedPreferences
    private val mDataNowPlaying: ArrayList<Movie> = ArrayList()
    private val mDataComingSoon: ArrayList<Movie> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()
        loadLayout()
    }

    private fun loadData() {
        mDataNowPlaying.add(Movie("Halloween", R.drawable.halloween, getString(R.string.overview_halloween), true))
        mDataNowPlaying.add(Movie("Venom", R.drawable.venom, getString(R.string.overview_venom), true))
        mDataNowPlaying.add(Movie("The Nun", R.drawable.nun, getString(R.string.overview_nun), true))
        mDataNowPlaying.add(Movie("Black Panther", R.drawable.bp, getString(R.string.overview_bp), true))
        mDataNowPlaying.add(Movie("Rampage", R.drawable.rampage, getString(R.string.overview_rampage), true))

        mDataComingSoon.add(Movie("Aquaman", R.drawable.aquaman, getString(R.string.overview_aquaman), false))
        mDataComingSoon.add(Movie("Bohemian Rhapsody", R.drawable.br, getString(R.string.overview_br), false))
        mDataComingSoon.add(Movie("Bumblebee", R.drawable.bumblebee, getString(R.string.overview_bumblebee), false))
        mDataComingSoon.add(Movie("The Grinch", R.drawable.grinch, getString(R.string.overview_grinch), false))
        mDataComingSoon.add(Movie("Wreck-It Ralph 2", R.drawable.wir, getString(R.string.overview_wir), false))
    }

    private fun loadLayout() {
        rv_now_playing.isNestedScrollingEnabled = false
        val nowPlayingLayout = LinearLayoutManager(applicationContext)
        nowPlayingLayout.orientation = LinearLayoutManager.HORIZONTAL
        rv_now_playing.layoutManager = nowPlayingLayout
        rv_now_playing.smoothScrollToPosition(1)

        val movieAdapter = MovieAdapter(mDataNowPlaying, this)
        rv_now_playing.adapter = movieAdapter

        rv_coming_soon.isNestedScrollingEnabled = false
        val comingSoonLayout = LinearLayoutManager(applicationContext)
        comingSoonLayout.orientation = LinearLayoutManager.HORIZONTAL
        rv_coming_soon.layoutManager = comingSoonLayout
        rv_coming_soon.smoothScrollToPosition(1)

        val movie2Adapter = MovieAdapter(mDataComingSoon, this)
        rv_coming_soon.adapter = movie2Adapter


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

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else if(item?.itemId == R.id.action_eticket){
            val intent = Intent(this, ETicketActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}
