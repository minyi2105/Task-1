package com.example.fourtitudetask1.ui.questionCount

import com.example.fourtitudetask1.lib.data.model.json.response.CategoryQuestionCount

interface QuestionCountFragmentMvpView {
    fun setQuestionCategoryCount(questionCountList: List<CategoryQuestionCount>)
    fun showProgress()
    fun hideProgress()
}