package com.example.contact_app_instant_task.repository

import android.content.Context
import com.example.contact_app_instant_task.data.Person
import com.example.contact_app_instant_task.data.PersonDatabase
import kotlinx.coroutines.flow.Flow

class PersonRepository (context:Context) {

     val db = PersonDatabase.getDatabase(context)

    fun getAllPerson(): Flow<List<Person>> = db.dao().getAllPerson()

    suspend fun insertPerson(person: Person) = db.dao().upsertPerson(person)

    suspend fun deletePerson(person: Person) = db.dao().deletePerson(person)

    suspend fun getPersonById(id:Int) = db.dao().getPersonById(id)

}