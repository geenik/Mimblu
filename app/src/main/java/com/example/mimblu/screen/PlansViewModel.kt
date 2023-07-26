package com.example.mimblu.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mimblu.model.Plans.Plans
import com.example.mimblu.model.Symptoms.Symptoms
import com.example.mimblu.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PlansViewModel:ViewModel() {
    private val repo = Repository()
    private val _plans = MutableStateFlow<Plans?>(null)
    val plans: Flow<Plans?> = _plans

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                val data = repo.getPlan()
                _plans.value = data
                Log.d("viewmodal", data.list.size.toString())

            } catch (e: Exception) {
                Log.d("viewmodal", e.message.toString())
            }
        }
    }
}