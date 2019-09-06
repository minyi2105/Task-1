package com.example.fourtitudetask1.lib.data.model.json.response

import com.google.gson.annotations.SerializedName

data class QuestionCountVM(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val categoryTitle: String,
        @SerializedName("total_question_count") val totalQuestionCount: Int,
        @SerializedName("total_easy_question_count") val totalEasyQuestionCount: Int,
        @SerializedName("total_medium_question_count") val totalMediumQuestionCount: Int,
        @SerializedName("total_hard_question_count") val totalHardQuestionCount: Int
)