package com.mgm.kiliaro.generals.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar

/**
 * Created by Majid-Golmoradi on 3/16/2022.
 * Email: golmoradi.majid@gmail.com
 */
fun View.snackToast(text: String) {
    val snack = Snackbar.make(this, text, Snackbar.LENGTH_SHORT)
    snack.show()
}