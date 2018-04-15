package com.example.denis.searchgithub.main.presenters;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;

/**
 * Created by Denis on 13.04.2018.
 */

public interface NavigationDrawerPresenter extends NavigationView.OnNavigationItemSelectedListener{
    boolean onOptionsItemSelected(MenuItem item);
}
