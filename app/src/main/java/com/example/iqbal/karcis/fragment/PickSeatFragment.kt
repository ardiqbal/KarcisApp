package com.example.iqbal.karcis.fragment

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView

import com.example.iqbal.karcis.R
import com.example.iqbal.karcis.model.Seat
import kotlinx.android.synthetic.main.fragment_pick_seat.*
import kotlinx.android.synthetic.main.item_seat.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_SECTION_NUMBER = "param1"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PickSeatFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PickSeatFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class PickSeatFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var page: Int? = null
    private var listener: OnFragmentInteractionListener? = null
    var seatList = ArrayList<Seat>()
    var adapter: SeatAdapter? = null
    private var ordered: Int = 0
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
        val v: View = inflater.inflate(R.layout.fragment_pick_seat, container, false)
        val gridSeat: GridView = v.findViewById(R.id.grid_seat)
        mSharedPref = context!!.getSharedPreferences(getString(R.string.order), Context.MODE_PRIVATE)

        // load seats
        for(i in 1..100){
            seatList.add(Seat(0))
        }

        adapter = SeatAdapter(this.context!!, seatList)

        gridSeat.adapter = adapter

        gridSeat.setOnItemClickListener { parent, view, position, id ->
            if(seatList[position].seat == 0){
                seatList[position].seat = 1
                view.item_seat.setBackgroundResource(R.color.colorAccent)
                ordered++
            }else{
                seatList[position].seat = 0
                view.item_seat.setBackgroundResource(android.R.color.darker_gray)
                ordered--
            }

            with (mSharedPref.edit()) {
                putInt(getString(R.string.order_count), ordered)
                apply()
            }
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
         * @return A new instance of fragment PickSeatFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(sectionNumber: Int) =
            PickSeatFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
    }

    inner class SeatAdapter : BaseAdapter {
        private var seatList = ArrayList<Seat>()
        private var context: Context? = null

        constructor(context: Context, seatList: ArrayList<Seat>) : super() {
            this.context = context
            this.seatList = seatList
        }

        override fun getCount(): Int {
            return seatList.size
        }

        override fun getItem(position: Int): Any {
            return seatList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val seat = this.seatList[position]

            val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val seatView = inflater.inflate(R.layout.item_seat, parent, false)

            /*seatView.setOnClickListener{
                if(seat.seat == 0){
                    seat.seat = 1
                    seatView.item_seat.setBackgroundResource(R.color.colorAccent)
                    ordered++
                }else{
                    seat.seat = 0
                    seatView.item_seat.setBackgroundResource(android.R.color.darker_gray)
                    ordered--
                }
            }*/

            return seatView
        }
    }
}
