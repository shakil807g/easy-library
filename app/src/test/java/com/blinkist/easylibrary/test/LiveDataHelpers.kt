package com.blinkist.easylibrary.test

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

// This should hopefully go away once https://issuetracker.google.com/issues/130709056 is resolved
fun <T> LiveData<T>.getOrAwaitValue(): T {
  var data: T? = null
  val latch = CountDownLatch(1)
  val observer = object : Observer<T> {
    override fun onChanged(o: T?) {
      data = o
      latch.countDown()
      this@getOrAwaitValue.removeObserver(this)
    }
  }
  observeForever(observer)

  if (!latch.await(2, TimeUnit.SECONDS)) {
    this.removeObserver(observer)
    throw TimeoutException("LiveData value was never set.")
  }

  @Suppress("UNCHECKED_CAST")
  return data as T
}
