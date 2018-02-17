package com.example.admin.githubrepos.service.repository;

import android.app.Application;
import android.support.annotation.NonNull;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.admin.githubrepos.service.model.Project;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Admin on 2/17/2018.
 */

public class ProjectRepository extends Application {
    private GitHubService gitHubService;

    public ProjectRepository(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }



    /*
    * This class will return a  list of the github projects of the given userId
    *
    * */
    public LiveData<List<Project>> getProjectList(String userId) {

        final MutableLiveData<List<Project>> data = new MutableLiveData<>();

        gitHubService.getProjectList(userId).enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(@NonNull Call<List<Project>> call, @NonNull Response<List<Project>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
                // Handle failures inside here
            }
        });
        return data;
    }

    /*
    *
    * This function will return details of the selected project
    */

    public LiveData<Project> getProjectDetails(String userID, String projectName) {
        final MutableLiveData<Project> data = new MutableLiveData<>();

        gitHubService.getProjectDetails(userID, projectName).enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {
                simulateDelay();
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Project> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }


    // Set some delay to allow a little more fetching of data from the network
    private void simulateDelay() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
