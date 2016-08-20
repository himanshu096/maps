package ga.himanshu.home.teststripe.services;

import android.location.Location;

import ga.himanshu.home.teststripe.models.AddressComponent;
import ga.himanshu.home.teststripe.models.Location_;
import ga.himanshu.home.teststripe.models.Result;
import ga.himanshu.home.teststripe.models.Viewport;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by Himanshu on 7/28/2016.
 */
public interface MyPlaceServiceInterface {
    @GET("json")
    Call<ga.himanshu.home.teststripe.models.Location> getPlace(@Query("address") String myaddress, @Query("key") String key);
}
