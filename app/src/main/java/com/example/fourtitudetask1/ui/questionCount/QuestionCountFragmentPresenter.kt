package com.example.fourtitudetask1.ui.questionCount

import android.util.Log
import android.util.SparseArray
import androidx.core.util.valueIterator
import com.example.fourtitudetask1.lib.data.model.json.response.CategoryQuestionCount
import com.example.fourtitudetask1.lib.data.remote.service.OpenTriviaApiService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class QuestionCountFragmentPresenter @Inject constructor() {
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

        val map = SparseArray<CategoryQuestionCount>()

        disposable = client.getCategoryResponseBody()
                .flatMapIterable { category ->
                    category.triviaCategoriesList.let {
                        for (triviaCategory in it) {
                            map.put(triviaCategory.id, CategoryQuestionCount(categoryId = triviaCategory.id, categoryName = triviaCategory.name))
                        }
                    }
                    category.triviaCategoriesList
                }
                .flatMap { triviaCategory ->
                    client.getQuestionCountByIdResponseBody(triviaCategory.id).subscribeOn(Schedulers.io())
                }
                .toList()
                .toObservable()
                .flatMap {
                    it.forEach { response ->
                        response.categoryId?.let {
                            val questionCount = map.get(it)
                            questionCount?.let {
                                questionCount.totalEasyQuestionCount = response.categoryQuestionCount.totalEasyQuestionCount
                                questionCount.totalMediumQuestionCount = response.categoryQuestionCount.totalMediumQuestionCount
                                questionCount.totalHardQuestionCount = response.categoryQuestionCount.totalHardQuestionCount
                                questionCount.totalQuestionCount = response.categoryQuestionCount.totalQuestionCount
                            }
                        }

                    }
                    Observable.just(map)
                }.flatMap {
                    Observable.just(
                            it.valueIterator().asSequence().toList()
                    )
                }.subscribeOn(Schedulers.io())
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