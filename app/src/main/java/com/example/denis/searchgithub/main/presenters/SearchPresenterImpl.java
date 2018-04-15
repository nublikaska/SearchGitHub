package com.example.denis.searchgithub.main.presenters;

import android.net.Uri;

import com.example.denis.searchgithub.authorization.modules.UserModule;
import com.example.denis.searchgithub.main.model.entities.GitHubUser;
import com.example.denis.searchgithub.main.model.entities.GitHubUsersResponse;
import com.example.denis.searchgithub.main.views.SearchView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Denis on 14.04.2018.
 */

public class SearchPresenterImpl implements SearchPresenter {

    private List<GitHubUser> usersList = new ArrayList<>();

    @Override
    public void onBindSearchRowViewAtPosition(int position, SearchView holder) {
        GitHubUser user = usersList.get(position);
        holder.setUserInfo(user.getLogin());
        Uri photo = Uri.parse(user.getAvatarUrl());
        if (photo!=null) {
            Picasso.get().load(photo).into(holder.getAvatar());
        }
    }

    @Override
    public int getSearchRowCount() {
        if(usersList==null) {
            return 0;
        }
        return usersList.size();
    }

    @Override
    public void notifyDataSetChanged(List<GitHubUser> users) {
        if (usersList != null) {
            usersList.clear();
        }
        usersList.addAll(users);
    }
}
