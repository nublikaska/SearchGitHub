package com.example.denis.searchgithub.authorization.presenter.facebook;

import android.content.Intent;

/**
 * Created by Denis on 11.04.2018.
 */

public interface FBAuthPresenter {
    void auth();
    void onActivityResult( int requestCode, int resultCode, Intent data);
    void logOut();
    boolean isLogin();
    void start();
    void setNameAndPhoto();
}
