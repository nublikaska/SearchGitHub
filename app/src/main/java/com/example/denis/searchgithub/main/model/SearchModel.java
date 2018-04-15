package com.example.denis.searchgithub.main.model;

import com.example.denis.searchgithub.main.model.entities.GitHubUser;
import com.example.denis.searchgithub.main.model.entities.GitHubUsersResponse;

import io.reactivex.Observable;
import retrofit2.http.Path;

/**
 * Created by Denis on 14.04.2018.
 */

public interface SearchModel {
    Observable<GitHubUsersResponse> getData(String query, int currentPage, String token);
    Observable<GitHubUser> getUser(String login);
    String getToken();
}
