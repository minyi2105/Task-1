package com.example.fourtitudetask1.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fourtitudetask1.R;
import com.example.fourtitudetask1.adapters.MovieAdapter;
import com.example.fourtitudetask1.task3.item.MovieFooterItem;
import com.example.fourtitudetask1.task3.item.MovieItem;
import com.example.fourtitudetask1.task3.di.InitApplication;
import com.example.fourtitudetask1.task3.di.component.DaggerActivityComponent;
import com.example.fourtitudetask1.task3.di.module.MvpModule;
import com.example.fourtitudetask1.task3.model.Search;
import com.example.fourtitudetask1.task3.mvp.ShowEmptyView;
import com.example.fourtitudetask1.task3.mvp.movie_list.MovieListContract;
import com.example.fourtitudetask1.util.ValidateUtil;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;
import com.xwray.groupie.OnItemClickListener;
import com.xwray.groupie.Section;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieMainActivity extends AppCompatActivity implements MovieListContract.View, View.OnClickListener, ShowEmptyView {

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

    private GroupAdapter groupAdapter = new GroupAdapter();
    private Section listSection = new Section();
    private List<MovieItem> movieItems = new ArrayList<>();
    private MovieFooterItem footerItem = new MovieFooterItem();

    private Boolean isSearch = true;

//    private MovieListPresenter movieListPresenter;

    @Inject
    MovieListContract.Presenter movieListPresenter;

    @Inject
    Context mContext;


    private LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

    private int page = 1;

    //private MovieMainActivityComponent movieMainActivityComponent;

    /*@Inject
    public Context mContext;

    @Inject
    public List<Search> movieList;*/

    public static void start(Context context) {
        Intent starter = new Intent(context, MovieMainActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_main);

//        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
//        movieMainActivityComponent = DaggerMovieMainActivityComponent.builder()
//                .movieMainActivityContextModule(new MovieMainActivityContextModule(this))
//                .movieListMvpModule(new MovieListMvpModule(this))
//                .applicationComponent(applicationComponent)
//                .build();
//
//        movieMainActivityComponent.injectMovieMainActivity(this);

        ButterKnife.bind(this);

        btnSearch.setOnClickListener(this);
        /*mLayoutManager = new GridLayoutManager(this, 2);
        movieAdapter = new MovieAdapter(this, moviesList);

        rvMovie.setLayoutManager(mLayoutManager);
        rvMovie.setHasFixedSize(true);
        rvMovie.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rvMovie.setAdapter(movieAdapter);*/

        //Initializing presenter
//        movieListPresenter = new MovieListPresenter(this);
//        movieListPresenter.requestDataFromServer();
        rvMovie.setLayoutManager(mLayoutManager);
        DaggerActivityComponent.builder()
                .appComponent(InitApplication.get(this).component())
                .mvpModule(new MvpModule(this))
                .build()
                .inject(this);

        setUpGroupie();
        setListeners();
    }

    private void setUpGroupie() {
        groupAdapter.add(listSection);

        rvMovie.setAdapter(groupAdapter);
        listSection.setFooter(footerItem);

        groupAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull Item item, @NonNull View view) {
                Intent intent = new Intent(MovieMainActivity.this, MovieDetailActivity.class);
                intent.putExtra("imdbId", ((MovieItem) item).getMovie().getImdbID());
                MovieMainActivity.this.startActivity(intent);
            }
        });
    }

    private void setListeners() {

        rvMovie.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    movieListPresenter.loadMoreMovieList(++page);
                    isSearch = false;
                }
            }
        });
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
    public String getSearchInput() {
        return ValidateUtil.getOnlyText(etSearch);
    }

    @Override
    public void showProgress() {
        //progressBar.setVisibility(View.VISIBLE);
        //listSection.setFooter(footerItem);
        footerItem.show();
    }

    @Override
    public void hideProgress() {
        //progressBar.setVisibility(View.GONE);
        //listSection.removeFooter();
        footerItem.hide();
    }

    @Override
    public void hideSoftKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void setDataToRecyclerView(List<Search> movieArrayList) {

        /*moviesList.addAll(movieArrayList);
        movieAdapter.updateList(movieArrayList);
        movieAdapter.notifyDataSetChanged();*/

        listSection.removeAll(movieItems);
        movieItems = new ArrayList<>();

        for (Search eachMovie : movieArrayList) {
            movieItems.add(new MovieItem(this, eachMovie));
        }

        listSection.addAll(movieItems);

        listSection.notifyChanged();
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.e(TAG, throwable.getMessage());
        Toast.makeText(this, getResources().getString(R.string.error_occurred), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_search:
                movieListPresenter.searchButtonClicked();
                isSearch = true;
                break;
        }
    }

    @Override
    public void showEmptyView() {
        rvMovie.setVisibility(View.GONE);
        cvNothingFound.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyView() {
        rvMovie.setVisibility(View.VISIBLE);
        cvNothingFound.setVisibility(View.GONE);
    }
}
