package com.example.fourtitudetask1.lib.data.model.json.response

import com.google.gson.annotations.SerializedName

data class CategoryQuestionCount(
        @SerializedName("total_question_count") var totalQuestionCount: Int = 0,
        var categoryId: Int = 0,
        var categoryName: String? = null,
        @SerializedName("total_easy_question_count") var totalEasyQuestionCount: Int = 0,
        @SerializedName("total_medium_question_count") var totalMediumQuestionCount: Int = 0,
        @SerializedName("total_hard_question_count") var totalHardQuestionCount: Int = 0
)