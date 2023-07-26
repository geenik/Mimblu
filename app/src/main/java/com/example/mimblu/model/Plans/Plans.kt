package com.example.mimblu.model.Plans

data class Plans(
    val _links: Links,
    val _meta: Meta,
    val copyrighths: String,
    val list: List<Plan>
)