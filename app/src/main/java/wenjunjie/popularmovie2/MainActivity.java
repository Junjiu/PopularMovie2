package wenjunjie.popularmovie2;


import android.content.Intent;
import android.support.v4.content.AsyncTaskLoader;



import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.app.LoaderManager;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.zip.Inflater;

import wenjunjie.popularmovie2.Utility.Movie;
import wenjunjie.popularmovie2.Utility.NetworkUtility;
import wenjunjie.popularmovie2.Utility.UrlBuilder;


public class MainActivity extends AppCompatActivity implements LoaderCallbacks<Movie[]>, MovieAdapter.MovieAdapaterOnClicker{

    public static String MOVIE_EXTRA = "moive_extra_id";
    public static int MOVIE_SEARCH_LOADER = 1;
    MovieAdapter movieAdapter;
    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieAdapter = new MovieAdapter(this);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_poster);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(movieAdapter);
        loadMovieData();
    }

    @NonNull
    @Override
    public Loader<Movie[]> onCreateLoader(int id, @Nullable Bundle args) {
        return new AsyncTaskLoader<Movie[]>(this) {
            @Override
            protected void onStartLoading() {
                forceLoad();
                super.onStartLoading();
            }

            @Nullable
            @Override
            public Movie[] loadInBackground() {
                return NetworkUtility.FetchMovie(UrlBuilder.getMoviesDataURL());
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Movie[]> loader, Movie[] data) {
        Log.d("test","onLoadFinished");
        movieAdapter.getMovieData(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Movie[]> loader) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menuSetting:
                Toast.makeText(this, "call settingAcitivity", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_sort_popular:
                UrlBuilder.sortByPopular();
                loadMovieData();
                movieAdapter.notifyDataSetChanged();
                break;
            case R.id.menu_sort_rate:
                UrlBuilder.sortByRate();
                loadMovieData();
                movieAdapter.notifyDataSetChanged();
                break;
        }
        return true;
    }

    public void loadMovieData(){
        Bundle bundleForLoader = null;
        LoaderManager loaderManager = getSupportLoaderManager();
        Loader<Movie[]> movieLoader = loaderManager.getLoader(MOVIE_SEARCH_LOADER);
        if(movieLoader == null){
            loaderManager.initLoader(MOVIE_SEARCH_LOADER, null, this);
        }else{
            loaderManager.restartLoader(MOVIE_SEARCH_LOADER, null, this);
        }
    }
    @Override
    public void itemGetClick(Movie movie) {
        Toast.makeText(this, "be click", Toast.LENGTH_LONG).show();
        Intent startDetailActivityIntent = new Intent(this, DetailActivity.class);
        Bundle bundle = new Bundle();
        startDetailActivityIntent.putExtra(MOVIE_EXTRA, movie);
        startActivity(startDetailActivityIntent);
    }
}
