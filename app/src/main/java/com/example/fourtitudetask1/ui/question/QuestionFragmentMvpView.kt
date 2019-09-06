package com.example.fourtitudetask1.ui.question

import com.example.fourtitudetask1.lib.data.model.json.response.Question

interface QuestionFragmentMvpView {
    fun setMultipleQuestion(question: List<Question>)
    fun setBooleanQuestion(question: List<Question>)
    fun showProgress()
    fun hideProgress()
    fun setSessionToken(token: String)
    fun onTokenReset()

}