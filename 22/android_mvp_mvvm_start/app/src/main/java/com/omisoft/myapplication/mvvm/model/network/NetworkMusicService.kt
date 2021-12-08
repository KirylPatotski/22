package com.omisoft.myapplication.mvvm.model.network

interface NetworkMusicService {
    fun getFavouriteMusic(): List<Any>
    fun updateFavouriteMusic(data: Any)
}