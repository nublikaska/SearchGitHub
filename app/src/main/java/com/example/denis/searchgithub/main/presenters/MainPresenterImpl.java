package com.example.denis.searchgithub.main.presenters;

import android.net.Uri;
import android.support.v7.widget.SearchView;

import com.example.denis.searchgithub.authorization.model.AuthModelImpl;
import com.example.denis.searchgithub.authorization.model.BaseAuthModel;
import com.example.denis.searchgithub.main.model.SearchModel;
import com.example.denis.searchgithub.main.model.SearchModelImpl;
import com.example.denis.searchgithub.main.model.entities.GitHubUser;
import com.example.denis.searchgithub.main.model.entities.GitHubUsersResponse;
import com.example.denis.searchgithub.main.views.MainView;
import com.squareup.picasso.Picasso;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Denis on 13.04.2018.
 */

public class MainPresenterImpl implements MainPresenter, SearchView.OnQueryTextListener {

    private MainView view;
    private AuthModelImpl authModel;
    private SearchModel searchModel;
    private List<GitHubUser> users = new ArrayList<>();
    private PublishSubject publishSubject;
    private Disposable currentDisposable;
    private int maxPage = 1;

    @Inject
    public MainPresenterImpl(MainView view, AuthModelImpl authModel, SearchModel searchModel) {
        this.view = view;
        this.authModel = authModel;
        this.searchModel = searchModel;
    }

    @Override
    public void start() {
        Uri photo = authModel.getUserPhoto();
        String name = authModel.getName();
        if (photo!=null) {
            Picasso.get().load(photo).into(view.getAvatar());
        }
        if (name != null) {
            view.setUserName(name);
        }
    }

    @Override
    public void search() {
        searchModel.getData(view.getQuery(), view.getCurrentPage(), searchModel.getToken())
                //.throttleLast(6, TimeUnit.MILLISECONDS)
                .subscribe(new Observer<GitHubUsersResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        currentDisposable = d;
                    }

                    @Override
                    public void onNext(GitHubUsersResponse value) {
                        try {
                            users = value.getGitHubUsers();
                            maxPage = value.getTotalCount()/30;
                            if (maxPage == 0) {
                                maxPage++;
                            }
                            if (maxPage > 35) {
                                maxPage = 35;
                            }
                        } catch (IndexOutOfBoundsException e) {
                            users.clear();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        view.setAllPages(String.valueOf(maxPage));
                        view.updateUi(users);
                    }
                });
    }

    @Override
    public void nextPage() {
        int cp = view.getCurrentPage();
        if (cp != maxPage) {
            disposeResponse();
            cp++;
            view.setCurrentPage(String.valueOf(cp));
            search();
        }
    }

    @Override
    public void prevPage() {
        int cp = view.getCurrentPage();
        if (cp != 1) {
            disposeResponse();
            cp--;
            view.setCurrentPage(String.valueOf(cp));
            search();
        }
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        view.setCurrentPage("1");
        disposeResponse();
        if (!newText.equals("")) {
            search();
        } else {
            view.setCurrentPage("");
            view.setAllPages("");
            users.clear();
            view.updateUi(users);
        }
        return true;
    }

    private void disposeResponse() {
        if (currentDisposable != null) {
            currentDisposable.dispose();
        }
    }
}
