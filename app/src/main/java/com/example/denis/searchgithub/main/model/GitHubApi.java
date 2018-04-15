package com.example.denis.searchgithub.main.model;

import com.example.denis.searchgithub.main.model.entities.GitHubUser;
import com.example.denis.searchgithub.main.model.entities.GitHubUsersResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Denis on 14.04.2018.
 */

public interface GitHubApi {
    @GET("search/users")
    Observable<GitHubUsersResponse> getData
            (@Query("q") String query, @Query("page") int currentPage, @Query("access_token") String token);

    @GET("users/{user}")
    Observable<GitHubUser> getUser
            (@Path("user") String login);
}
