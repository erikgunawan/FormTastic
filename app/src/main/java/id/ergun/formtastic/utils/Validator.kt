package id.ergun.formtastic.utils

import android.util.Patterns

fun CharSequence.isValidLength(min: Int, max: Int): Boolean
        = this.toString().length in min..max

fun String.isValidEmail(): Boolean
        = Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isValidName(): Boolean
        = this.matches( "[a-zA-Z]*".toRegex())

fun String.isAlfanumerik(): Boolean
        = this.matches( "[a-zA-Z0-9]*".toRegex())
