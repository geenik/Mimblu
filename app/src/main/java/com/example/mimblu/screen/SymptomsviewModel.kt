package com.example.mimblu.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mimblu.model.Symptoms.Symptom
import com.example.mimblu.model.Symptoms.Symptoms
import com.example.mimblu.repository.Repository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SymptomsviewModel:ViewModel() {
    private val repo = Repository()
    private val _symptoms = MutableStateFlow<Symptoms?>(null)
    val symptoms: Flow<Symptoms?> = _symptoms

    init {
        Log.d("viewmodal", "init1")
        fetchData()
        Log.d("viewmodal", "init2")
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                val data = repo.getSymptoms()
                _symptoms.value = data
                Log.d("viewmodal", data.list.size.toString())

            } catch (e: Exception) {
                Log.d("viewmodal", e.message.toString())
            }
        }
    }

}