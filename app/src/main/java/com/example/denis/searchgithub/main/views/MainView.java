package com.example.denis.searchgithub.main.views;

import com.example.denis.searchgithub.main.model.entities.GitHubUser;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Denis on 13.04.2018.
 */

public interface MainView {
    void startLoginActivity(String nameParam, boolean valueParam);
    void startLoginActivity();
    void closeDrawer(int gravity);
    void openDrawer(int gravity);
    CircleImageView getAvatar();
    String getQuery();
    void updateUi(List<GitHubUser> users);
    int getCurrentPage();
    void setRecyclerView();
    void setUserName(String userName);
    void setCurrentPage(String page);
    void setAllPages(String page);
}
