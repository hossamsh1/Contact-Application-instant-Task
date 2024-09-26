package com.example.contact_app_instant_task.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "person")
data class Person (
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    var name:String,
    var number:String
)