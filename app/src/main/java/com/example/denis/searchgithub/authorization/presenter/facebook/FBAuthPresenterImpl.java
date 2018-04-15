package com.example.denis.searchgithub.authorization.presenter.facebook;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.denis.searchgithub.authorization.model.AuthModelImpl;
import com.example.denis.searchgithub.authorization.view.LoginView;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Denis on 11.04.2018.
 */

public class FBAuthPresenterImpl implements FBAuthPresenter {
    private LoginView view;
    private AuthModelImpl model;
    private static final int RC_SIGN_IN_FB = 1444;
    private CallbackManager callbackManager;
    private List<String> permissionNeeds = Arrays.asList("public_profile", "user_photos");

    @Inject
    public FBAuthPresenterImpl(LoginView view, AuthModelImpl model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void auth() {
        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().logInWithReadPermissions((Activity) view,
                permissionNeeds);
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        if (isLogin()) {
                            model.loginFB();
                            setNameAndPhoto();
                        }
                    }

                    @Override
                    public void onCancel() {
                    }

                    @Override
                    public void onError(FacebookException exception) {

                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (FacebookSdk.isFacebookRequestCode(requestCode)) {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void logOut() {
        LoginManager.getInstance().logOut();
        model.logOut();
    }

    @Override
    public boolean isLogin() {
        try {
            return AccessToken.getCurrentAccessToken() != null;
        } catch (NullPointerException exeption) {
            return false;
        }
    }

    @Override
    public void start() {
        if (isLogin()) {
            model.loginFB();
            setNameAndPhoto();
        }
    }

    @Override
    public void setNameAndPhoto() {
        Bundle params = new Bundle();
        params.putString("fields", "first_name,picture.type(large)");

        new GraphRequest(AccessToken.getCurrentAccessToken(),  "/me/", params, HttpMethod.GET,
                new GraphRequest.Callback() {

                    @Override
                    public void onCompleted( GraphResponse response) {
                        saveData(response);
                        view.startMainActivity("LogIn", true);
                    }
                    private void saveData(GraphResponse response) {
                        JSONObject data = response.getJSONObject();

                        if (response.getError() != null) {
                            model.setLoginStatus(false);
                            return;
                        }

                        if (data.has("picture")) {
                            try {
                                model.setPhoto(Uri.parse(data.getJSONObject("picture").getJSONObject("data").getString("url")));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        try {
                            model.setName(data.getString("first_name"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            model.setName("");
                        }
                    }

                }).executeAsync();
    }
}
