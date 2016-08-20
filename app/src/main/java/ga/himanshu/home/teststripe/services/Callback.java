package ga.himanshu.home.teststripe.services;

/**
 * Created by Himanshu on 7/28/2016.
 */
public interface Callback<T> {

    void onSuccess(T response);


    void onFailure(Error error);
}
