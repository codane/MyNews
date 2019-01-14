package hr.dbab.mynews.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


//we use this class to send a network request to API
//for that we use Retrofit.Builder class

public class RetrofitClient {

    //specifying the basic URL which we will use for all the requests
    public static final String BASE_URL = "https://newsapi.org/v2/";

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())//this converter factory is used for mapping JSON data into our Java object which we will create in model package
                    .build();
        }
        return retrofit;

    }
}
