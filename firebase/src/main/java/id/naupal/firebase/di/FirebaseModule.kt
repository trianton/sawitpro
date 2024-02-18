package id.naupal.firebase.di

import dagger.Module
import dagger.Provides
import id.naupal.firebase.repo.FirestoreRepo
import id.naupal.firebase.repo.FirestoreRepoImpl

@Module
class FirebaseModule {

    @Provides
    fun provideFirestoreRepository(): FirestoreRepo {
        return FirestoreRepoImpl()
    }

}