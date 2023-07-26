package com.example.mimblu.model.Symptoms

data class Symptom(
    val created_by_id: Int,
    val created_on: String,
    val id: Int,
    val is_selected: Boolean,
    val state_id: Int,
    val title: String,
    val type_id: Int
)