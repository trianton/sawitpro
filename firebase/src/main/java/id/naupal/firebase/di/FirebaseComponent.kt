package id.naupal.firebase.di

import dagger.Component
import id.naupal.firebase.repo.FirestoreRepo

@Component(
    modules = [
        FirebaseModule::class
    ]
)
interface FirebaseComponent {
    @Component.Builder
    interface Builder {
        fun provideFirebaseModule(firebaseModule: FirebaseModule): Builder
        fun build(): FirebaseComponent
    }

    fun provideFirestoreRepo(): FirestoreRepo
}