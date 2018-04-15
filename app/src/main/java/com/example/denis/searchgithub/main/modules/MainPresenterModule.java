package com.example.denis.searchgithub.main.modules;

import com.example.denis.searchgithub.components.PerApplication;
import com.example.denis.searchgithub.main.model.GitHubApi;
import com.example.denis.searchgithub.main.model.SearchModel;
import com.example.denis.searchgithub.main.model.SearchModelImpl;
import com.example.denis.searchgithub.main.views.MainView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Denis on 13.04.2018.
 */

@Module
public class MainPresenterModule {

    MainView view;

    public MainPresenterModule(MainView view) {
        this.view = view;
    }

    @Provides
    @PerApplication
    MainView provideView() {
        return view;
    }

    @Provides
    @PerApplication
    SearchModel provideSearchModel(GitHubApi gitHubApi) {
        return new SearchModelImpl(gitHubApi);
    }
}
