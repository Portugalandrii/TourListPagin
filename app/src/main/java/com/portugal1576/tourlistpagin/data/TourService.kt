package com.portugal1576.tourlistpagin.data

import io.reactivex.Single
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

//анотация меняет поведение метода не меняя код

interface TourService {

    @GET("***************")  // запрос методом GET
    fun getTour() : Single<Response>


    companion object {
        var BASE_URL = "*********"

        fun create(): TourService {    //эта функция реализует интерфейс BankService с помощью класса Retrofit

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(TourService::class.java)

        }
    }
}
