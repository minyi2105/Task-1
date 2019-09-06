package com.example.fourtitudetask1.lib.data.model.json.response

import com.google.gson.annotations.SerializedName

data class QuestionResponse(
        @SerializedName("response_code") val responseCode: Int,
        @SerializedName("results") val questions: List<Question>
)