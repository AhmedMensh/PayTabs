package com.android.paytabs.ui.books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.paytabs.models.DataResult
import com.android.paytabs.models.BooksResponseModel
import com.android.paytabs.repositories.BooksRepo
import com.android.paytabs.repositories.IBooksRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BooksViewModel(private val iBooksRepo: IBooksRepo = BooksRepo()) : ViewModel() {


    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _books = MutableLiveData<List<BooksResponseModel>>()
    val books: LiveData<List<BooksResponseModel>> get() = _books

    init {
//        getCurrentBooks()
        getListOfBooks()

    }
    private fun getListOfBooks() {

        viewModelScope.launch {
            _isLoading.value = true
            withContext(Dispatchers.IO){
            when (val response = iBooksRepo.getListOfBooks()) {
                is DataResult.Success -> {
                    _isLoading.postValue(false)
                    _books.postValue(response.content)
                }
                is DataResult.Error -> {
                    _isLoading.postValue(false)
                    _error.postValue(response.exception.message)
                }
            }
        }
        }
    }
//    private fun getListOfBooks() {
//
//        viewModelScope.launch {
//            withContext(Dispatchers.IO){
//
//                iBooksRepo.getListOfBooks()
//            }
//        }
//    }

}