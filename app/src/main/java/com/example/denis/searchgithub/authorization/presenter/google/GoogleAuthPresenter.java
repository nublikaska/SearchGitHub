package com.example.denis.searchgithub.authorization.presenter.google;

import android.app.Activity;
import android.content.Intent;

import com.example.denis.searchgithub.authorization.view.LoginView;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;

/**
 * Created by Denis on 10.04.2018.
 */

public interface GoogleAuthPresenter {
    void createGoogleClient();
    void onStart();
    void auth();
    void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data);
    void logOut();
    boolean isLogin();
    void start();
}
