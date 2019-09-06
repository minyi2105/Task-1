package com.example.fourtitudetask1.lib.data.model.json.response

import com.google.gson.annotations.SerializedName

data class QuestionCategory (
        @SerializedName("category_id") val categoryId:Int,
        @SerializedName("category_question_count") val categoryQuestionCount: CategoryQuestionCount
)