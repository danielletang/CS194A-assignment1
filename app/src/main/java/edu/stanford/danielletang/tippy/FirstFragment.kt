package edu.stanford.danielletang.tippy

import android.content.Context
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment : BaseFragment(){
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val totalAmount = ACTIVITY.total
        val splitBy = ACTIVITY.splitNumber
        val perPersonAmount = totalAmount / splitBy
        if (perPersonAmount > 0) {
            tvPerPersonAmount.text = "%.2f".format(perPersonAmount)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }
}