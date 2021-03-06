package com.blinkist.easylibrary

import android.app.Application
import com.blinkist.easylibrary.di.ApplicationComponent
import com.blinkist.easylibrary.di.DaggerApplicationComponent
import com.blinkist.easylibrary.di.DaggerComponentProvider
import com.blinkist.easylibrary.ktx.unsyncLazy
import com.blinkist.easylibrary.service.MockServer
import com.jakewharton.threetenabp.AndroidThreeTen
import timber.log.Timber

class EasyLibraryApplication : Application(), DaggerComponentProvider {

  override val component: ApplicationComponent by unsyncLazy {
    DaggerApplicationComponent.factory().create(applicationContext)
  }

  override fun onCreate() {
    super.onCreate()

    initMockServer()
    initTimber()
    initThreeTen()
    initStetho()
  }

  private fun initMockServer() = MockServer.setupAndStart(this)

  private fun initTimber() {
    if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
  }

  private fun initThreeTen() = AndroidThreeTen.init(this)

  private fun initStetho() = component.stethoInitializer.init()
}
