package com.portugal1576.tourlistpagin.data

import com.portugal1576.tourlistpagin.data.State.DONE
import com.portugal1576.tourlistpagin.data.State.ERROR

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

class NewsDataSource(
    private val networkService: TourService,
    private val compositeDisposable: CompositeDisposable)
    : PageKeyedDataSource<Int, Entity>() {

    var state: MutableLiveData<State> = MutableLiveData()
    private var retryCompletable: Completable? = null


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Entity>) {
        updateState(State.LOADING)
        compositeDisposable.add(
            networkService.getTour()
                .subscribe(
                    { response ->
                        updateState(DONE)
                        callback.onResult(response.entities,
                            null,
                            2
                        )
                    },
                    {
                        updateState(ERROR)
                        setRetry(Action { loadInitial(params, callback) })
                    }
                )
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Entity>) {
        updateState(State.LOADING)
        compositeDisposable.add(
            networkService.getTour()
                .subscribe(
                    { response ->
                        updateState(DONE)
                        callback.onResult(response.entities,
                            params.key + 1
                        )
                    },
                    {
                        updateState(ERROR)
                        setRetry(Action { loadAfter(params, callback) })
                    }
                )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Entity>) {
    }

    private fun updateState(state: State) {
        this.state.postValue(state)
    }

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(retryCompletable!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
        }
    }

    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }

}