package com.example.fourtitudetask1.lib.data.model.json.response

import com.google.gson.annotations.SerializedName

data class SessionToken(
        @SerializedName("response_code") val responseCode: Int,
        @SerializedName("response_message") val responseMessage: String,
        @SerializedName("token") val token: String
)