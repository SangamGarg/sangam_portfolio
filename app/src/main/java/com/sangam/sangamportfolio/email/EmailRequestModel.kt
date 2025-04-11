package com.sangam.sangamportfolio.email

import com.google.gson.annotations.SerializedName

data class EmailRequestModel(
    @SerializedName("name") val name: String? = null,
    @SerializedName("email") val email: String? = null,
    @SerializedName("subject") val subject: String? = null,
    @SerializedName("body") val body: String? = null,
)