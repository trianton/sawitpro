package id.naupal.firebase.repo

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import id.naupal.firebase.model.ResultFbState
import id.naupal.firebase.model.TicketDto
import id.naupal.firebase.model.TicketFiler
import id.naupal.firebase.repo.FirestoreRepoImpl.Sort.Companion.fromLabel
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FirestoreRepoImpl : FirestoreRepo {

    companion object {
        private const val ticketColl = "ticket"

        private const val idField = "id"
        private const val dateTimeField = "dateTime"
        private const val licenceNumberField = "licenceNumber"
        private const val driverNameField = "driverName"
        private const val inboundWeightField = "inboundWeight"
        private const val outboundWeightField = "outboundWeight"
        private const val netWeightField = "netWeight"
    }

    enum class Sort(val value: Pair<String, String>) {
        DATE(Pair("Date", dateTimeField)),
        DRIVER_NAME(Pair("Driver Name", driverNameField)),
        LICENSE_NUMBER(Pair("License Number", licenceNumberField));

        companion object {
            val map: MutableMap<String, Sort> = HashMap()

            init {
                for (i in Sort.values()) {
                    map[i.value.first] = i
                }
            }

            fun fromLabel(type: String?): Sort {
                return map[type] ?: DATE
            }
        }
    }

    override suspend fun insertTicket(ticket: TicketDto): ResultFbState =
        suspendCoroutine { continuation ->

            val db = Firebase.firestore
            val batch = db.batch()

            val dataStock = hashMapOf(
                idField to ticket.id,
                dateTimeField to ticket.dateTime,
                licenceNumberField to ticket.licenceNumber,
                driverNameField to ticket.driverName,
                inboundWeightField to ticket.inboundWeight,
                outboundWeightField to ticket.outboundWeight,
                netWeightField to ticket.netWeight
            )
            val refStock = db.collection(ticketColl).document(ticket.id)

            batch.set(refStock, dataStock)


            batch.commit().addOnSuccessListener {
                continuation.resume(ResultFbState.Success.InsertTicket(ticket))
            }.addOnFailureListener {
                continuation.resume(ResultFbState.Error(it, it.message))
            }
        }

    override suspend fun getTickets(sortBy: String, filter: TicketFiler?): ResultFbState =
        suspendCoroutine { continuation ->

            var collRef = Firebase.firestore.collection(ticketColl)
                .orderBy(fromLabel(sortBy).value.second)

            filter?.let {
                if (it.starDate > 0) {
                    collRef = collRef.whereGreaterThanOrEqualTo(dateTimeField, it.starDate)
                }
                if (it.endDate > 0) {
                    collRef = collRef.whereLessThanOrEqualTo(dateTimeField, it.endDate)
                }
                if (it.driverName.isNotEmpty()) {
                    collRef = collRef.whereEqualTo(driverNameField, it.driverName)
                }
                if (it.licenseNumber.isNotEmpty()) {
                    collRef = collRef.whereEqualTo(licenceNumberField, it.licenseNumber)
                }
            }

            collRef.get().addOnSuccessListener { result ->
                val tickets = result.map { doc ->
                    TicketDto(
                        id = doc.getString(idField) ?: "",
                        dateTime = doc.getLong(dateTimeField) ?: 0,
                        licenceNumber = doc.getString(licenceNumberField) ?: "",
                        driverName = doc.getString(driverNameField) ?: "",
                        inboundWeight = doc.getLong(inboundWeightField) ?: 0,
                        outboundWeight = doc.getLong(inboundWeightField) ?: 0,
                        netWeight = doc.getLong(inboundWeightField) ?: 0
                    )
                }
                continuation.resume(ResultFbState.Success.GetTickets(tickets))
            }.addOnFailureListener { exception ->
                continuation.resume(
                    ResultFbState.Error(
                        throwable = exception.cause,
                        message = exception.message
                    )
                )
            }
        }
}