package id.naupal.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Naupal T. on 16/09/23.
 */

const val ISO_OFFSET_DATE_TIME_MILLI = "yyyy-MM-dd.HH:mm:ss.SSS"
const val EEE_dd_MMM_yyyy_DATE_FORMAT = "EEEE, d MMM yyyy HH:mm"//Senin, 4 Feb 2020 HH:mm
const val SIMPLE_DATE_TIME_MILLI = "dd-MM-yyyy, HH:mm:ss"
const val YYYY_MM_DD_DATE_FORMAT = "yyyy-MM-dd"

private fun generateSimpleDateFormatIndonesia(format: String): SimpleDateFormat {
    val id = Locale("in", "ID")
    return SimpleDateFormat(format, id)
}


/**
 * Return Current Time
 * "2023-08-21.12:30:11:001"
 */
fun getCurrentTimeIsoReadAbel(): String {
    val sdp = generateSimpleDateFormatIndonesia(ISO_OFFSET_DATE_TIME_MILLI)

    val cal = Calendar.getInstance()
    cal.timeInMillis = System.currentTimeMillis()

    return sdp.format(cal.time)
}

/**
 * Convert
 * param "2023-08-21.12:30:11:001"
 * result "Senin, 4 Feb 2020 14:35"
 */
fun convertFromIsoToReadableDaily(isoDateTime: String): String {
    return try {
        val sourceFormat: SimpleDateFormat =
            generateSimpleDateFormatIndonesia(ISO_OFFSET_DATE_TIME_MILLI)
        val date: Date? = sourceFormat.parse(isoDateTime)

        val targetFormat: SimpleDateFormat =
            generateSimpleDateFormatIndonesia(EEE_dd_MMM_yyyy_DATE_FORMAT)
        return targetFormat.format(date?.time)
    } catch (ex: Exception) {
        return isoDateTime
    }
}

/**
 * Return Current Time
 * "2023-08-21.12:30:11"
 */
fun getReadableDateTime(milSec: Long? = null): String {
    val sdp = generateSimpleDateFormatIndonesia(SIMPLE_DATE_TIME_MILLI)

    val cal = Calendar.getInstance()
    cal.timeInMillis = milSec ?: System.currentTimeMillis()

    return sdp.format(cal.time)
}

fun getMilSecFromReadableTime(dateTime: String, format: String): Long {
    return try {
        val sourceFormat: SimpleDateFormat = generateSimpleDateFormatIndonesia(format)
        sourceFormat.parse(dateTime)?.time ?: 0
    }catch (ex: Exception){
        0
    }
}

fun getMilSecFromSimpleFormat(dateTime: String):Long{
    return getMilSecFromReadableTime(dateTime, SIMPLE_DATE_TIME_MILLI)
}


fun getMilliSec(yyyyMMdd: String): Long {
    return try {
        val sourceFormat: SimpleDateFormat = generateSimpleDateFormatIndonesia(
            YYYY_MM_DD_DATE_FORMAT
        )
        val date: Date? = sourceFormat.parse(yyyyMMdd)
        date?.time ?: 0
    } catch (ex: Exception) {
        0
    }
}