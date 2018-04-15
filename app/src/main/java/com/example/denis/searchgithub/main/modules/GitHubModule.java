package com.example.denis.searchgithub.main.modules;

import com.example.denis.searchgithub.components.PerApplication;
import com.example.denis.searchgithub.main.model.GitHubApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Denis on 14.04.2018.
 */

@Module
public class GitHubModule {

    @Provides
    @PerApplication
    GitHubApi provideGitHubApi(Retrofit retrofit) {
        return retrofit.create(GitHubApi.class);
    }
}
