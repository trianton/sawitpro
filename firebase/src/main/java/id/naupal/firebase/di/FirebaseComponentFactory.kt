package id.naupal.firebase.di

/**
 * Created by Naupal T. on 27/10/22.
 */
object FirebaseComponentFactory {

    private lateinit var daggerComponent: FirebaseComponent

    fun create(): FirebaseComponent {
        if (!this::daggerComponent.isInitialized) {
            daggerComponent = DaggerFirebaseComponent
                .builder()
                .provideFirebaseModule(FirebaseModule())
                .build()
        }
        return daggerComponent
    }
}

