package wenjunjie.popularmovie2;

import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import wenjunjie.popularmovie2.Utility.Movie;

public class DetailActivity extends AppCompatActivity {

    Movie mMovie;

    TextView headerTv;
    TextView titleTv;
    TextView rateTv;
    TextView overviewTv;
    ImageView posterIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mMovie = (Movie)getIntent().getSerializableExtra(MainActivity.MOVIE_EXTRA);

        titleTv = (TextView)findViewById(R.id.detail_title);
        rateTv = (TextView)findViewById(R.id.detail_rate);
        overviewTv = (TextView)findViewById(R.id.detail_overview);
        headerTv = (TextView)findViewById(R.id.detail_header_title);
        posterIV = (ImageView)findViewById(R.id.detail_poster);

        titleTv.setText(mMovie.title);
        rateTv.setText(mMovie.rate);
        overviewTv.setText(mMovie.overview);
        headerTv.setText(mMovie.title);
        Log.d("adapter", mMovie.posterPath);
        Picasso.get().load(mMovie.posterPath).into(posterIV);
    }
}
