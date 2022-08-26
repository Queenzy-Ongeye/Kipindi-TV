package dev.queen.kipindiauthentication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.queen.kipindiauthentication.databinding.MovieItemBinding
import dev.queen.kipindiauthentication.models.MovieResponse
import dev.queen.kipindiauthentication.models.Movies

class MoviesAdapter( private val movies: List<Movies>) :
    RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val bindingMovie =
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(bindingMovie)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        var moviewHolder = movies.get(position)
        val IMAGE_URL = "https://image.tmdb.org/t/p/w500"

        with(holder.bindingMovie) {
//            movieTitle.text = moviewHolder.title
//            releasedDate.text = moviewHolder.release
            Picasso.get()
                .load(IMAGE_URL + moviewHolder.poster_path)
                .into(moviePoster)
        }

    }

    override fun getItemCount(): Int {
        return movies.size
    }

}

class MovieViewHolder(var bindingMovie: MovieItemBinding) :
    RecyclerView.ViewHolder(bindingMovie.root) {

}


