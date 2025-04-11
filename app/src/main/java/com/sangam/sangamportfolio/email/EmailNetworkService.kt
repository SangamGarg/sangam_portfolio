package com.sangam.sangamportfolio.email

import com.sangam.sangamportfolio.app_utils.AppUrlsEndpoint
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface EmailNetworkService {

    @POST(AppUrlsEndpoint.SEND_EMAIL)
    fun callEmailApi(
        @Body emailRequestModel: EmailRequestModel?,
    ): Call<EmailResponseModel>
}