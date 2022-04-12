package com.asoul.asasfans_concept.utils

import android.widget.Toast
import com.asoul.asasfans_concept.AsApplication

fun String.showShortToast() {
    Toast.makeText(AsApplication.context, this, Toast.LENGTH_SHORT).show()
}

fun String.showLongToast() {
    Toast.makeText(AsApplication.context, this, Toast.LENGTH_LONG).show()
}