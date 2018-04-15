package com.example.denis.searchgithub.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.denis.searchgithub.R;
import com.example.denis.searchgithub.main.model.entities.GitHubUser;
import com.example.denis.searchgithub.main.presenters.SearchPresenter;
import com.example.denis.searchgithub.main.presenters.SearchPresenterImpl;
import com.example.denis.searchgithub.main.views.SearchView;
import com.example.denis.searchgithub.main.views.SearchViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Denis on 14.04.2018.
 */

public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchViewHolder> {

    private SearchPresenter searchPresenter;

    public SearchRecyclerAdapter(SearchPresenter searchPresenter) {
        this.searchPresenter = searchPresenter;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        searchPresenter.onBindSearchRowViewAtPosition(position, holder);
    }

    @Override
    public int getItemCount() {
        return searchPresenter.getSearchRowCount();
    }

    public void myNotifyDataSetChanged(List<GitHubUser> users) {
        searchPresenter.notifyDataSetChanged(users);
        notifyDataSetChanged();
    }
}
