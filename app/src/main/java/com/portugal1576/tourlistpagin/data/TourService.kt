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
//http://api.tourstart.org/api/list.tour/simpleAll
interface TourService {

    @GET("list.tour/simpleAll")  // запрос методом GET
    fun getTour() : Single<Response>  //функция выводит объект класса Call,
    // который содержит результат работы сервера

    companion object {   // сингитон - статический объект одиночка, для обращения к которому не требуется создание класса

        var BASE_URL = "http://api.tourstart.org/api/"

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
