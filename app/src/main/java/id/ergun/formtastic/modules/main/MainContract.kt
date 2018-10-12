package id.ergun.formtastic.modules.main

import id.ergun.formtastic.models.User

class MainContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun showResult(result: String)
        fun showError(error: String)
    }

    interface Presenter {
        fun register(user: User)
    }
}