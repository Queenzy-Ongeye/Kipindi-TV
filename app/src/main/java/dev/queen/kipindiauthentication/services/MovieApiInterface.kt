package dev.queen.kipindiauthentication.services

import dev.queen.kipindiauthentication.models.MovieResponse
import dev.queen.kipindiauthentication.models.Movies
import retrofit2.Call
import retrofit2.http.GET

interface MovieApiInterface {
    @GET("/movie/popular?api_key=d04f9f433b6bc8aaf962beee8a8e2c00")
    fun getMovieList(): Call<List<Movies>>
}