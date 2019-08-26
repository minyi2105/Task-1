package com.example.fourtitudetask1.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fourtitudetask1.R;
import com.example.fourtitudetask1.activities.MovieDetailActivity;
import com.example.fourtitudetask1.activities.MovieMainActivity;
import com.example.fourtitudetask1.task3.model.Search;
import com.example.fourtitudetask1.util.ValidateUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Search> movieList;
    private Context context;

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_year)
        TextView tvYear;
        @BindView(R.id.tv_imdbID)
        TextView tvImdbId;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.cv_movie)
        CardView cvMovie;
        @BindView(R.id.iv_poster)
        ImageView ivPoster;

        public MovieViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
        }
    }

    /*@Inject*/
    public MovieAdapter(Context context, List<Search> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);

        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        MovieMainActivity movieMainActivity = (MovieMainActivity) context;

        if (movieList != null) {
            Search movie = movieList.get(position);

            holder.tvTitle.setText(movie.getTitle() + " " + position);
            holder.tvYear.setText(movie.getYear());
            holder.tvImdbId.setText(movie.getImdbID());
            holder.tvType.setText(movie.getType());

            Glide
                    .with(context)
                    .load(movie.getPoster())
                    .placeholder(ValidateUtil.getCircularProgressDrawable(context))
                    .error(R.drawable.ic_broken_image)
                    .into(holder.ivPoster);

            holder.cvMovie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, MovieDetailActivity.class);
                    intent.putExtra("imdbId", movieList.get(position).getImdbID());
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (movieList == null) {
            return 0;
        } else {
            return movieList.size();
        }
    }

    public void updateList(List<Search> updatedList) {
        movieList = updatedList;
    }


}