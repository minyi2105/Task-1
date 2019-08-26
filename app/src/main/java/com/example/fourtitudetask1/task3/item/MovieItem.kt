package com.example.fourtitudetask1.task3.item

import android.content.Context
import com.bumptech.glide.Glide
import com.example.fourtitudetask1.R
import com.example.fourtitudetask1.databinding.ItemMovieBinding
import com.example.fourtitudetask1.task3.model.Search
import com.example.fourtitudetask1.util.ValidateUtil
import com.xwray.groupie.databinding.BindableItem


class MovieItem(private val context: Context, val movie: Search) : BindableItem<ItemMovieBinding>() {

    override fun getLayout(): Int {
        return R.layout.item_movie
    }

    override fun bind(viewBinding: ItemMovieBinding, position: Int) {
        viewBinding.tvTitle.text = "${movie.title} $position"
        viewBinding.tvYear.text = movie.year
        viewBinding.tvImdbID.text = movie.imdbID
        viewBinding.tvType.text = movie.type

        Glide
                .with(context)
                .load(movie.poster)
                .placeholder(ValidateUtil.getCircularProgressDrawable(context))
                .error(R.drawable.ic_broken_image)
                .into(viewBinding.ivPoster)

        /*viewBinding.cvMovie.setOnClickListener {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra("imdbId", movie.imdbID)
            context.startActivity(intent)
        }*/
    }

}