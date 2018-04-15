package com.example.denis.searchgithub.main.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Denis on 14.04.2018.
 */

public class GitHubUsersResponse {

    @SerializedName("total_count")
    @Expose
    private Integer totalCount;
    @SerializedName("incomplete_results")
    @Expose
    private Boolean incompleteResults;
    @SerializedName("items")
    @Expose
    private List<GitHubUser> gitHubUsers = null;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Boolean getIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(Boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public List<GitHubUser> getGitHubUsers() {
        return gitHubUsers;
    }

    public void setGitHubUsers(List<GitHubUser> gitHubUsers) {
        this.gitHubUsers = gitHubUsers;
    }

}
