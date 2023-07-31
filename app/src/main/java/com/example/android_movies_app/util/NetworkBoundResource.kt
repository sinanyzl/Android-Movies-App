package com.example.android_movies_app.util

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.*
import com.example.android_movies_app.api.responses.ApiEmptyResponse
import com.example.android_movies_app.api.responses.ApiErrorResponse
import com.example.android_movies_app.api.responses.ApiResponse
import com.example.android_movies_app.api.responses.ApiSuccessResponse

abstract class NetworkBoundResource<CacheObject, RequestObject> @MainThread constructor(private val appExecutors: AppExecutors) {

    private val result = MediatorLiveData<Resource<CacheObject>>()

    init {
        result.value = Resource.Loading(null)
        @Suppress("LeakingThis")
        val dbSource = loadFromDb()
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)){
                fetchFromNetwork(dbSource)
            }else{
                result.addSource(dbSource){currentCachedData -> setValue(Resource.Success(currentCachedData))}
            }

        }

    }

    @MainThread
    private fun setValue(newValue: Resource<CacheObject>){
        if (result.value != newValue){
            result.value = newValue
        }

    }

    private fun fetchFromNetwork(dbSource: LiveData<CacheObject>) {

        val apiResponse = createCall()

        result.addSource(dbSource) { currentCachedData ->  setValue(Resource.Loading(currentCachedData)) }
        result.addSource(apiResponse) { response -> result.removeSource(apiResponse)

            result.removeSource(dbSource)

            when (response) {
                is ApiSuccessResponse -> {

                    appExecutors.diskIO.execute {

                        saveCallResult(processResponse(response))

                        appExecutors.mainThreadExecutor.execute {

                            result.addSource(loadFromDb()) { newCachedObject ->

                                setValue(Resource.Success(newCachedObject))
                            }
                        }
                    }
                }
                is ApiEmptyResponse -> {
                    appExecutors.mainThreadExecutor.execute {

                        result.addSource(loadFromDb()) { oldCachedObject ->
                            setValue(Resource.Success(oldCachedObject))
                        }
                    }
                }
                is ApiErrorResponse -> {

                    result.addSource(dbSource) { oldCachedObject ->
                        setValue(Resource.Error(response.errorMessage, oldCachedObject))
                    }
                }
            }
        }
    }

    fun asLiveData() = result as LiveData<Resource<CacheObject>>

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<RequestObject>) = response.body

    @WorkerThread
    protected abstract fun saveCallResult(item: RequestObject?)

    @MainThread
    protected abstract fun shouldFetch(data: CacheObject?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): LiveData<CacheObject>

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<RequestObject>>

}