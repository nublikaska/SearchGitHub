package com.example.denis.searchgithub.main.presenters;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.view.MenuItem;

import com.example.denis.searchgithub.R;
import com.example.denis.searchgithub.main.views.MainView;

import javax.inject.Inject;

/**
 * Created by Denis on 13.04.2018.
 */

public class NavigationDrawerPresenterImpl implements NavigationDrawerPresenter {

    MainView view;

    @Inject
    public NavigationDrawerPresenterImpl(MainView view) {
        this.view = view;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
                case R.id.exit :
                    view.startLoginActivity("LogOut", true);
                    view.closeDrawer(GravityCompat.START);
                    break;
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                view.openDrawer(GravityCompat.START);
                return true;
        }
        return false;
    }
}
