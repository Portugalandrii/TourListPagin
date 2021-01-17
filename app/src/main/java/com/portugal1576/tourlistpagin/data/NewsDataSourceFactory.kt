package com.portugal1576.tourlistpagin.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import io.reactivex.disposables.CompositeDisposable

class NewsDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val networkService: TourService)
    : DataSource.Factory<Int, Entity>() {

    val newsDataSourceLiveData = MutableLiveData<NewsDataSource>()

    override fun create(): DataSource<Int, Entity> {
        val newsDataSource = NewsDataSource(networkService, compositeDisposable)
        newsDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }
}