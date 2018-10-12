package id.ergun.formtastic.modules.main

import android.os.Handler
import id.ergun.formtastic.models.User
import id.ergun.formtastic.utils.*

class MainPresenter(private val view: MainContract.View): MainContract.Presenter {

    override fun register(user: User) {
        view.showLoading()
        Handler().postDelayed({
            view.showResult("Sukses")
            view.hideLoading()
        }, 2000)
    }

    fun validate(user: User) {

        var isError = false
        var errorMessage = "Harap lengkapi data berikut: <br/><font color=red>"

        if (user.namaDepan.isEmpty()) {
            isError = true
            errorMessage += "Nama depan tidak boleh kosong".unOrderedListFormat()
        }
        else {
            if (!user.namaDepan.isValidLength(2,10)) {
                isError = true
                errorMessage += "Nama depan berupa alfabet 2-10 karakter".unOrderedListFormat()
            }
            else {
                if (!user.namaDepan.isValidName()) {
                    isError = true
                    errorMessage += "Nama depan berupa alfabet".unOrderedListFormat()
                }
            }
        }

        if (user.namaBelakang.isEmpty()) {
            isError = true
            errorMessage += "Nama belakang tidak boleh kosong".unOrderedListFormat()
        }
        else {
            if (!user.namaBelakang.isValidLength(2,20)) {
                isError = true
                errorMessage += "Nama belakang berupa alfabet 2-20 karakter".unOrderedListFormat()
            }
            else {
                if (!user.namaBelakang.isValidName()) {
                    isError = true
                    errorMessage += "Nama belakang berupa alfabet".unOrderedListFormat()
                }
            }
        }

        if (user.email.isEmpty()) {
            isError = true
            errorMessage += "Email tidak boleh kosong".unOrderedListFormat()
        }
        else {
            if (!user.email.isValidEmail()) {
                errorMessage += "Email tidak valid".unOrderedListFormat()
                isError = true
            }
        }

        if (user.password.isEmpty()) {
            isError = true
            errorMessage += "Password tidak boleh kosong".unOrderedListFormat()
        }
        else {
            if (!user.password.isValidLength(6,10)) {
                errorMessage += "Password berupa alfanumerik 6-10 karakter".unOrderedListFormat()
                isError = true
            }
            else {
                if (!user.password.isAlfanumerik()) {
                    isError = true
                    errorMessage += "Password berupa alfanumerik".unOrderedListFormat()
                }
            }
        }

        if (user.cpassword.isEmpty()) {
            isError = true
            errorMessage += "Konfirmasi password tidak boleh kosong".unOrderedListFormat()
        }
        else {
            if (!user.cpassword.equals(user.password)) {
                errorMessage += "Konfirmasi password tidak sesuai dengan password".unOrderedListFormat()
                isError = true
            }
        }

        errorMessage += "</font>"

        if (!isError) {
            register(user)
        }
        else {
            view.showError(errorMessage)
        }
    }

}