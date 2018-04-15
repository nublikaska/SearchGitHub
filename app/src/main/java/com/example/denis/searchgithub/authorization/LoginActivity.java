package com.example.denis.searchgithub.authorization;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.denis.searchgithub.App;
import com.example.denis.searchgithub.MainActivity;
import com.example.denis.searchgithub.R;
import com.example.denis.searchgithub.authorization.modules.AuthPresenterModule;
import com.example.denis.searchgithub.authorization.presenter.facebook.FBAuthPresenterImpl;
import com.example.denis.searchgithub.authorization.presenter.google.GoogleAuthPresenterImpl;
import com.example.denis.searchgithub.authorization.presenter.vkontakte.VkAuthPresenterImpl;
import com.example.denis.searchgithub.authorization.view.LoginView;
import com.example.denis.searchgithub.components.DaggerAuthPresenterComponent;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.SignInButton;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Denis on 09.04.2018.
 */

public class LoginActivity extends AppCompatActivity implements LoginView {

    @Inject
    GoogleAuthPresenterImpl googlePresenter;
    @Inject
    VkAuthPresenterImpl vkPresenter;
    @Inject
    FBAuthPresenterImpl FBpresenter;

    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @BindView(R.id.buttonAuthVk)
    AppCompatButton buttonAuthVk;
    @BindView(R.id.buttonAuthGoogle)
    SignInButton buttonAuthGoogle;
    @BindView(R.id.login_button)
    LoginButton fbButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        DaggerAuthPresenterComponent.builder()
                .singletoneComponent(App.getSingletoneComponent())
                .authPresenterModule(new AuthPresenterModule(this))
                .build()
                .inject(this);

        googlePresenter.createGoogleClient();

        Intent intent = getIntent();
        if (intent.getBooleanExtra("LogOut", false)) {
            logOut();
        }

        buttonAuthVk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vkPresenter.auth();
            }
        });
        buttonAuthGoogle.setSize(SignInButton.SIZE_STANDARD);
        buttonAuthGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googlePresenter.auth();
            }
        });
        fbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FBpresenter.auth();
            }
        });

        FBpresenter.start();
        vkPresenter.start();
        googlePresenter.start();

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void startMainActivity() {
        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
        finish();
        startActivity(mainIntent);
    }

    @Override
    public void startMainActivity(String nameParam, boolean valueParam) {
        Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
        loginIntent.putExtra(nameParam, valueParam);
        finish();
        startActivity(loginIntent);
    }

    @Override
    public void showToast(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT);
    }

    @Override
    public void errorFromAuth(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        vkPresenter.onActivityResult(requestCode, resultCode, data);
        googlePresenter.onActivityResult(LoginActivity.this, requestCode, resultCode, data);
        FBpresenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void strtActivityForResult(Intent intent, int RC_SIGN_IN) {
        startActivityForResult(intent, RC_SIGN_IN);
    }

    @Override
    public Context getContext() {
        return this.getApplicationContext();
    }

    @Override
    public void logOut() {
        googlePresenter.logOut();
        vkPresenter.logOut();
        FBpresenter.logOut();
    }
}
