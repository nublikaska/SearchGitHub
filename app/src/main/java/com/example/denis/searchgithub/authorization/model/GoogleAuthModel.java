package com.example.denis.searchgithub.authorization.model;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

/**
 * Created by Denis on 11.04.2018.
 */

public interface GoogleAuthModel extends BaseAuthModel {
    void loginGoogle(GoogleSignInAccount account);
}
