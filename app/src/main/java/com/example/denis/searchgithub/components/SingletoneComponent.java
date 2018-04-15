package com.example.denis.searchgithub.components;

import com.example.denis.searchgithub.MainActivity;
import com.example.denis.searchgithub.authorization.model.AuthModelImpl;
import com.example.denis.searchgithub.authorization.modules.UserModule;
import com.example.denis.searchgithub.main.modules.RetrofitModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by Denis on 10.04.2018.
 */
@Singleton
@Component(modules = {UserModule.class, RetrofitModule.class})
public interface SingletoneComponent {
    AuthModelImpl getAuthModel();
    Retrofit getRetrofit();
}
