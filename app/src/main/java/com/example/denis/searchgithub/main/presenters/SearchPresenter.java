package com.example.denis.searchgithub.main.presenters;

import com.example.denis.searchgithub.main.model.entities.GitHubUser;
import com.example.denis.searchgithub.main.views.SearchView;

import java.util.List;

/**
 * Created by Denis on 14.04.2018.
 */

public interface SearchPresenter {
    void onBindSearchRowViewAtPosition(int position, SearchView holder);
    int getSearchRowCount();
    void notifyDataSetChanged(List<GitHubUser> users);
}
