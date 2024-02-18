package id.naupal.xweighbridge.presentation.listticket

import android.os.Handler
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.naupal.xweighbridge.R
import id.naupal.xweighbridge.model.Ticket

class TicketAdapter(
    private val onClick: (item: Ticket) -> Unit,
    private val onEdit: (item: Ticket) -> Unit,
) : RecyclerView.Adapter<TicketViewHolder>() {

    private var items: List<Ticket> = mutableListOf()

    fun setData(newItems: List<Ticket>) {
        items = newItems
        Handler().post { notifyDataSetChanged() }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        return TicketViewHolder(R.layout.xweighbridge_item_ticket, parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        holder.bind(items[position], onClick, onEdit)
    }
}