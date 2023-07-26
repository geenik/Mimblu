package com.example.mimblu.data

import com.example.mimblu.model.Plans.Plans
import com.example.mimblu.model.Symptoms.Symptoms
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface mimbluApi {

    @GET("user/symptoms")
    suspend fun getsymptoms(): Symptoms

    @GET("plan/all")
    suspend fun getplan(): Plans
}