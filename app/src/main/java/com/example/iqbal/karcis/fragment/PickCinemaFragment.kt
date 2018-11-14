package com.example.iqbal.karcis.fragment

import android.content.Context
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast

import com.example.iqbal.karcis.R
import kotlinx.android.synthetic.main.fragment_pick_cinema.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_SECTION_NUMBER = "param1"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PickCinemaFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PickCinemaFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class PickCinemaFragment : Fragment(), AdapterView.OnItemSelectedListener {

    // TODO: Rename and change types of parameters
    private var page: Int? = null
    private var listener: OnFragmentInteractionListener? = null
    private var item1: Boolean = false
    private var item2: Boolean = false
    private var item3: Boolean = false

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
        val v : View = inflater.inflate(R.layout.fragment_pick_cinema, container, false)
        val spinnerCity: Spinner = v.findViewById(R.id.spinner_city)
        spinnerCity.onItemSelectedListener = this
        ArrayAdapter.createFromResource(
            context, R.array.city_array, R.layout.spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerCity.adapter = adapter
        }

        val spinnerCinema: Spinner = v.findViewById(R.id.spinner_cinema)
        spinnerCinema.onItemSelectedListener = this
        ArrayAdapter.createFromResource(
            context, R.array.cinema_array, R.layout.spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerCinema.adapter = adapter
        }

        val spinnerTime: Spinner = v.findViewById(R.id.spinner_time)
        spinnerTime.onItemSelectedListener = this
        ArrayAdapter.createFromResource(
            context, R.array.time_array, R.layout.spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerTime.adapter = adapter
        }

        return v
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if(parent?.id == R.id.spinner_city){
            if(position != 0){
                item1 = true
            }else{
                item1 = false
            }
        }else if(parent?.id == R.id.spinner_cinema){
            if(position != 0){
                item2 = true
            }else{
                item2 = false
            }
        }else if(parent?.id == R.id.spinner_time){
            if(position != 0){
                item3 = true
            }else{
                item3 = false
            }
        }

        if(item1 && item2 && item3){
            swipe_next.visibility = View.VISIBLE
        }else{
            swipe_next.visibility = View.GONE
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

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
         * @return A new instance of fragment PickCinemaFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(sectionNumber: Int) =
            PickCinemaFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
    }
}
