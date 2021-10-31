package com.android.paytabs.repositories

import com.android.paytabs.models.DataResult
import com.android.paytabs.models.BooksResponseModel

interface IBooksRepo {


    suspend fun getListOfBooks() : DataResult<List<BooksResponseModel>>
}