package com.example.fourtitudetask1.ui.questionCount

import com.example.fourtitudetask1.lib.data.remote.service.OpenTriviaApiService
import io.reactivex.disposables.Disposable

class QuestionCountFragmentPresenter {
    private lateinit var view: QuestionCountFragmentMvpView
//    private val openTriviaApiService:OpenTriviaApiService

    val client by lazy {
        OpenTriviaApiService.create()
    }

    var disposable: Disposable? = null

    fun attach(view: QuestionCountFragmentMvpView) {
        this.view = view
    }

    fun loadQuestionCount() {
//        disposable = client.getCategoryResponseBody()
    }

    fun onPause() {

    }

//    fun getRestaurants(userId: Int): Single<List<QuestionCountVM>> {
////        return client.getCategoryResponseBody().flatMap { Function  }
////        user -> client.getQuestionCountByIdResponseBody(user.id)
//    }

//    fun getCategory(): Single<List<Category>> {
//        return client.getCategoryResponseBody().flatMap(category ->{
//            return client.getQuestionCountByIdResponseBody(category.)
//        })
//    }
}