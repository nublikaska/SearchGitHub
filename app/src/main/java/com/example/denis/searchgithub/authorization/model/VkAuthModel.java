package com.example.denis.searchgithub.authorization.model;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Denis on 11.04.2018.
 */

public interface VkAuthModel extends BaseAuthModel {
    void logInVk();
}
