package com.example.denis.searchgithub.authorization.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import com.example.denis.searchgithub.R;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Denis on 10.04.2018.
 */

public class AuthModelImpl implements VkAuthModel, GoogleAuthModel, FBAuthModel {
    private String name;
    private Uri photo;
    private boolean loginStatus = false;
    private SharedPreferences sPref;
    public static final String MY_PREF = "MY_PREF";

    @Inject
    public AuthModelImpl() {

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Uri getUserPhoto() {
        return photo;
    }

    @Override
    public boolean getLoginStatus() {
        return loginStatus;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    @Override
    public void setPhoto(Uri photo) {
        this.photo = photo;
    }

    @Override
    public void logOut() {
        loginStatus = false;
        name = null;
        photo = null;
    }

    @Override
    public void logInVk() {
        setLoginStatus(true);
    }

    @Override
    public void loginFB() {
        setLoginStatus(true);
    }

    @Override
    public void loginGoogle(GoogleSignInAccount account) {
        setLoginStatus(true);
        setName(account.getDisplayName());
        setPhoto(account.getPhotoUrl());
    }
}
