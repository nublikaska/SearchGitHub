package com.example.denis.searchgithub.authorization.modules;

import com.example.denis.searchgithub.authorization.model.AuthModelImpl;
import com.example.denis.searchgithub.authorization.model.BaseAuthModel;
import com.example.denis.searchgithub.authorization.model.VkAuthModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Denis on 10.04.2018.
 */

@Module
public class UserModule {
    String BaseUrl;

    @Provides
    @Singleton
    AuthModelImpl provideGoogleAuthModel() {
        return new AuthModelImpl();
    }

}
