package com.example.fourtitudetask1.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.fourtitudetask1.R;
import com.example.fourtitudetask1.task3.di.InitApplication;
import com.example.fourtitudetask1.task3.di.component.DaggerActivityComponent;
import com.example.fourtitudetask1.task3.di.module.MvpModule;
import com.example.fourtitudetask1.task3.model.MovieApiResponse;
import com.example.fourtitudetask1.task3.mvp.movie_detail.MovieDetailsContract;
import com.example.fourtitudetask1.util.ValidateUtil;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailsContract.View {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_year)
    TextView tvYear;
    @BindView(R.id.tv_rated)
    TextView tvRated;
    @BindView(R.id.tv_released)
    TextView tvReleased;
    @BindView(R.id.tv_runtime)
    TextView tvRuntime;
    @BindView(R.id.tv_genre)
    TextView tvGenre;
    @BindView(R.id.tv_director)
    TextView tvDirector;
    @BindView(R.id.tv_writer)
    TextView tvWriter;
    @BindView(R.id.tv_actors)
    TextView tvActors;
    @BindView(R.id.tv_plot)
    TextView tvPlot;
    @BindView(R.id.tv_language)
    TextView tvLanguage;
    @BindView(R.id.tv_country)
    TextView tvCountry;
    @BindView(R.id.tv_awards)
    TextView tvAwards;
    @BindView(R.id.iv_poster)
    ImageView ivPoster;
    @BindView(R.id.tv_metascore)
    TextView tvMetascore;
    @BindView(R.id.tv_imdbRating)
    TextView tvImdbRating;
    @BindView(R.id.tv_imdbVotes)
    TextView tvImdbVotes;
    @BindView(R.id.tv_imdbId)
    TextView tvImdbID;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_dvd)
    TextView tvDvd;
    @BindView(R.id.tv_boxOffice)
    TextView tvBoxOffice;
    @BindView(R.id.tv_production)
    TextView tvProduction;
    @BindView(R.id.tv_website)
    TextView tvWebsite;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.rl_movieDetails)
    RelativeLayout rlMovieDetails;

    private String imdbId;

    @Inject
    MovieDetailsContract.Presenter movieDetailsPresenter;

    @Inject
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ButterKnife.bind(this);

        DaggerActivityComponent.builder()
                .appComponent(InitApplication.get(this).component())
                .mvpModule(new MvpModule(this))
                .build()
                .inject(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent().hasExtra("imdbId")) {

            imdbId = getIntent().getStringExtra("imdbId");
            movieDetailsPresenter.requestMovieData(imdbId);
        }

        Glide
                .with(MovieDetailActivity.this)
                .load(ValidateUtil.getCircularProgressDrawable(MovieDetailActivity.this))
                .placeholder(ValidateUtil.getCircularProgressDrawable(MovieDetailActivity.this))
                .error(R.drawable.ic_broken_image)
                .into(ivPoster);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setDataToViews(MovieApiResponse movieApiResponse) {
        if (movieApiResponse != null) {
            getSupportActionBar().setTitle(movieApiResponse.getTitle());

            tvTitle.setText(movieApiResponse.getTitle());
            tvYear.setText(movieApiResponse.getYear());
            tvRated.setText(movieApiResponse.getRated());
            tvReleased.setText(movieApiResponse.getReleased());
            tvRuntime.setText(movieApiResponse.getRuntime());
            tvGenre.setText(movieApiResponse.getGenre());
            tvDirector.setText(movieApiResponse.getDirector());
            tvWriter.setText(movieApiResponse.getWriter());
            tvActors.setText(movieApiResponse.getActors());
            tvPlot.setText(movieApiResponse.getPlot());
            tvLanguage.setText(movieApiResponse.getLanguage());
            tvCountry.setText(movieApiResponse.getCountry());
            tvAwards.setText(movieApiResponse.getAwards());
            tvMetascore.setText(movieApiResponse.getMetascore());
            tvImdbRating.setText(movieApiResponse.getImdbRating());
            tvImdbVotes.setText(movieApiResponse.getImdbVotes());
            tvImdbID.setText(movieApiResponse.getImdbID());
            tvType.setText(movieApiResponse.getType());
            tvDvd.setText(movieApiResponse.getDVD());
            tvBoxOffice.setText(movieApiResponse.getBoxOffice());
            tvProduction.setText(movieApiResponse.getProduction());
            tvWebsite.setText(movieApiResponse.getWebsite());

            Glide
                    .with(MovieDetailActivity.this)
                    .load(movieApiResponse.getPoster())
                    .placeholder(ValidateUtil.getCircularProgressDrawable(MovieDetailActivity.this))
                    .error(R.drawable.ic_broken_image)
                    .into(ivPoster);
        }
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Snackbar.make(rlMovieDetails, getResources().getString(R.string.error_occurred), Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        movieDetailsPresenter.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
