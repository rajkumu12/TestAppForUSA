package com.techmaster.onetestapp

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.techmaster.onetestapp.databinding.CalenderdialogBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class CalenderDialog:  DialogFragment() {
    interface DialogListener {
        fun onDataPassed(minDate: String,maxDate:String)
    }

    lateinit var _binding: CalenderdialogBinding
    var minDate:String?=null
    var maxDate:String?=null
    private var dialogListener: DialogListener? = null


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = CalenderdialogBinding.inflate(LayoutInflater.from(requireContext()))
        val view = _binding.root

        val dialog = Dialog(requireContext())
        dialog.setContentView(view)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        _binding.calendarView.selectionMode = MaterialCalendarView.SELECTION_MODE_RANGE
        _binding.calendarView.topbarVisible = false

        _binding.calendarView.setOnRangeSelectedListener { widget, dates ->

        }

        // Add listener to handle range selection
        _binding.calendarView.setOnRangeSelectedListener { widget, dates -> // Handle the selected range of dates
            val selectedDates = mutableListOf<Date>()
            minDate="${dates.get(0).day}:${dates.get(0).month}:${dates.get(0).year}"
            maxDate="${dates.get(dates.size-1).day}:${dates.get(dates.size-1).month}:${dates.get(dates.size-1).year}"
            _binding.tvStartdate.text=parseDate(minDate)
            _binding.tvEnddate.text=parseDate(maxDate)

        }
        _binding.imgBack.setOnClickListener {
            dismiss()
        }
        _binding.cancel.setOnClickListener {
            dismiss()
        }

        _binding.apply.setOnClickListener {
            dialogListener?.onDataPassed(parseDate(minDate).toString(),parseDate(maxDate).toString())
            dismiss()

        }
        return dialog
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }

    fun parseDate(time: String?): String? {
        val inputPattern = "dd:MM:yyyy"
        val outputPattern = "MMM dd,yyyy"
        val inputFormat = SimpleDateFormat(inputPattern)
        val outputFormat = SimpleDateFormat(outputPattern)
        var date: Date? = null
        var str: String? = null
        try {
            date = inputFormat.parse(time)
            str = outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return str
    }
    fun setDialogListener(listener: DialogListener) {
        dialogListener = listener
    }

}