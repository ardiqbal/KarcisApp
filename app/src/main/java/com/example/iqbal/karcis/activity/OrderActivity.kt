package com.example.iqbal.karcis.activity

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import com.example.iqbal.karcis.R
import com.example.iqbal.karcis.fragment.PickCinemaFragment
import com.example.iqbal.karcis.fragment.PickSeatFragment
import com.example.iqbal.karcis.fragment.SummaryFragment
import kotlinx.android.synthetic.main.activity_order.*

class OrderActivity : AppCompatActivity(), SummaryFragment.OnFragmentInteractionListener, PickSeatFragment.OnFragmentInteractionListener, PickCinemaFragment.OnFragmentInteractionListener {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        setTitle("Order your ticket")

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter

    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return when(position){
                0 -> PickCinemaFragment.newInstance(0)
                1 -> PickSeatFragment.newInstance(1)
                2 -> SummaryFragment.newInstance(2)
                else -> PickCinemaFragment.newInstance(0)
            }
        }

        override fun getCount(): Int {
            // Show 3 total pages.
            return 3
        }


    }
}
