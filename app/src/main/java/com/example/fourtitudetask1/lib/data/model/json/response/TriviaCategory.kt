package com.example.fourtitudetask1.lib.data.model.json.response

import com.google.gson.annotations.SerializedName

data class TriviaCategory(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String
)