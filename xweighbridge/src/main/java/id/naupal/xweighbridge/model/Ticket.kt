package id.naupal.xweighbridge.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Ticket(
    val id: String,
    val dateTime: Long,
    val licenceNumber: String,
    val driverName: String,
    val inboundWeight: Long,
    val outboundWeight: Long,
    val netWeight: Long
) : Parcelable