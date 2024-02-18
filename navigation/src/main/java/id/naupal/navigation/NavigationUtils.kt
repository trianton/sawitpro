package id.naupal.navigation

import android.content.Context
import android.content.Intent

/**
 * Created by Naupal T. on 23/02/21.
 */

private fun intentTo(context: Context, className: String): Intent =
    Intent(Intent.ACTION_VIEW).setClassName(context, className)

internal fun String.loadIntentOrNull(context: Context?): Intent? =
    context?.let {
        try {
            Class.forName(this).run { intentTo(context, this@loadIntentOrNull) }
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
            null
        }
    }
