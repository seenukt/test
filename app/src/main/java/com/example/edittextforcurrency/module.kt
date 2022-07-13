package com.example.edittextforcurrency

import com.example.edittextforcurrency.repo.PagingRepo
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    single {
        OkHttpClient.Builder()
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }
    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl("https://api.instantwebtools.net/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(ApiInterFace::class.java) }

    single { CoroutinesManager() }
}

val mainModule = module{

    single { PagingRepo(get())  }
    viewModel { MainViewModel(get(),get(),get()) }

}

