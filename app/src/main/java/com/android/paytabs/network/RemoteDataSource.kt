package com.android.paytabs.network

import android.text.TextUtils
import android.util.Log
import com.android.paytabs.models.BooksResponseModel
import com.android.paytabs.models.DataResult
import java.net.URL
import org.json.JSONArray


class RemoteDataSource() {


     fun getListOfBooks() : DataResult<List<BooksResponseModel>>{
        val response = QueryUtils.init(URL("https://simple-books-api.glitch.me/books"))
//
        val bookList: MutableList<BooksResponseModel> = mutableListOf()
        try {
            if (response != null && !TextUtils.isEmpty(response)) {
                val arrayItems = JSONArray(response)
                Log.d("TAG", "getListOfBooks: $arrayItems")
                for (i in 0 until arrayItems.length()) {
                    val currentBook = arrayItems.getJSONObject(i)
                    bookList.add(
                        BooksResponseModel(
                            id = currentBook.optInt("id"),
                            name = currentBook.optString("name"),
                            available = currentBook.optBoolean("available",false),
                            type = currentBook.optString("type"),
                        )
                    )
                }
                return DataResult.Success(bookList)

            }
        }catch (e : Exception){
            return DataResult.Error(e)
        }

        return DataResult.Error(java.lang.Exception("Something went wrong"))
    }


}