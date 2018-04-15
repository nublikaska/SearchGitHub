package com.example.denis.searchgithub.authorization.presenter.google;

import android.app.Activity;
import android.content.Intent;

import com.example.denis.searchgithub.authorization.model.AuthModelImpl;
import com.example.denis.searchgithub.authorization.view.LoginView;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import javax.inject.Inject;

/**
 * Created by Denis on 10.04.2018.
 */

public class GoogleAuthPresenterImpl implements GoogleAuthPresenter {

    private LoginView view;
    private AuthModelImpl model;
    private GoogleSignInClient mGoogleSignInClient;
    GoogleSignInAccount account;
    private static final int RC_SIGN_IN = 2018;

    @Inject
    public GoogleAuthPresenterImpl(LoginView view, AuthModelImpl model) {
        this.view = view;
        this.model = model;
    }
    @Override
    public void createGoogleClient() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient((Activity) view, gso);
    }

    @Override
    public void onStart() {
        account = GoogleSignIn.getLastSignedInAccount((Activity) view);
        if (account != null) {
            view.startMainActivity();
        }
    }

    @Override
    public void auth() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        view.strtActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task, requestCode, resultCode, data);
        }
    }

    @Override
    public void logOut() {
        mGoogleSignInClient.signOut();
        model.logOut();
    }

    @Override
    public boolean isLogin() {
        account = GoogleSignIn.getLastSignedInAccount((Activity)view);
        if (account != null) {
            return true;
        } return false;

    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask, int requestCode, int resultCode, Intent data) {
        try {
            account = completedTask.getResult(ApiException.class);
            model.loginGoogle(account);
            view.startMainActivity("LogIn", true);
        } catch (ApiException e) {
            view.errorFromAuth(requestCode, resultCode, data);
        }
    }

    @Override
    public void start() {
        boolean s = isLogin();
        if (isLogin()) {
            model.loginGoogle(account);
            view.startMainActivity("LogIn", true);
        }
    }
}
