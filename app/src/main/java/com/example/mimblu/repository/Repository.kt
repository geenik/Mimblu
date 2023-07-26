package com.example.mimblu.repository

import android.util.Log
import com.example.mimblu.data.RetrofitInstance
import com.example.mimblu.model.Plans.Plans
import com.example.mimblu.model.Symptoms.Symptoms
import kotlinx.coroutines.flow.Flow

class Repository {
        private val service = RetrofitInstance.Service

        suspend fun getSymptoms(): Symptoms {
            Log.d("repo", "1")
            return service.getsymptoms()
        }

        suspend fun getPlan():Plans{
           return service.getplan()
        }
}