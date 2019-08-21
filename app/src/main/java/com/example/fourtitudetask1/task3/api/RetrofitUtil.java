package com.example.fourtitudetask1.task3.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {
    private static Retrofit retrofit;

    private static final String BASE_URL = "https://www.omdbapi.com/";

    public static Retrofit getRetrofitInstance() {

        if (retrofit == null) {
            //uncomment it if needed to format dateTime value, and apply ".addConverterFactory(GsonConverterFactory.create(gson))"
//            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
