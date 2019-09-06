package com.example.fourtitudetask1.lib.data.model.json.response

import com.google.gson.annotations.SerializedName

data class CategoryQuestionCount(
        @SerializedName("total_question_count") val totalQuestionCount: Int,
        @SerializedName("total_easy_question_count") val totalEasyQuestionCount: Int,
        @SerializedName("total_medium_question_count") val totalMediumQuestionCount: Int,
        @SerializedName("total_hard_question_count") val totalHardQuestionCount: Int
)