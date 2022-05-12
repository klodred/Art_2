package com.example.art_2.api

import com.example.art_2.data.ArtId
import com.example.art_2.data.ArtObj
import com.example.art_2.data.Department
import com.example.art_2.data.ListDepartmentObj
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RepositoryRetriever {
    private val service: ArtApi

    companion object {
        //1
        const val BASE_URL = "https://collectionapi.metmuseum.org/"
    }

    init {
        // 2
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit = Retrofit.Builder()
            // 1
            .baseUrl(BASE_URL)
            //3
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        //4
        service = retrofit.create(ArtApi::class.java)
    }

    suspend fun getAllIdArt(): ArtId {
        return service.getAllIdArt()
    }

    suspend fun getArtById(id: Int): ArtObj {
        return service.getArtByID(id)
    }

    suspend fun getAllDepartments(): ListDepartmentObj {
        return service.getAllDepartments()
    }

    suspend fun getArtByDepartment(id: List<Int>): ArtId {
        return service.getArtByDepartment(id)
    }
}