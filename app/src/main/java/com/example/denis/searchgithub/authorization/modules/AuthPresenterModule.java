package com.example.denis.searchgithub.authorization.modules;

import com.example.denis.searchgithub.components.PerApplication;
import com.example.denis.searchgithub.authorization.view.LoginView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Denis on 10.04.2018.
 */

@Module
public class AuthPresenterModule {
    LoginView view;

    public AuthPresenterModule(LoginView view) {
        this.view = view;
    }

    @Provides
    @PerApplication
    LoginView provideView() {
        return view;
    }
}
