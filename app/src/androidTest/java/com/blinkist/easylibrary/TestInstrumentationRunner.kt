package com.blinkist.easylibrary

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class TestInstrumentationRunner : AndroidJUnitRunner() {

  override fun newApplication(cl: ClassLoader, className: String, context: Context): Application =
    super.newApplication(cl, TestApplication::class.java.name, context)
}
