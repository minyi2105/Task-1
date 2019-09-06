package com.example.fourtitudetask1

class BasePresenter {
    interface Presenter<in T> {
        fun attachView(view: T)
    }
}