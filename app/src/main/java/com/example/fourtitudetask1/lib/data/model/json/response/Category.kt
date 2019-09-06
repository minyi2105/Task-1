package com.example.fourtitudetask1.lib.data.model.json.response

import com.google.gson.annotations.SerializedName

data class Category(
        @SerializedName("trivia_categories") val triviaCategoriesList: List<TriviaCategory>
)