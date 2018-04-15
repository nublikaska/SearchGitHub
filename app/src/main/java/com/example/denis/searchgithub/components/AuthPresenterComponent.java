package com.example.denis.searchgithub.components;

import com.example.denis.searchgithub.authorization.LoginActivity;
import com.example.denis.searchgithub.authorization.modules.AuthPresenterModule;

import dagger.Component;

/**
 * Created by Denis on 10.04.2018.
 */
@PerApplication
@Component(dependencies = SingletoneComponent.class, modules = AuthPresenterModule.class)
public interface AuthPresenterComponent {
    void inject(LoginActivity activity);
}
