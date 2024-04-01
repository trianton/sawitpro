package id.naupal.playground

import android.app.Activity
import android.os.Bundle
import id.naupal.navigation.Navigation
import javax.inject.Inject

class MainActivity : Activity() {
    @Inject
    lateinit var posNavigation: Navigation
    override fun onCreate(savedInstanceState: Bundle?) {
        PlaygorundApp.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        startActivity(posNavigation.goToActionName(posNavigation.openXweighbridgeListOfTicketActivity))
        finish()
    }
}