package com.example.mimblu.model.Symptoms

data class Symptoms(
    val _links: Links,
    val _meta: Meta,
    val copyrighths: String,
    val list: List<Symptom>
)