package wenjunjie.popularmovie2;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import wenjunjie.popularmovie2.Utility.Movie;
import wenjunjie.popularmovie2.Utility.UrlBuilder;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    public  int screen_width;
    public  int screen_height;
    public Movie[] movies;
    public Context mContext;
    public MovieAdapaterOnClicker movieAdapaterOnClicker;
    public interface MovieAdapaterOnClicker{
        public void itemGetClick(Movie movie);
    }
    public MovieAdapter(Context context){
        mContext = context;
        movieAdapaterOnClicker = (MovieAdapaterOnClicker)context;
    }
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.movie_item;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layoutIdForListItem, parent, false);
        screen_width = Resources.getSystem().getDisplayMetrics().widthPixels;
        screen_height = Resources.getSystem().getDisplayMetrics().heightPixels;
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        if(movies == null || movies[position] == null) return;
       // Log.d("test", movies[0].poster_path);
        Picasso.get().load(movies[position].posterPath).into(holder.item_poster);
        holder.movie = movies[position];
    }

    @Override
    public int getItemCount() {
        if(movies == null) return 0;
        return movies.length;
    }

    public void getMovieData(Movie[] movies){
        this.movies = movies;
        notifyDataSetChanged();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements  RecyclerView.OnClickListener{
        public final ImageView item_poster;
        public Movie movie;
        public MovieViewHolder(View itemView) {
            super(itemView);
            item_poster = itemView.findViewById(R.id.item_poster);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d("Adapter","Get clicked" + getAdapterPosition());
            movieAdapaterOnClicker.itemGetClick(movies[getAdapterPosition()]);
        }
    }

}
