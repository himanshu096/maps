package ga.himanshu.home.teststripe.services;

import android.util.Log;

import java.io.IOException;
import java.lang.annotation.Annotation;

import ga.himanshu.home.teststripe.models.Location;
import ga.himanshu.home.teststripe.models.Result;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Himanshu on 7/28/2016.
 */
public class MyPlaceService {
    private final String BASE_URL = "https://maps.googleapis.com/maps/api/geocode/";
    private MyPlaceServiceInterface service;
    private Retrofit retrofit;

    public MyPlaceService(){


        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(MyPlaceServiceInterface.class);
    }


    public void listPlace(String address,Callback<Location> callback){
        service.getPlace(address,"AIzaSyDPhKKAp1WLWX3w5A_CxjwbkQ-3xPgxL9g").enqueue(new CallbackHandler<Location>(retrofit,callback));;
    }







    private class CallbackHandler<T> implements retrofit2.Callback<T> {

        private Retrofit retrofit;
        private Callback callback;

        public CallbackHandler(Retrofit retrofit, Callback callback) {
            this.retrofit = retrofit;
            this.callback = callback;
        }

        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            if(response.isSuccessful()) {
                callback.onSuccess(response.body());
            } else {
                Log.d("codekamp", "response isSuccessful");

                Converter<ResponseBody, Error> errorConverter =
                        retrofit.responseBodyConverter(Error.class, new Annotation[0]);
                try {
                    Error error = errorConverter.convert(response.errorBody());
                    callback.onFailure(error);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            Log.d("codekamp", "response onFailure");

            Error error = new Error("error occured");

            callback.onFailure(error);
        }
    }
}

