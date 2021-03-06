package com.blinkist.easylibrary.di

import com.blinkist.easylibrary.service.BooksService
import com.blinkist.easylibrary.service.LocalDateAdapter
import com.blinkist.easylibrary.service.OffsetDateTimeAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import io.appflate.restmock.RESTMockServer
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module(includes = [OkHttpModule::class, MoshiModule::class])
object RetrofitModule {

  @Provides @Singleton
  fun provideBooksService(okHttpClient: OkHttpClient, moshi: Moshi): BooksService = Retrofit.Builder()
    .baseUrl(RESTMockServer.getUrl())
    .client(okHttpClient)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()
    .create(BooksService::class.java)
}

@Module
object MoshiModule {

  @Provides fun provideMoshi(): Moshi = Moshi.Builder()
    .add(OffsetDateTimeAdapter())
    .add(LocalDateAdapter())
    .build()
}
