package id.ergun.formtastic.utils

import android.os.Build
import android.text.Html
import android.text.Spanned

@Suppress("DEPRECATION")
fun String.fromHtml(): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(this)
    }
}

fun String.unOrderedListFormat(): String {
    return "&#8226;    " + this + "<br/>"
}