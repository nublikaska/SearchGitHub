package com.example.denis.searchgithub.authorization.presenter.vkontakte;

import android.content.Intent;

import com.example.denis.searchgithub.authorization.view.LoginView;

/**
 * Created by Denis on 10.04.2018.
 */

public interface VkAuthPresenter {
    void auth();
    void onActivityResult(int requestCode, int resultCode, Intent data);
    void logOut();
    boolean isLogin();
    void start();
    void setNameAndPhoto();
}
