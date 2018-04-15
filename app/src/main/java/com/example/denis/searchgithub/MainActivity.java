package com.example.denis.searchgithub;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.denis.searchgithub.authorization.LoginActivity;
import com.example.denis.searchgithub.components.DaggerMainPresenterComponent;
import com.example.denis.searchgithub.main.SearchRecyclerAdapter;
import com.example.denis.searchgithub.main.model.entities.GitHubUser;
import com.example.denis.searchgithub.main.modules.MainPresenterModule;
import com.example.denis.searchgithub.main.presenters.MainPresenterImpl;
import com.example.denis.searchgithub.main.presenters.NavigationDrawerPresenterImpl;
import com.example.denis.searchgithub.main.presenters.SearchPresenterImpl;
import com.example.denis.searchgithub.main.views.MainView;

import org.w3c.dom.Text;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements MainView {
    @Inject
    NavigationDrawerPresenterImpl navDrawerPresenter;
    @Inject
    MainPresenterImpl mainPresenter;

    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.buttonPrev)
    Button buttonPrev;
    @BindView(R.id.buttonNext)
    Button buttonNext;
    @BindView(R.id.textCurrentPage)
    TextView textCurrentPage;
    @BindView(R.id.allPages)
    TextView allPages;
    @BindView(R.id.rv)
    RecyclerView mRecyclerView;
    private SearchView searchView;


    private CircleImageView profileImage;
    private TextView userName;
    private RecyclerView.LayoutManager mLayoutManager;
    private SearchRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        View headerLayout = navigationView.getHeaderView(0);
        profileImage = headerLayout.findViewById(R.id.profile_image);
        userName = headerLayout.findViewById(R.id.userName);

        DaggerMainPresenterComponent.builder()
                .singletoneComponent(App.getSingletoneComponent())
                .mainPresenterModule(new MainPresenterModule(this))
                .build()
                .inject(this);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);

        Intent intent = getIntent();
        if (!intent.getBooleanExtra("LogIn", false)) {
            startLoginActivity();
            return;
        }

        setRecyclerView();

        mainPresenter.start();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return navDrawerPresenter.onNavigationItemSelected(item);
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresenter.nextPage();
            }
        });

        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresenter.prevPage();
            }
        });
        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return navDrawerPresenter.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return mainPresenter.onQueryTextSubmit(query);
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return mainPresenter.onQueryTextChange(newText);
            }
        });

        return true;
    }


    @Override
    public void startLoginActivity(String nameParam, boolean valueParam) {
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        loginIntent.putExtra(nameParam, valueParam);
        finish();
        startActivity(loginIntent);
    }

    @Override
    public void startLoginActivity() {
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        finish();
        startActivity(loginIntent);
    }

    @Override
    public void closeDrawer(int gravity) {
        drawerLayout.closeDrawer(gravity);
    }

    @Override
    public void openDrawer(int gravity) {
        drawerLayout.openDrawer(gravity);
    }

    @Override
    public CircleImageView getAvatar() {
        return profileImage;
    }

    @Override
    public String getQuery() {
        return searchView.getQuery().toString();
    }

    @Override
    public void updateUi(List<GitHubUser> users) {
        adapter.myNotifyDataSetChanged(users);
    }

    @Override
    public int getCurrentPage() {

        if (!textCurrentPage.getText().toString().equals("")) {
            return Integer.parseInt(textCurrentPage.getText().toString());
        }
        return 1;
    }

    @Override
    public void setRecyclerView() {
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        adapter = new SearchRecyclerAdapter(new SearchPresenterImpl());
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void setUserName(String userName) {
        this.userName.setText(userName);
    }

    @Override
    public void setCurrentPage(String page) {
        textCurrentPage.setText(page);
    }

    @Override
    public void setAllPages(String page) {
        allPages.setText("из " + page);
    }
}
