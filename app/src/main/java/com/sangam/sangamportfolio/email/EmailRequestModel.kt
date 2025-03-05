package com.sangam.sangamportfolio.email

import com.google.gson.annotations.SerializedName

data class EmailRequestModel(
    @SerializedName("category") val category: String? = null,
    @SerializedName("from") val from: From? = null,
    @SerializedName("subject") val subject: String? = null,
    @SerializedName("text") val text: String? = null,
    @SerializedName("to") val to: List<To>? = arrayListOf()
)

data class From(
    @SerializedName("email") val email: String? = null,
    @SerializedName("name") val name: String? = null
)

data class To(
    @SerializedName("email") val email: String? = null
)