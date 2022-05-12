package com.example.art_2.api

import com.example.art_2.data.ArtId
import com.example.art_2.data.ArtObj
import com.example.art_2.data.Department
import com.example.art_2.data.ListDepartmentObj
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArtApi {
    @GET("/public/collection/v1/objects")
    suspend fun getAllIdArt(): ArtId

    @GET("/public/collection/v1/objects/{objectID}")
    suspend fun getArtByID(@Path("objectID") id: Int): ArtObj

    @GET("/public/collection/v1/departments")
    suspend fun getAllDepartments(): ListDepartmentObj

    @GET("/public/collection/v1/objects")
    suspend fun getArtByDepartment(@Query("departmentIds") id: List<Int>): ArtId
}