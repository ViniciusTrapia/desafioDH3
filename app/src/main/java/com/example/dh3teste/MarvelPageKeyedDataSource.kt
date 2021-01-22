package com.example.dh3teste

import androidx.paging.PageKeyedDataSource
import com.example.dh3teste.Model.Comics
import com.example.dh3teste.Model.Result
import com.example.dh3teste.api.ResponseAPI
import com.example.dh3teste.repository.MarvelRepository
import com.example.dh3teste.utils.Constants.marvelAPI.FIRST_PAGE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ComicsPageKeyedDataSource(): PageKeyedDataSource<Int, Result>() {
    private val repository by lazy {
        MarvelRepository()
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Result>
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            when(val response = repository.getComics(FIRST_PAGE)) {
                is ResponseAPI.Success -> {
                    val data = response.data as Comics
                    data.data?.results?.forEach {result ->
                        result.thumbnail?.path = result.thumbnail?.path?.getFullPath()
                        result.images?.forEach {
                            it.path = it.path?.getFullPath()
                        }
                        if(result.description == null) {
                            result.description = "Description not found"
                        }
                    }
                    data.data?.results?.let { callback.onResult(it, null, FIRST_PAGE + 1) }
                }
                is ResponseAPI.Error -> {
                    callback.onResult(mutableListOf(), null, FIRST_PAGE + 1)
                }
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        val page = params.key
        CoroutineScope(Dispatchers.IO).launch {
            when(val response = repository.getComics(page)) {
                is ResponseAPI.Success -> {
                    val data = response.data as Comics
                    data.data?.results?.forEach {result ->
                        result.images?.forEach {
                            it.path = it.path?.getFullPath()
                        }
                        if(result.description == null) {
                            result.description = "Description not found"
                        }
                        result.thumbnail?.path = result.thumbnail?.path?.getFullPath()
                    }
                    data.data?.results?.let { callback.onResult(it,  page + 1) }
                }
                is ResponseAPI.Error -> {
                    callback.onResult(mutableListOf(), page + 1)
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        val page = params.key
        CoroutineScope(Dispatchers.IO).launch {
            when(val response = repository.getComics(page)) {
                is ResponseAPI.Success -> {
                    val data = response.data as Comics
                    data.data?.results?.forEach {result ->
                        result.images?.forEach {
                            it.path = it.path?.getFullPath()
                        }
                        if(result.description == null) {
                            result.description = "Description not found"
                        }
                        result.thumbnail?.path = result.thumbnail?.path?.getFullPath()
                    }
                    data.data?.results?.let { callback.onResult(it,  page - 1) }
                }
                is ResponseAPI.Error -> {
                    callback.onResult(mutableListOf(), page - 1)
                }
            }
        }
    }
}