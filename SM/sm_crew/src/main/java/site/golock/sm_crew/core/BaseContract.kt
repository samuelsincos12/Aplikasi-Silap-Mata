package site.golock.sm_crew.core

interface BaseContract {

    interface BasePresenter {
        fun start()
    }

    interface BaseView<T> {
        var presenter: T
    }
}