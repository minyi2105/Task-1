package com.example.fourtitudetask1.ui.question

import android.os.Build
import android.text.Html
import android.util.Log
import com.example.fourtitudetask1.lib.data.model.json.response.Question
import com.example.fourtitudetask1.lib.data.model.json.response.QuestionResponse
import com.example.fourtitudetask1.lib.data.remote.service.OpenTriviaApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class QuestionFragmentPresenter @Inject constructor() {

    private lateinit var view: QuestionFragmentMvpView

    val client by lazy {
        OpenTriviaApiService.create()
    }

    var disposable: Disposable? = null

    fun attach(view: QuestionFragmentMvpView) {
        this.view = view
    }

    fun getSessionToken() {
        view.showProgress()

        disposable = client.getSessionTokenResponseBody("request")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            Log.v("SESSION TOKEN", "" + result)
                            view.hideProgress()
                            view.setSessionToken(result.token)
                        },
                        { error ->
                            Log.e("ERROR", error.message!!)
                            view.hideProgress()
                        }
                )
    }

    fun getTokenReset(token: String) {
        view.showProgress()

        disposable = client.getTokenResetResponseBody(token, "reset")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            Log.v("RESET SESSION TOKEN", "" + result)
                            view.hideProgress()
                        },
                        { error ->
                            Log.e("ERROR", error.message!!)
                            view.hideProgress()
                        }
                )
    }

    fun loadQuestion(token: String, category: Int?, difficulty: String?, type: String?) {
        view.showProgress()

        disposable = client.getQuestionResponseBody(token, 1, category, difficulty?.toLowerCase(), type?.toLowerCase())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            Log.v("QUESTION", "" + result)
                            view.hideProgress()

                            when (result.responseCode) {
                                0 -> {
                                    val response: Question = result.questions[0]
                                    val incorrectAnswersList: MutableList<String> = mutableListOf()

                                    if (Build.VERSION.SDK_INT >= 24) {
                                        response.question = Html.fromHtml(response.question, Html.FROM_HTML_MODE_LEGACY).toString()
                                        response.correctAnswer = Html.fromHtml(response.correctAnswer, Html.FROM_HTML_MODE_LEGACY).toString()
                                        for (answer in response.incorrectAnswers) {
                                            incorrectAnswersList.add(Html.fromHtml(answer, Html.FROM_HTML_MODE_LEGACY).toString())
                                        }
                                    } else {
                                        response.question = Html.fromHtml(response.question).toString()
                                        response.correctAnswer = Html.fromHtml(response.correctAnswer).toString()

                                        for (answer in response.incorrectAnswers) {
                                            incorrectAnswersList.add(Html.fromHtml(answer).toString())
                                        }
                                    }

                                    if (result.questions[0].type.equals("multiple")) {
                                        view.setMultipleQuestion(result.questions)
                                    } else {
                                        view.setBooleanQuestion(result.questions)
                                    }
                                }
                                3 -> getSessionToken()
                                4 -> getTokenReset(token)
                            }
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