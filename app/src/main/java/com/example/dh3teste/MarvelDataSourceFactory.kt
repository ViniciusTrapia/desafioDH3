package com.example.dh3teste

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.dh3teste.Model.Result

class MarvelDataSourceFactory(): DataSource.Factory<Int, Result>(){
    private val marvelLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, Result>>()

    override fun create(): DataSource<Int, Result> {
        val marvelDataSource = ComicsPageKeyedDataSource()

        marvelLiveDataSource.postValue(marvelDataSource)
        return marvelDataSource
    }

    fun getComicsLiveDataSource(): MutableLiveData<PageKeyedDataSource<Int, Result>> {
        return marvelLiveDataSource
    }
}