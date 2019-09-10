package com.example.fourtitudetask1.ui.questionCount

import android.util.Log
import com.example.fourtitudetask1.lib.data.remote.service.OpenTriviaApiService
import io.reactivex.disposables.Disposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class QuestionCountFragmentPresenter {
    private lateinit var view: QuestionCountFragmentMvpView

    val client by lazy {
        OpenTriviaApiService.create()
    }

    var disposable: Disposable? = null

    fun attach(view: QuestionCountFragmentMvpView) {
        this.view = view
    }

    fun onPause() {
        disposable?.dispose()
    }

    fun getQuestionCount() {
        view.showProgress()

        disposable = client.getCategoryResponseBody()
                .flatMapIterable { category -> category.triviaCategoriesList }
                .flatMap { triviaCategory -> client.getQuestionCountByIdResponseBody(triviaCategory.id) }
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            Log.v("QUESTION COUNT", "" + result)
                            view.hideProgress()
                            view.setQuestionCategoryCount(result)
                        },
                        { error ->
                            Log.e("ERROR", error.message!!)
                            view.hideProgress()
                        }
                )
    }
}