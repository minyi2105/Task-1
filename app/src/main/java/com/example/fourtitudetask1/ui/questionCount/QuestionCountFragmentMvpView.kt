package com.example.fourtitudetask1.ui.questionCount

import com.example.fourtitudetask1.lib.data.model.json.response.QuestionCountVM

interface QuestionCountFragmentMvpView {
    fun setQuestionCategoryCount(categoryList: List<QuestionCountVM>?)
    fun showProgress()
    fun hideProgress()
}