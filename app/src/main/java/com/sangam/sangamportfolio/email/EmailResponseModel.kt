package com.sangam.sangamportfolio.email

import com.google.gson.annotations.SerializedName

data class EmailResponseModel(
    @SerializedName("message") val messageIds: String? = null,
)