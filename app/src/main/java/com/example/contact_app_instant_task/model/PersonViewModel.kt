package com.example.contact_app_instant_task.model


import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.contact_app_instant_task.data.Person
import com.example.contact_app_instant_task.repository.PersonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class PersonViewModel (a:Application) : AndroidViewModel(a){

    val tasks = mutableStateOf<List<Person>>(emptyList())

    val r= PersonRepository(a)



    fun upsertPerson(person: Person){
        viewModelScope.launch(Dispatchers.IO) {
            r.insertPerson(person)
        }
    }

    fun deletePerson(person: Person){
        viewModelScope.launch (Dispatchers.IO){
            r.deletePerson(person)
        }
    }

    fun getAllPerson(): Flow<List<Person>> = r.getAllPerson()

}