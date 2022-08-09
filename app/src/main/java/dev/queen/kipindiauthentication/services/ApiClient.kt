package dev.queen.kipindiauthentication.services

import retrofit2.Retrofit

object ApiClient {
    var retrofit = Retrofit.Builder().baseUrl("https://api.themoviedb.org/3")
}