package id.naupal.xweighbridge.presentation.listticket

import android.app.DatePickerDialog
import android.content.Context
import id.naupal.firebase.model.TicketFiler
import id.naupal.ui.BaseBottomSheetDialogFragment
import id.naupal.utils.getMilSecFromSimpleFormat
import id.naupal.utils.getMilliSec
import id.naupal.utils.getReadableDateTime
import id.naupal.xweighbridge.R
import id.naupal.ui.R as uiR
import id.naupal.xweighbridge.databinding.XweighbridgeBsfFilterTicketBinding
import java.util.Calendar

class FilterTicketBsf : BaseBottomSheetDialogFragment() {

    private var activeFilter = TicketFiler()
    private var onFilterChoose: (filter: TicketFiler?) -> Unit = {}
    private var _binding: XweighbridgeBsfFilterTicketBinding? = null
    private val binding: XweighbridgeBsfFilterTicketBinding
        get() = _binding as XweighbridgeBsfFilterTicketBinding

    companion object {
        fun newInstance(
            prevFilter: TicketFiler?,
            cb: (filter: TicketFiler?) -> Unit
        ): FilterTicketBsf {
            return FilterTicketBsf().apply {
                prevFilter?.let { activeFilter = it }
                onFilterChoose = cb
            }
        }
    }

    override fun layoutId(): Int = R.layout.xweighbridge_bsf_filter_ticket

    override fun setupLayoutContent() {
        _binding = XweighbridgeBsfFilterTicketBinding.bind(requireView().findViewById(R.id.bsfRoot))

        binding.run {
            if (activeFilter.starDate > 0) {
                etStartDate.setText(getReadableDateTime(activeFilter.starDate))
            }
            if (activeFilter.endDate > 0) {
                etEndDate.setText(getReadableDateTime(activeFilter.endDate))
            }
            if (activeFilter.driverName.isNotEmpty()) {
                etDriverName.setText(activeFilter.driverName)
            }
            if (activeFilter.licenseNumber.isNotEmpty()) {
                etLicenseNumber.setText(activeFilter.licenseNumber)
            }

            etStartDate.setOnClickListener {
                datePicker(it.context) { millSec ->
                    etStartDate.setText(getReadableDateTime(millSec))
                }
            }

            etEndDate.setOnClickListener {
                datePicker(it.context) { millSec ->
                    etEndDate.setText(getReadableDateTime(millSec + 86399999))
                }
            }
            btnApply.setOnClickListener {
                activeFilter = TicketFiler(
                    starDate = getMilSecFromSimpleFormat(etStartDate.text.toString()),
                    endDate = getMilSecFromSimpleFormat(etEndDate.text.toString()),
                    driverName = etDriverName.text.toString(),
                    licenseNumber = etLicenseNumber.text.toString()
                )
                onFilterChoose.invoke(activeFilter)
                dismissAllowingStateLoss()
            }
            btnClear.setOnClickListener {
                etStartDate.setText("")
                etEndDate.setText("")
                etDriverName.setText("")
                etLicenseNumber.setText("")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun datePicker(context: Context, onPick: (millSec: Long) -> Unit) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(
            context, uiR.style.MyTimePickerDialogTheme, { view, year, monthOfYear, dayOfMonth ->
                val month = String.format("%02d", monthOfYear + 1)
                val hari = String.format("%02d", dayOfMonth)
                val date = "$year-$month-$hari"
                onPick(getMilliSec(date))
            },
            year,
            month,
            day
        ).show()
    }

}