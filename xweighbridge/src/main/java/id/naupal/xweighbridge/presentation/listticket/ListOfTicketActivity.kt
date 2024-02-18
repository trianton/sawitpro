package id.naupal.xweighbridge.presentation.listticket

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import id.naupal.ui.R
import id.naupal.firebase.model.TicketFiler
import id.naupal.firebase.repo.FirestoreRepoImpl
import id.naupal.navigation.Navigation
import id.naupal.ui.BaseViewBindingActivity
import id.naupal.ui.databinding.LayoutToolbarBinding
import id.naupal.utils.extension.observe
import id.naupal.xweighbridge.databinding.XweighbridgeActivityListOfTicketBinding
import id.naupal.xweighbridge.di.WeighbridgeComponentFactory
import id.naupal.xweighbridge.model.Ticket
import id.naupal.xweighbridge.model.UiState
import javax.inject.Inject

class ListOfTicketActivity : BaseViewBindingActivity<XweighbridgeActivityListOfTicketBinding>() {
    @Inject
    lateinit var navigation: Navigation

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var listOfTicketViewModel: ListOfTicketViewModel
    private lateinit var adapter: TicketAdapter

    private val sortByLabel = arrayOf(
        FirestoreRepoImpl.Sort.DATE.value.first,
        FirestoreRepoImpl.Sort.DRIVER_NAME.value.first,
        FirestoreRepoImpl.Sort.LICENSE_NUMBER.value.first
    )

    private var selectedSortIndex: Int = 0
    private var activeFilter: TicketFiler? = null

    private var resultInputLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            triggerGetData()
        }
    }

    override fun getLayoutBinding() =
        XweighbridgeActivityListOfTicketBinding.inflate(layoutInflater)

    override fun setupDependencyInjection() {
        WeighbridgeComponentFactory.createComponent(this).inject(this)
        listOfTicketViewModel =
            ViewModelProvider(this, viewModelFactory)[ListOfTicketViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupToolbar()
        adapter = TicketAdapter(::onItemClick, ::onItemEdited)
        binding.rvTicket.adapter = adapter
        observe(listOfTicketViewModel.getTicketsState, ::handleInsertTicketState)
        setupListener()
        triggerGetData()
    }

    private fun triggerGetData() {
        listOfTicketViewModel.getTickets(sortByLabel[selectedSortIndex], activeFilter)
    }

    private fun setupToolbar() {
        val toolbarBinding: LayoutToolbarBinding = LayoutToolbarBinding.bind(binding.root)
        with(toolbarBinding) {
            toolbarSync.visibility = View.VISIBLE
            toolbarFilter.visibility = View.VISIBLE
            toolbarTxtTitle.text = "Weighbridge Tickets"
            toolbarImgHome.setImageResource(R.drawable.ui_ic_home_24)
            toolbarSync.setImageResource(R.drawable.baseline_sort_24)
            toolbarImgHome.setOnClickListener { finishAndRemoveTask() }
            toolbarSync.setOnClickListener {
                showRadioConfirmationDialog()
            }
            toolbarFilter.setOnClickListener {
                FilterTicketBsf.newInstance(activeFilter, ::handlePemilihanFilter)
                    .show(supportFragmentManager, "BSF")
            }
        }
    }

    private fun handlePemilihanFilter(filter: TicketFiler?) {
        activeFilter = filter
        triggerGetData()
    }

    private fun setupListener() {
        binding.fabAddTicket.setOnClickListener {
            navigation.goToActionName(navigation.openXweighbridgeInputTicketActivity).apply {
                resultInputLauncher.launch(this)
            }
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

            is UiState.Success.GetTickets -> {
                adapter.setData(uiState.tickets)
            }

            else -> {
                Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showRadioConfirmationDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Sort by")
            .setSingleChoiceItems(sortByLabel, selectedSortIndex) { dialog, which ->
                if (which != selectedSortIndex) {
                    selectedSortIndex = which
                    triggerGetData()
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun onItemClick(ticket: Ticket) {
        navigation.goToActionName(navigation.openXweighbridgeInputTicketActivity).apply {
            putExtra("PARAM_TICKET", ticket)
            resultInputLauncher.launch(this)
        }
    }

    private fun onItemEdited(ticket: Ticket) {
        navigation.goToActionName(navigation.openXweighbridgeInputTicketActivity).apply {
            putExtra("PARAM_TICKET", ticket)
            putExtra("PARAM_IS_EDIT", true)
            resultInputLauncher.launch(this)
        }
    }
}