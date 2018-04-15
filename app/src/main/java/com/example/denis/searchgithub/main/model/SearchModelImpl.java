package com.example.denis.searchgithub.main.model;

import com.example.denis.searchgithub.main.model.entities.GitHubUser;
import com.example.denis.searchgithub.main.model.entities.GitHubUsersResponse;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Denis on 14.04.2018.
 */

public class SearchModelImpl implements SearchModel {

    private GitHubApi gitHubApi;
    private static final String token = "480939ccb7777170f2830fd98ac2d0fee37ea28e";

    public SearchModelImpl(GitHubApi gitHubApi) {
        this.gitHubApi = gitHubApi;
    }

    @Override
    public Observable<GitHubUsersResponse> getData(String query, int currentPage, String token) {
        return gitHubApi
                .getData(query, currentPage, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<GitHubUser> getUser(String login) {
        return gitHubApi
                .getUser(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public String getToken() {
        return token;
    }

}
