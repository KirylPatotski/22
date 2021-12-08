package com.omisoft.myapplication.mvvm.ui.auth

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omisoft.myapplication.mvvm.model.network.NetworkAuthService
import com.omisoft.myapplication.mvvm.model.network.NetworkAuthServiceImpl
import com.omisoft.myapplication.mvvm.model.storage.LocalStorageModel
import com.omisoft.myapplication.mvvm.model.storage.UserStorage

class AuthViewModel : ViewModel(), LifecycleObserver {
    val isLoginSuccessLiveData = MutableLiveData<Unit>()
    val isLoginFailedLiveData = MutableLiveData<Unit>()
    val showProgressLiveData = MutableLiveData<Unit>()
    val hideProgressLiveData = MutableLiveData<Unit>()
    val titleLiveData = MutableLiveData<String>()

    val emailLiveData = MutableLiveData<String>()
    val passwordLiveData = MutableLiveData<String>()

    private val authModel: NetworkAuthService = NetworkAuthServiceImpl()
    private val storageModel: UserStorage = LocalStorageModel()

    fun onLoginClicked(email: String, password: String) {
//      Сообщаем нашему view, в нашем случае MvvmActivity, что нужно показать прогресс
        showProgressLiveData.postValue(Unit)

//      Здесь мы делаем задержку, чтобы показать прогресс на протяжении 3000 миллисекунд (3 секунды).
//      Это мы делаем штучно, на самом деле штучную задержку делать не надо)
        Handler(Looper.getMainLooper()).postDelayed({
//          titleLiveData.postValue("Main Page")
            val token = authModel.onLoginClicked(email, password)

//      Сообщаем нашему view, в нашем случае MvvmActivity, что нужно спрятать прогресс
            hideProgressLiveData.postValue(Unit)
            if (token != null) {
                storageModel.saveToken(token)
                isLoginSuccessLiveData.postValue(Unit)
            } else {
                isLoginFailedLiveData.postValue(Unit)
            }
        }, 3000)
    }
}