package com.android.paytabs.repositories

import com.android.paytabs.models.DataResult
import com.android.paytabs.models.BooksResponseModel
import com.android.paytabs.network.RemoteDataSource

class BooksRepo(private val remoteDataSource: RemoteDataSource = RemoteDataSource()) : IBooksRepo {


    override suspend fun getListOfBooks() : DataResult<List<BooksResponseModel>>{
       return remoteDataSource.getListOfBooks()
    }
}