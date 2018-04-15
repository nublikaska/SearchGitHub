package com.example.denis.searchgithub.authorization.model;

import android.content.Context;
import android.net.Uri;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

/**
 * Created by Denis on 11.04.2018.
 */

public interface BaseAuthModel {
    String getName();
    Uri getUserPhoto();
    boolean getLoginStatus();
    void setName(String name);
    void setLoginStatus(boolean loginStatus);
    void setPhoto(Uri photo);
    void logOut();
}
