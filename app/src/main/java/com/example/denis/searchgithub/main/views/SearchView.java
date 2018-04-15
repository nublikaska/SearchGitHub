package com.example.denis.searchgithub.main.views;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Denis on 14.04.2018.
 */

public interface SearchView {
    void setUserInfo(String login);
    CircleImageView getAvatar();
}
