package com.example.fourtitudetask1.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fourtitudetask1.R;
import com.example.fourtitudetask1.di.InitApplication;
import com.example.fourtitudetask1.di.component.DaggerActivityComponent;
import com.example.fourtitudetask1.di.module.MvpModule;
import com.example.fourtitudetask1.item.MovieFooterItem;
import com.example.fourtitudetask1.item.MovieItem;
import com.example.fourtitudetask1.model.Search;
import com.example.fourtitudetask1.model.SearchApiResponse;
import com.example.fourtitudetask1.mvp.ShowEmptyView;
import com.example.fourtitudetask1.mvp.movie_list.MovieListContract;
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

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.rv_movie)
    RecyclerView rvMovie;
    @BindView(R.id.btn_search)
    Button btnSearch;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.cv_nothingFound)
    CardView cvNothingFound;
    @BindView(R.id.lv_search)
    LinearLayout lvSearch;

    private static final String TAG = "MovieMainActivity";
    private SearchApiResponse searchApiResponse;

    private GroupAdapter groupAdapter = new GroupAdapter();
    private Section listSection = new Section();
    private List<MovieItem> movieItems = new ArrayList<>();
    private MovieFooterItem footerItem = new MovieFooterItem();

    private LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

    private int totalResults = 0;
    private int page = 1;

    @Inject
    MovieListContract.Presenter movieListPresenter;

    @Inject
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_main);

        ButterKnife.bind(this);

        btnSearch.setOnClickListener(this);

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

        groupAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull Item item, @NonNull View view) {
                Intent intent = new Intent(MovieMainActivity.this, MovieDetailActivity.class);
                intent.putExtra("imdbId", ((MovieItem) item).getMovie().getImdbID());
                MovieMainActivity.this.startActivity(intent);

//                Bundle imdbIdBundle = new Bundle();
//                imdbIdBundle.putString("imdbId", ((MovieItem) item).getMovie().getImdbID());
//                Navigation.findNavController(view).navigate(R.id.movieDetailActivity, imdbIdBundle);
            }
        });
    }

    private void setListeners() {
        rvMovie.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                int totalItemCount = mLayoutManager.getItemCount();
                totalResults = Integer.valueOf(searchApiResponse.getTotalResults());

                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (totalItemCount < totalResults) {
                        movieListPresenter.loadMoreMovieList(++page);
                    }
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
        progressBar.setVisibility(View.VISIBLE);
        footerItem.show();
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
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
        movieItems = new ArrayList<>();

        if (movieArrayList != null) {

            for (Search eachMovie : movieArrayList) {
                movieItems.add(new MovieItem(this, eachMovie));
            }

            listSection.addAll(movieItems);
            listSection.notifyChanged();
        }
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
                //reset the Groupie else items will be appended each time new search is made
                listSection.clear();
                movieItems.clear();
                listSection.notifyChanged();

                movieListPresenter.searchButtonClicked();
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

    @Override
    public void setSearchApiResponse(SearchApiResponse searchApiResponse) {
        this.searchApiResponse = searchApiResponse;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.search) {
            if (lvSearch.getVisibility() == View.VISIBLE) {
                lvSearch.setVisibility(View.GONE);
            } else {
                lvSearch.setVisibility(View.VISIBLE);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
