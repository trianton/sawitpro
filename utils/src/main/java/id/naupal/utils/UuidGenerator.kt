package id.naupal.utils

import java.util.Locale
import java.util.UUID


fun generateUuid(): String {
    return UUID.randomUUID().toString().replace("-".toRegex(), "").uppercase(Locale.getDefault())
}