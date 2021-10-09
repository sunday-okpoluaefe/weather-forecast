package com.sunday.weatherapplication.di

import com.sunday.weatherapplication.BuildConfig
import com.sunday.weatherapplication.data.remote.ForecastApiClient
import com.sunday.weatherapplication.service.ApiClient
import com.sunday.weatherapplication.service.ForecastService
import com.sunday.weatherapplication.ui.factory.MainViewModelFactory
import com.sunday.weatherapplication.util.WEATHER_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.annotation.Nonnull
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract  class AppModule {

    companion object {

        @Singleton
        @Provides
        fun requestInterceptor() = Interceptor {
            val url = it.request()
                .url()
                .newBuilder()
                .addQueryParameter("appid", BuildConfig.WEATHER_API_KEY)
                .build()

            val request = it.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor it.proceed(request)
        }

        @Singleton
        @Provides
        fun okHttpClient(interceptor: Interceptor): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        @Singleton
        @Provides
        fun provideRetrofit(@Nonnull okhttpClient: OkHttpClient) : Retrofit {
            return Retrofit.Builder()
                .baseUrl(WEATHER_BASE_URL)
                .client(okhttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        }

        @Singleton
        @Provides
        fun provideForecastService(retrofit: Retrofit) : ForecastService  = retrofit
            .create(ForecastService::class.java)

        @Singleton
        @Provides
        fun provideForecastApiClient(service: ForecastService) : ApiClient = ForecastApiClient(service = service)

        @Singleton
        @Provides
        fun provideMainViewModelFactory(client: ApiClient) = MainViewModelFactory(client = client)
    }
}