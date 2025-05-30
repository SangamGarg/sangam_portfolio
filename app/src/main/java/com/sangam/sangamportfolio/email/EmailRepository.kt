package com.sangam.sangamportfolio.email

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.sangam.sangamportfolio.retrofitUtils.RetrofitUtilClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmailRepository {
    val showProgress = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    val emailResponse = MutableLiveData<EmailResponseModel>()

    fun postEmail(emailRequestModel: EmailRequestModel?) {
        showProgress.value = true
        val client = RetrofitUtilClass.getRetrofitEmail().create(EmailNetworkService::class.java)
        val call = client.callEmailApi(emailRequestModel)
        call.enqueue(object : Callback<EmailResponseModel?> {
            override fun onResponse(
                call: Call<EmailResponseModel?>, response: Response<EmailResponseModel?>,
            ) {
                showProgress.postValue(false)
                val body = response.body()
                Log.d("emailResponse", "body : ${body.toString()}")

                if (response.isSuccessful) {
                    emailResponse.postValue(body!!)
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("emailResponse", "Error: $errorBody")
                    if (response.code() == 404) {
                        errorMessage.postValue("User not exist, please sign up")
                    } else {
                        errorMessage.postValue("Error: $errorBody")
                    }
                }
            }

            override fun onFailure(call: Call<EmailResponseModel?>, t: Throwable) {
                Log.d("emailResponse", "failed : ${t.localizedMessage}")
                showProgress.postValue(false)
                errorMessage.postValue("Server error please try after sometime")
            }

        })
    }

}