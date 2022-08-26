package dev.queen.kipindiauthentication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import dev.queen.kipindiauthentication.R
import dev.queen.kipindiauthentication.adapters.MoviesAdapter
import dev.queen.kipindiauthentication.databinding.FragmentHomeBinding
import dev.queen.kipindiauthentication.models.MovieResponse
import dev.queen.kipindiauthentication.models.Movies
import dev.queen.kipindiauthentication.services.MovieApiInterface
import dev.queen.kipindiauthentication.services.MovieApiServicee
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.getRoot();
        getMovies()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

//    companion object {
//        fun newInstance() : HomeFragment{
//            return HomeFragment()
//        }
//    }

    fun getMovies(){
        var movieClient = MovieApiServicee.buildMovieApiServicee(MovieApiInterface::class.java)
        var request = movieClient.getMovieList()

        request.enqueue(object: Callback<List<Movies>> {
            override fun onResponse(call: Call<List<Movies>>, response: Response<List<Movies>>) {
                var movies = response.body()
                if (response.isSuccessful){
                    var adapter = movies?.let { MoviesAdapter(it) }
                    binding.rcvMovieList.adapter = adapter
                    binding.rcvMovieList.layoutManager = LinearLayoutManager(context)

                }
            }

            override fun onFailure(call: Call<List<Movies>>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }


}