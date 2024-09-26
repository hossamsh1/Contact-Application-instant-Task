package com.example.contact_app_instant_task.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {

    @Upsert
    suspend fun upsertPerson(person: Person)

    @Delete
    suspend fun deletePerson(person: Person)

    @Query("SELECT * FROM person")
    fun getAllPerson(): Flow<List<Person>>

    @Query("SELECT * FROM person WHERE id = :id")
    suspend fun getPersonById(id:Int):Person?
}

