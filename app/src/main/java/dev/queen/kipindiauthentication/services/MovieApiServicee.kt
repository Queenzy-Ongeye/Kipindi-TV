package dev.queen.kipindiauthentication.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieApiServicee {
    var retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> buildMovieApiServicee(apiInterface: Class<T>) : T {
        return  retrofit.create(apiInterface)
    }
}