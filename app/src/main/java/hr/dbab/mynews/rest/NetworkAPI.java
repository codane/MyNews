package hr.dbab.mynews.rest;

import hr.dbab.mynews.model.ResponseModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkAPI {

    @GET("top-headlines")
    Call<ResponseModel> getNews (@Query("sources") String source, @Query("apiKey") String apiKey);
}
