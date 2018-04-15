package com.example.denis.searchgithub.components;

import com.example.denis.searchgithub.MainActivity;
import com.example.denis.searchgithub.main.modules.GitHubModule;
import com.example.denis.searchgithub.main.modules.MainPresenterModule;

import dagger.Component;

/**
 * Created by Denis on 13.04.2018.
 */
@PerApplication
@Component(dependencies = {SingletoneComponent.class}, modules = {MainPresenterModule.class, GitHubModule.class})
public interface MainPresenterComponent {
    void inject(MainActivity activity);
}
