package id.naupal.xcomposetdd.contactlist

import id.naupal.xcomposetdd.model.Contact
import retrofit2.Response

interface ContactRepository {
    suspend fun getContacts(): Response<List<Contact>>
}