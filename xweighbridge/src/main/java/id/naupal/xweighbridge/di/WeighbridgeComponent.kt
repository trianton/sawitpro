package id.naupal.xweighbridge.di

import dagger.Component
import id.naupal.firebase.di.FirebaseComponent
import id.naupal.sawitpro.di.AppComponent
import id.naupal.sawitpro.di.FeatureScope
import id.naupal.xweighbridge.presentation.inputticket.InputTicketActivity
import id.naupal.xweighbridge.presentation.listticket.ListOfTicketActivity

@FeatureScope
@Component(
    modules = [
        ViewModelModule::class,
        MapperModule::class,
        DomainModule::class
    ],
    dependencies = [
        AppComponent::class,
        FirebaseComponent::class
    ]
)
interface WeighbridgeComponent {
    @Component.Factory
    interface Factory {
        fun create(
            appComponent: AppComponent,
            firebaseComponent: FirebaseComponent
        ): WeighbridgeComponent
    }

    fun inject(activity: ListOfTicketActivity)
    fun inject(activity: InputTicketActivity)
}