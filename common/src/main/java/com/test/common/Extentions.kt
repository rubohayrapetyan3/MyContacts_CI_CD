package com.test.common

import android.content.Context
import android.content.ContextWrapper
import android.net.Uri
import androidx.activity.ComponentActivity

inline fun Boolean.isTrue(block: () -> Unit): Boolean {
  if (this) {
    block()
  }
  return this
}

fun String.parseToUri(): Uri = Uri.parse(this)

fun Uri.parseToString(): String = this.toString()

inline fun <reified Activity : ComponentActivity> Context.getMyActivity(): Activity? {
  return when (this) {
    is Activity -> this
    else -> {
      var context = this
      while (context is ContextWrapper) {
        context = context.baseContext
        if (context is Activity) {
          return context
        }
      }
      null
    }
  }
}