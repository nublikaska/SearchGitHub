package com.example.denis.searchgithub;

import android.app.Application;

import com.example.denis.searchgithub.authorization.modules.UserModule;
import com.example.denis.searchgithub.components.DaggerSingletoneComponent;
import com.example.denis.searchgithub.components.SingletoneComponent;
import com.example.denis.searchgithub.main.modules.RetrofitModule;
import com.facebook.FacebookSdk;
import com.vk.sdk.VKSdk;

/**
 * Created by Denis on 09.04.2018.
 */

public class App extends Application {
    private static SingletoneComponent singletoneComponent;

    @Override
        public void onCreate() {
        super.onCreate();

        VKSdk.initialize(this);
        FacebookSdk.sdkInitialize(this);

        singletoneComponent = DaggerSingletoneComponent.builder()
                .retrofitModule(new RetrofitModule("https://api.github.com"))
                .userModule(new UserModule())
                .build();
    }

    public static SingletoneComponent getSingletoneComponent() {
        return singletoneComponent;
    }
}
