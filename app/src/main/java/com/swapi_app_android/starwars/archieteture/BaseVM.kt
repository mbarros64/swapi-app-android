package com.swapi_app_android.starwars.archieteture

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.swapi_app_android.starwars.extensions.hide
import io.reactivex.disposables.CompositeDisposable

abstract class BaseVM : ViewModel() {

    val disposable = CompositeDisposable()

    protected val _loading: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val loading: LiveData<Boolean> by lazy { _loading }

    val _error: MutableLiveData<Throwable> by lazy { MutableLiveData<Throwable>() }
    val error: LiveData<Throwable> by lazy { _error }

    protected fun handleError(err: Throwable) {
        _loading.hide()
        _error.postValue(err)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

    fun errorHandled() {
        _error.value = null
    }

}