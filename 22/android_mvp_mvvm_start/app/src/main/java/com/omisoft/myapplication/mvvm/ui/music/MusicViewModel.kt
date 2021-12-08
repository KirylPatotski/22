package com.omisoft.myapplication.mvvm.ui.music

import androidx.lifecycle.ViewModel
import com.omisoft.myapplication.mvvm.model.network.NetworkMusicService
import com.omisoft.myapplication.mvvm.model.network.NetworkMusicServiceImpl
import com.omisoft.myapplication.mvvm.model.storage.LocalStorageModel

class MusicViewModel : ViewModel() {
    private val authModel: NetworkMusicService = NetworkMusicServiceImpl()
    private val storageModel = LocalStorageModel()
}