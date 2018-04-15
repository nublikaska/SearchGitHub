package com.example.denis.searchgithub.authorization.view;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Denis on 10.04.2018.
 */

public interface LoginView {
        void showProgress();
        void hideProgress();
        void startMainActivity();
        void startMainActivity(String nameParam, boolean valueParam);
        void showToast(String error);
        void errorFromAuth(int requestCode, int resultCode, Intent data);
        void strtActivityForResult(Intent intent, int RC_SIGN_IN);
        Context getContext();
        void logOut();
}
