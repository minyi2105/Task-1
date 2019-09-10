package com.example.fourtitudetask1.ui.main

import android.util.Log
import com.example.fourtitudetask1.lib.data.model.json.response.TriviaCategory
import com.example.fourtitudetask1.lib.data.remote.service.OpenTriviaApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TriviaMainFragmentPresenter {

    @Inject
    fun TriviaMainFragmentPresenter() {

    }

    private lateinit var view: TriviaMainFragmentMvpView

    val client by lazy {
        OpenTriviaApiService.create()
    }

    var disposable: Disposable? = null

    fun attach(view: TriviaMainFragmentMvpView) {
        this.view = view
    }

    fun loadCategory() {
        view.showProgress()

        disposable = client.getCategoryResponseBody()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            Log.v("CATEGORIES", "" + result)
                            view.hideProgress()

                            val finalCategoryList: MutableList<TriviaCategory> = mutableListOf()
                            finalCategoryList.add(TriviaCategory(99, "Default"))
                            finalCategoryList.addAll(result.triviaCategoriesList)

                            view.setCategorySpinner(finalCategoryList)
                        },
                        { error ->
                            Log.e("ERROR", error.message!!)
                            view.hideProgress()
                        }
                )
    }

    fun onPause() {
        disposable?.dispose()
    }
}