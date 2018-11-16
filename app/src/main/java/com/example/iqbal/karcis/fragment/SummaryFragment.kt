package com.example.iqbal.karcis.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.AppCompatButton
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.iqbal.karcis.R
import com.example.iqbal.karcis.activity.ConfirmActivity
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.fragment_summary.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_SECTION_NUMBER = "param1"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SummaryFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SummaryFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class SummaryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var page: Int? = null
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var mSharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            page = it.getInt(ARG_SECTION_NUMBER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v: View = inflater.inflate(R.layout.fragment_summary, container, false)
        mSharedPref = context!!.getSharedPreferences(getString(R.string.order), Context.MODE_PRIVATE)
        val textTitle: TextView = v.findViewById(R.id.txt_title)
        val textCity: TextView = v.findViewById(R.id.txt_city)
        val textCinema: TextView = v.findViewById(R.id.txt_cinema)
        val textDate: TextView = v.findViewById(R.id.txt_date)
        val textTime: TextView = v.findViewById(R.id.txt_time)
        val textTotalSeat: TextView = v.findViewById(R.id.txt_seat)
        val textTotalPrice: TextView = v.findViewById(R.id.txt_price)
        val btnOrder: AppCompatButton = v.findViewById(R.id.btn_order)

        textTitle.text = mSharedPref.getString(getString(R.string.order_title), "Not selected")
        textCity.text = mSharedPref.getString(getString(R.string.order_city), "Not selected")
        textCinema.text = mSharedPref.getString(getString(R.string.order_cinema), "Not selected")
        textDate.text = mSharedPref.getString(getString(R.string.order_date), "Not selected")
        textTime.text = mSharedPref.getString(getString(R.string.order_time), "Not selected")
        textTotalSeat.text = mSharedPref.getInt(getString(R.string.order_count), 0).toString()
        textTotalPrice.text = (mSharedPref.getInt(getString(R.string.order_count), 0) * 35000).toString()

        Log.i("Jumlah", "" + mSharedPref.getInt(getString(R.string.order_count), 0))

        btnOrder.setOnClickListener {
            val intent = Intent(context, ConfirmActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        return v
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SummaryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(sectionNumber: Int) =
            SummaryFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
    }
}
