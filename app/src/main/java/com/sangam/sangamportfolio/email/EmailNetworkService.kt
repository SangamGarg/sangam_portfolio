package com.sangam.sangamportfolio.email

import com.sangam.sangamportfolio.app_utils.AppUrlsEndpoint
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface EmailNetworkService {

    @POST(AppUrlsEndpoint.SEND_EMAIL)
    @Headers(
        "Authorization: Bearer e3cd7ed2fb1dae5952e80ded1e1a96a9", "Content-Type: application/json"
    )
    fun callEmailApi(
        @Body emailRequestModel: EmailRequestModel?
    ): Call<EmailResponseModel>
}