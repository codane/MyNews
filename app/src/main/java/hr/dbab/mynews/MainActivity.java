package hr.dbab.mynews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import hr.dbab.mynews.adapter.CustomAdapter;
import hr.dbab.mynews.model.Article;
import hr.dbab.mynews.model.ResponseModel;
import hr.dbab.mynews.rest.NetworkAPI;
import hr.dbab.mynews.rest.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements CustomItemClickListener {

    public static final String API_KEY ="";//your API key here
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getData();
    }

    public void getData(){
        final NetworkAPI networkService = RetrofitClient.getClient().create(NetworkAPI.class);

        //we call the API asynchronously
        Call<ResponseModel> call = networkService.getNews("bbc-sport", API_KEY);

        // here Rttrofit is parsing data on a background thread and passes that data back to the main thread
        // using the onResponse and onFailure methods
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.body().getStatus().equals("ok")) {
                    List<Article> articleList = response.body().getArticles();
                    if (articleList.size() > 0) {
                        final CustomAdapter adapter = new CustomAdapter(articleList);
                        adapter.setCustomItemClickListener(MainActivity.this);
                        recyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                Log.e("failure", t.toString());

            }
        });
    }

    @Override
    public void customOnClick(View view, int position) {
        if (view.getId() == R.id.card_lin_layout){
            Article clickedArticle = (Article) view.getTag();
            Intent webIntent = new Intent(this, WebActivity.class);
            webIntent.putExtra("url", clickedArticle.getUrl());
            startActivity(webIntent);
        }
    }
}
