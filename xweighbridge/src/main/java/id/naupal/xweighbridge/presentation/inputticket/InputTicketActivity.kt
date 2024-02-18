package id.naupal.xweighbridge.presentation.inputticket

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import id.naupal.ui.BaseViewBindingActivity
import id.naupal.ui.databinding.LayoutToolbarBinding
import id.naupal.utils.extension.observe
import id.naupal.utils.generateUuid
import id.naupal.utils.getMilSecFromSimpleFormat
import id.naupal.utils.getMilliSec
import id.naupal.utils.getReadableDateTime
import id.naupal.ui.R as uiR
import id.naupal.xweighbridge.databinding.XweighbridgeActivityInputTicketBinding
import id.naupal.xweighbridge.di.WeighbridgeComponentFactory
import id.naupal.xweighbridge.model.Ticket
import id.naupal.xweighbridge.model.UiState
import java.util.Calendar
import javax.inject.Inject

class InputTicketActivity : BaseViewBindingActivity<XweighbridgeActivityInputTicketBinding>() {
    companion object {
        const val PARAM_TICKET = "PARAM_TICKET"
        const val PARAM_IS_EDIT = "PARAM_IS_EDIT"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var inputTicketViewModel: InputTicketViewModel

    private var activeTicket: Ticket? = null
    private var isEdit: Boolean = false

    override fun getLayoutBinding() = XweighbridgeActivityInputTicketBinding.inflate(layoutInflater)

    override fun setupDependencyInjection() {
        WeighbridgeComponentFactory.createComponent(this).inject(this)
        inputTicketViewModel =
            ViewModelProvider(this, viewModelFactory)[InputTicketViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activeTicket = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(PARAM_TICKET, Ticket::class.java)
        } else {
            intent.getParcelableExtra(PARAM_TICKET)
        }
        isEdit = intent.getBooleanExtra(PARAM_IS_EDIT, false)

        setupToolbar()
        renderExistingData()
        observe(inputTicketViewModel.insertTicketState, ::handleInsertTicketState)
        setupListener()
    }

    private fun setupListener() {
        with(binding) {
            btnSave.setOnClickListener {
                val tmpTicket = Ticket(
                    id = generateUuid(),
                    dateTime = getMilSecFromSimpleFormat(etDateTime.text.toString()),
                    licenceNumber = etLicenseNumber.text.toString(),
                    driverName = etDriverName.text.toString(),
                    inboundWeight = etInboundWeight.text.toString().toLong(),
                    outboundWeight = etOutboundWeight.text.toString().toLong(),
                    netWeight = etNetWeight.text.toString().toLong()
                )
                inputTicketViewModel.insertTicket(tmpTicket)
            }

            etDateTime.setOnClickListener {
                datePicker(it.context) { millSec ->
                    etDateTime.setText(getReadableDateTime(millSec))
                }
            }
        }
    }

    private fun setupToolbar() {
        val toolbarBinding: LayoutToolbarBinding = LayoutToolbarBinding.bind(binding.root)
        with(toolbarBinding) {
            toolbarSync.visibility = View.GONE
            toolbarFilter.visibility = View.GONE
            toolbarTxtTitle.text = if (activeTicket == null) "Create Ticket" else {
                if (isEdit) "Edit Ticket" else "View Ticket"
            }
            toolbarImgHome.setImageResource(uiR.drawable.ic_arrow_back_24)
            toolbarImgHome.setOnClickListener { finishAndRemoveTask() }
        }
    }

    private fun renderExistingData() {
        activeTicket?.let {
            binding.etDateTime.setText(getReadableDateTime(it.dateTime))
            binding.etDriverName.setText(it.driverName)
            binding.etLicenseNumber.setText(it.licenceNumber)
            binding.etInboundWeight.setText("${it.inboundWeight}")
            binding.etOutboundWeight.setText("${it.outboundWeight}")
            binding.etNetWeight.setText("${it.netWeight}")
        }
    }

    private fun handleInsertTicketState(uiState: UiState) {
        when (uiState) {
            is UiState.Loading -> {
                Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
            }

            is UiState.Error -> {
                Toast.makeText(this, "Error ${uiState.error}", Toast.LENGTH_SHORT).show()
            }

            is UiState.Success.InputTicket -> {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                setResult(RESULT_OK)
                finish()
            }

            else -> {
                Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun datePicker(context: Context, onPick: (millSec: Long) -> Unit) {
        val currentDateTime = Calendar.getInstance()
        val startYear = currentDateTime.get(Calendar.YEAR)
        val startMonth = currentDateTime.get(Calendar.MONTH)
        val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
        val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
        val startMinute = currentDateTime.get(Calendar.MINUTE)

        DatePickerDialog(context, { _, year, month, day ->
            TimePickerDialog(context, { _, hour, minute ->
                val pickedDateTime = Calendar.getInstance()
                pickedDateTime.set(year, month, day, hour, minute)
                onPick(pickedDateTime.timeInMillis)
            }, startHour, startMinute, false).show()
        }, startYear, startMonth, startDay).show()
    }
}