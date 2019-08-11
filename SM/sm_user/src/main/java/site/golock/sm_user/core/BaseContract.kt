package site.golock.sm_user.core

interface BaseContract {

    interface BasePresenter {
        fun start()
    }

    interface BaseView<T> {
        var presenter: T
    }
}