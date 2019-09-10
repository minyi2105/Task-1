package com.example.fourtitudetask1.ui.questionCount

import com.example.fourtitudetask1.lib.data.model.json.response.QuestionCount

interface QuestionCountFragmentMvpView {
    fun setQuestionCategoryCount(questionCountList: List<QuestionCount>)
    fun showProgress()
    fun hideProgress()
}