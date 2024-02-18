package id.naupal.xweighbridge.di

import android.content.Context
import id.naupal.firebase.di.FirebaseComponentFactory
import id.naupal.sawitpro.SawitProApplication

/**
 * Created by Naupal T. on 07/05/22.
 */

object WeighbridgeComponentFactory {

    fun createComponent(context: Context): WeighbridgeComponent {
        return DaggerWeighbridgeComponent.factory().create(
            appComponent = SawitProApplication.appComponent,
            firebaseComponent = FirebaseComponentFactory.create()
        )
    }
}