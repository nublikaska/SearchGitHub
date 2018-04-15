package com.example.denis.searchgithub.main.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.denis.searchgithub.R;

import butterknife.BindView;

/**
 * Created by Denis on 14.04.2018.
 */

public class SearchViewHolder extends RecyclerView.ViewHolder implements SearchView {

    private TextView userText;

    public SearchViewHolder(View itemView) {
        super(itemView);
        userText = itemView.findViewById(R.id.user_text);
    }

    @Override
    public void setUserInfo(String login) {
        String loin = login;
        userText.setText(login);
    }
}
