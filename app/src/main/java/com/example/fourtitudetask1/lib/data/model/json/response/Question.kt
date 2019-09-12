package com.example.fourtitudetask1.lib.data.model.json.response

import com.google.gson.annotations.SerializedName

data class Question(
        @SerializedName("category") val category: String,
        @SerializedName("type") val type: String,
        @SerializedName("difficulty") val difficulty: String,
        @SerializedName("question") var question: String,
        @SerializedName("correct_answer") var correctAnswer: String,
        @SerializedName("incorrect_answers") var incorrectAnswers: List<String>
)