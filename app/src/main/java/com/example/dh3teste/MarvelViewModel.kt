package com.example.dh3teste

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.example.dh3teste.Model.Result

class MarvelViewModel : ViewModel() {
    var marvelPagedList: LiveData<PagedList<Result>>? = null
    private var marvelLiveDataSource: LiveData<PageKeyedDataSource<Int, Result>>? = null

    init {
        comicsData()
    }

    private fun comicsData() {
        val marvelSourceFactory = MarvelDataSourceFactory()
        marvelLiveDataSource = marvelSourceFactory.getComicsLiveDataSource()

        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(20).build()

        marvelPagedList = LivePagedListBuilder(marvelSourceFactory, pagedListConfig).build()
    }
}