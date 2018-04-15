package com.example.denis.searchgithub.main.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.denis.searchgithub.R;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Denis on 14.04.2018.
 */

public class SearchViewHolder extends RecyclerView.ViewHolder implements SearchView {

    private TextView userText;
    private CircleImageView profileImage;

    public SearchViewHolder(View itemView) {
        super(itemView);
        userText = itemView.findViewById(R.id.user_text);
        profileImage = itemView.findViewById(R.id.profile_image2);
    }

    @Override
    public void setUserInfo(String login) {
        String loin = login;
        userText.setText(login);
    }

    @Override
    public CircleImageView getAvatar() {
        return profileImage;
    }
}
