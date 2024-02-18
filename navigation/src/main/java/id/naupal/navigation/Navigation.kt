package id.naupal.navigation

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager

class Navigation (private val context: Context) {
    val openXweighbridgeListOfTicketActivity = "open.xweighbridge.ListOfTicketActivity"
    val openXweighbridgeInputTicketActivity = "open.xweighbridge.InputTicketActivity"

    fun goToActionName(actionName: String, keepBackStack: Boolean = true): Intent {
        return getIntent(actionName).apply {
            if (!keepBackStack) addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }

    private fun getIntent(actionName: String): Intent {
        return Intent().apply {
            action = actionName
        }
    }

    fun openDfmIntent(actionName: String, keepBackStack: Boolean = true): Intent? {
        val intent = getIntent(actionName)

        val className = with(
            context.packageManager.queryIntentActivities(
                intent,
                PackageManager.MATCH_DEFAULT_ONLY
            ).firstOrNull()
        ) {
            this?.let {
                Class.forName(it.activityInfo.name)
            }
        }
        return with(className) {
            Intent(context, this).apply {
                if (!keepBackStack) addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            }
        }
    }
}