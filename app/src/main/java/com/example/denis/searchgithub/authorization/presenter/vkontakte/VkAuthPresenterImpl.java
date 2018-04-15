package com.example.denis.searchgithub.authorization.presenter.vkontakte;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.example.denis.searchgithub.App;
import com.example.denis.searchgithub.authorization.model.AuthModelImpl;
import com.example.denis.searchgithub.authorization.view.LoginView;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKScopes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

/**
 * Created by Denis on 09.04.2018.
 */

public class VkAuthPresenterImpl implements VkAuthPresenter {

    private LoginView view;
    private AuthModelImpl model;

    @Inject
    public VkAuthPresenterImpl(LoginView view, AuthModelImpl model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void auth() {
        view.showProgress();
        VKSdk.login((Activity) view, VKScopes.PHOTOS, VKScopes.OFFLINE);
        view.hideProgress();
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                model.logInVk();
                setNameAndPhoto();
            }
            @Override
            public void onError(VKError error) {
                view.errorFromAuth(requestCode ,resultCode, data);
            }
        }));
    }

    @Override
    public void logOut() {
        VKSdk.logout();
        model.logOut();
    }

    @Override
    public boolean isLogin() {
        try {
            return VKAccessToken.currentToken().accessToken != null;
        } catch (NullPointerException exeption) {
            return false;
        }
    }

    @Override
    public void start() {
        if (isLogin()) {
            model.logInVk();
            setNameAndPhoto();
        }
    }

    @Override
    public void setNameAndPhoto() {
        VKParameters params = new VKParameters();
        params.put(VKApiConst.FIELDS, "photo_100");

        VKRequest request = new VKRequest("users.get",params);
        request.executeWithListener(new VKRequest.VKRequestListener() {

            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                JSONArray resp = null;
                try {
                    resp = response.json.getJSONArray("response");

                    JSONObject user = resp.getJSONObject(0);
                    model.setName(user.getString("first_name"));
                    model.setPhoto(Uri.parse(user.getString("photo_100")));
                    view.startMainActivity("LogIn", true);

                } catch (JSONException e) {
                    e.printStackTrace();
                    view.startMainActivity("LogIn", true);
                }
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
                view.startMainActivity("LogIn", true);
            }
        });
    }
}
