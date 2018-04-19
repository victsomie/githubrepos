package com.example.admin.githubrepos.service.repository;

import com.example.admin.githubrepos.service.model.Project;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Admin on 2/17/2018.
 */

public interface GitHubService {
    String HTTPS_API_GITHUB_URL = "https://api.github.com/";

    @GET("users/{user}/repos?page=7&per_page=700")
    Call<List<Project>> getProjectList(@Path("user") String user);

    // @GET("repos/{user}/{reponame}")
    @GET("users/{user}/repos/{reponame}")
    Call<Project> getProjectDetails(@Path("user") String user, @Path("reponame") String projectName);

}
