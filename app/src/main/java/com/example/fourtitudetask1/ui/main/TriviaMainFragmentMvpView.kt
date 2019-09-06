package com.example.fourtitudetask1.ui.main

import com.example.fourtitudetask1.lib.data.model.json.response.TriviaCategory

interface TriviaMainFragmentMvpView {
    fun setCategorySpinner(categoryList: List<TriviaCategory>?)
    fun showProgress()
    fun hideProgress()
}