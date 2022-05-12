package com.example.art_2.data

import com.google.gson.annotations.SerializedName
//import kotlinx.serialization.Serializable
import java.io.Serializable

//data class ArtList(val items: List<ArtData>)
data class ArtObj(
    var id: Int?,

    @SerializedName("title")
    val name: String?,

    @SerializedName("primaryImageSmall")
    val urlSmallImage: String?,

    @SerializedName("accessionYear")
    val year: String?,

    @SerializedName("country")
    val country: String?,

    @SerializedName("artistDisplayName")
    val author: String?,

    @SerializedName("primaryImage")
    val urlPrimaryImage: String?,

    @SerializedName("additionalImages")
    val listUrlImage: List<String>?

) : Serializable {}

data class ArtId(
    @SerializedName("objectIDs")
    val id: List<Int>
)

data class Department(
    @SerializedName("departmentId")
    val id: Int?,

    @SerializedName("displayName")
    val name: String?
)

data class ListDepartmentObj(
    @SerializedName("departments")
    val department: List<Department>
)
