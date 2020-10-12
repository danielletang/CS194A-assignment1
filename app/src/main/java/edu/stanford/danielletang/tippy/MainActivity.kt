package edu.stanford.danielletang.tippy

import android.animation.ArgbEvaluator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "MainActivity"
private const val INITIAL_TIP_PERCENT = 15
private const val INITIAL_SPLIT_BY = 0

class MainActivity : AppCompatActivity() {
    var total = 0.0
    var splitNumber = 1.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstFragment = FirstFragment()
        val secondFragment = SecondFragment()
        val thirdFragment = ThirdFragment()

        bFragment1.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, firstFragment)
                addToBackStack(null)
                commit()
            }
        }

        bFragment2.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, secondFragment)
                addToBackStack(null)
                commit()
            }
        }

        bFragment3.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, thirdFragment)
                addToBackStack(null)
                commit()
            }
        }

        seekBarSplit.progress = INITIAL_SPLIT_BY
        updateSplitDescription(INITIAL_SPLIT_BY)
        seekBarSplit.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.i(TAG, "onProgressChanged $progress")
                tvSplitBy.text = "$progress%"
                updateSplitDescription(progress)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        seekBarTip.progress = INITIAL_TIP_PERCENT
        tvTipPercent.text = "$INITIAL_TIP_PERCENT%"
        updateTipDescription(INITIAL_TIP_PERCENT)
        seekBarTip.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.i(TAG, "onProgressChanged $progress")
                tvTipPercent.text = "$progress%"
                updateTipDescription(progress)
                computeTipAndTotal()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        etBase.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                Log.i(TAG, "afterTextChanged $s")
                computeTipAndTotal()
            }
        })
    }

    private fun updateSplitDescription(splitBy: Int) {
        tvSplitBy.text = (splitBy + 1).toString()
        splitNumber = splitBy + 1.0
    }

    private fun updateTipDescription(tipPercent: Int) {
        val tipDescription : String
        when (tipPercent) { // updates description to Emoji
            in 0..9 -> tipDescription = "\uD83D\uDE41"
            in 10..14 -> tipDescription = "\uD83D\uDE10"
            in 15..19 -> tipDescription = "\uD83D\uDE42"
            in 20..24 -> tipDescription = "\uD83D\uDE00"
            else -> tipDescription = "\uD83E\uDD29"
        }
        tvTipDescription.text = tipDescription
//        val color = ArgbEvaluator().evaluate(
//            tipPercent.toFloat() / seekBarTip.max,
//            ContextCompat.getColor(this, R.color.colorWorstTip),
//            ContextCompat.getColor(this, R.color.colorBestTip)
//         ) as Int
//        tvTipDescription.setTextColor(color)
    }

    private fun computeTipAndTotal() {
        if (etBase.text.isEmpty()) {
            tvTipAmount.text = ""
            tvTotalAmount.text = ""
            return
        }
        val baseAmount = etBase.text.toString().toDouble()
        val tipPercent = seekBarTip.progress
        val tipAmount = baseAmount * tipPercent / 100
        val totalAmount = baseAmount + tipAmount
        total = totalAmount.toDouble()
        tvTipAmount.text = "%.2f".format(tipAmount)
        tvTotalAmount.text = "%.2f".format(totalAmount)
    }
}