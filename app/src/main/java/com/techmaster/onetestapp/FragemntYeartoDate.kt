package com.techmaster.onetestapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.techmaster.onetestapp.databinding.FragmentFragemntYeartoDateBinding


class FragemntYeartoDate : Fragment(),CalenderDialog.DialogListener{


    lateinit var binding:FragmentFragemntYeartoDateBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding= FragmentFragemntYeartoDateBinding.inflate(layoutInflater)

        val earningsData = getEarningsData()
        val bookingsData = getBookingsData()

        // Set up line chart
        setupLineChart(earningsData, bookingsData)



        binding.tvDate.setOnClickListener {
            showCustomDialog()
        }

        return binding.root
    }

    private fun showCustomDialog() {
        val dialogFragment = CalenderDialog()
        dialogFragment.setDialogListener(this)
        dialogFragment.show(childFragmentManager, "CustomDialogFragment")
    }

    private fun getEarningsData(): List<Entry> {
        // Replace with your actual data for earnings
        val data = ArrayList<Entry>()
        data.add(Entry(0f, 580f)) // Monday
        data.add(Entry(1f, 480f)) // Tuesday
        data.add(Entry(2f, 680f)) // Tuesday
        data.add(Entry(3f, 590f)) // Tuesday
        data.add(Entry(4f, 420f)) // Tuesday
        data.add(Entry(5f, 390f)) // Tuesday
        data.add(Entry(6f, 575f)) // Tuesday
        // Add data for other days...
        return data
    }

    private fun getBookingsData(): List<Entry> {
        // Replace with your actual data for bookings
        val data = ArrayList<Entry>()
        data.add(Entry(0f, 430f)) // Monday
        data.add(Entry(1f, 630f)) // Tuesday
        data.add(Entry(2f, 445f)) // Tuesday
        data.add(Entry(3f, 410f)) // Tuesday
        data.add(Entry(4f, 390f)) // Tuesday
        data.add(Entry(5f, 450f)) // Tuesday
        data.add(Entry(6f, 410f)) // Tuesday
        // Add data for other days...
        return data
    }

    private fun setupLineChart(earningsData: List<Entry>, bookingsData: List<Entry>) {
        val earningsDataSet = LineDataSet(earningsData, "Earnings")
        earningsDataSet.color = ContextCompat.getColor(requireContext(), R.color.theme)
        earningsDataSet.valueTextColor = ContextCompat.getColor(requireContext(), R.color.theme)
        earningsDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER

        val bookingsDataSet = LineDataSet(bookingsData, "Bookings")
        bookingsDataSet.color = ContextCompat.getColor(requireContext(), R.color.booking)
        bookingsDataSet.valueTextColor = ContextCompat.getColor(requireContext(), R.color.booking)
        bookingsDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        val lineData = LineData(earningsDataSet, bookingsDataSet)

        binding.lineChart.data = lineData
        binding.lineChart.description.isEnabled = false

        val xAxis = binding.lineChart.xAxis
        xAxis.valueFormatter = DayAxisValueFormatter()
        xAxis.position = XAxis.XAxisPosition.BOTTOM

        val yAxisRight = binding.lineChart.axisRight
        yAxisRight.isEnabled = false
        binding.apply {
            lineChart.axisLeft.setDrawGridLines(false);
            lineChart.xAxis.setDrawGridLines(false);
            lineChart.extraBottomOffset = 10f;
            lineChart.invalidate()
        }

    }

    private class DayAxisValueFormatter : ValueFormatter() {
        private val days = arrayOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")

        override fun getAxisLabel(value: Float, axis: AxisBase): String {
            val index = value.toInt()
            return if (index >= 0 && index < days.size) {
                days[index]
            } else {
                ""
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onDataPassed(minDate: String, maxDate: String) {
        binding.tvDate.text="$minDate - $maxDate"
    }

}