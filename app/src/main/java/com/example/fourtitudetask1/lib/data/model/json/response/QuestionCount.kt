package com.example.fourtitudetask1.lib.data.model.json.response

import com.google.gson.annotations.SerializedName

data class QuestionCount(
        @SerializedName("category_id") var categoryId: Int = 0,
        @SerializedName("category_question_count") val categoryQuestionCount: CategoryQuestionCount
)