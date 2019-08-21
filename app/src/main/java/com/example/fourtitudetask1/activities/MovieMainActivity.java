package com.example.fourtitudetask1.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fourtitudetask1.R;
import com.example.fourtitudetask1.adapters.MovieAdapter;
import com.example.fourtitudetask1.task3.model.Search;
import com.example.fourtitudetask1.task3.mvp.movie_list.MovieListContract;
import com.example.fourtitudetask1.task3.mvp.movie_list.MovieListPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieMainActivity extends AppCompatActivity implements MovieListContract.View, View.OnClickListener {

    @BindView(R.id.tv_quote)
    TextView tvQuote;
    @BindView(R.id.btn_test)
    Button btnTest;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.rv_movie)
    RecyclerView rvMovie;
    //    @BindView(R.id.fab_search)
//    FloatingActionButton fabSearch;
    @BindView(R.id.cl_movie)
    ConstraintLayout clMovie;
    @BindView(R.id.btn_search)
    Button btnSearch;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.cv_nothingFound)
    CardView cvNothingFound;


    private static final String TAG = "MovieMainActivity";
    private List<Search> moviesList;
    private MovieAdapter movieAdapter;

    private MovieListPresenter movieListPresenter;

    public static void start(Context context) {
        Intent starter = new Intent(context, MovieMainActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_main);

        ButterKnife.bind(this);



        moviesList = new ArrayList<>();
        movieAdapter = new MovieAdapter(this, rvMovie, cvNothingFound, moviesList);

        rvMovie.setHasFixedSize(true);
        rvMovie.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.VERTICAL ,false));
        rvMovie.setAdapter(movieAdapter);

        //Initializing presenter
        movieListPresenter = new MovieListPresenter(this);
        movieListPresenter.requestDataFromServer();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        movieListPresenter.onDestroy();
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
    public void setDataToRecyclerView(List<Search> movieArrayList) {
        moviesList.addAll(movieArrayList);
        movieAdapter.updateList(movieArrayList);
        movieAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.e(TAG, throwable.getMessage());
        Toast.makeText(this, "An error has occurred, please try again later.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_search:

        }
    }
}
