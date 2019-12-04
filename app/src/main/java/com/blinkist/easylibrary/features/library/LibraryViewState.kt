package com.blinkist.easylibrary.features.library

import com.blinkist.easylibrary.livedata.EmptyLiveDataEvent
import com.blinkist.easylibrary.livedata.SafeMediatorLiveData

data class LibraryViewState(
  val books: List<LibraryItem> = emptyList(),
  val isLoading: Boolean = false,
  val error: LibraryError? = null
)

sealed class LibraryError : EmptyLiveDataEvent() {
  class Network : LibraryError()
  class Unexpected : LibraryError()
}

fun SafeMediatorLiveData<LibraryViewState>.update(
  books: List<LibraryItem> = value.books,
  isLoading: Boolean = value.isLoading,
  error: LibraryError? = value.error
) {
  value = value.copy(books = books, isLoading = isLoading, error = error)
}
