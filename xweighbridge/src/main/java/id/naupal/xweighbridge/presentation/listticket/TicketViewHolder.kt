package id.naupal.xweighbridge.presentation.listticket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import id.naupal.utils.getReadableDateTime
import id.naupal.xweighbridge.databinding.XweighbridgeItemTicketBinding
import id.naupal.xweighbridge.model.Ticket

class TicketViewHolder(@LayoutRes layout: Int, parent: ViewGroup) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layout, parent, false)) {

    fun bind(
        item: Ticket,
        onItemClicked: (item: Ticket) -> Unit,
        onItemEdited: (item: Ticket) -> Unit
    ) = with(XweighbridgeItemTicketBinding.bind(itemView)) {
        txtDateTime.text = getReadableDateTime(item.dateTime)
        txtDriverName.text = item.driverName
        txtLicenseNumber.text = item.licenceNumber
        txtNetWeight.text = "Net Weight:${item.netWeight}"
        btnEdit.setOnClickListener { onItemEdited(item) }
        btnView.setOnClickListener { onItemClicked(item) }
    }
}