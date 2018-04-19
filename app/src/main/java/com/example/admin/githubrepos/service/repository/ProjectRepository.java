package com.example.admin.githubrepos.service.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.admin.githubrepos.db.ProjectDao;
import com.example.admin.githubrepos.service.model.Project;

import java.io.IOException;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Admin on 2/17/2018.
 */

public class ProjectRepository {

    private static final String TAG = ProjectRepository.class.getSimpleName();


    private GitHubService gitHubService;
    private ProjectDao projectDao;
    private Executor executor;

    public ProjectRepository(GitHubService gitHubService, ProjectDao projectDao, Executor executor) {
        this.gitHubService = gitHubService;
        this.projectDao = projectDao;
        this.executor = executor;
    }

    public ProjectRepository(GitHubService gitHubService, ProjectDao projectDao) {
        this.gitHubService = gitHubService;
        this.projectDao = projectDao;
    }

    public ProjectRepository(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }


    private LiveData<List<Project>> getProjects() {

        return projectDao.getAll();
    }


    /*
    * This class will return a  list of the github projects of the given userId
    *
    * */
    public LiveData<List<Project>> getProjectList(final String userId) {

        refreshProject(userId);

        return getProjects();

        //return projectDao.getAll();

                /*.observe(this, new Observer<List<Project>>() {
            @Override
            public void onChanged(@Nullable List<Project> projects) {
                if (projects == null){
                    Log.e(TAG, "No projects is the list");
                } else {
                    //return projects;
                }

                //return;
            }
        });

        */


        /*
        simulateDelay();
        final MutableLiveData<List<Project>> data = new MutableLiveData<>();

        Log.e(TAG, "data >> " + projectDao.getAll().toString());

        //data.postValue((List<Project>) projectDao.getAll());
        //data.setValue((List<Project>) projectDao.getAll());

        data.postValue((List<Project>) projectDao.getAll());

        //return projectDao.getAll();
        return data;
        */


        /*
        final MutableLiveData<List<Project>> data = new MutableLiveData<>();

        gitHubService.getProjectList(userId).enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(@NonNull Call<List<Project>> call, @NonNull Response<List<Project>> response) {
                Log.e("PROJECT REPOS : >> ", data.toString());

                simulateDelay();
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
                // Handle failures inside here
            }
        });
        return data;
        */
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


    // Refresshing the DB
    private void refreshProject(final String userId) {

        Log.e("PROJECT REPOS : >> ", "We are refreshing your list here");

        final MutableLiveData<List<Project>> data = new MutableLiveData<>();

        gitHubService.getProjectList(userId).enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(@NonNull Call<List<Project>> call, @NonNull Response<List<Project>> response) {
                Log.e("PROJECT REPOS : >> ", data.toString());
                Log.e("Repos Response : >> ", response.body().toString());


                //simulateDelay();
                //data.setValue(response.body());

                /*8
                AsyncTask.execute(new Runnable() { @Overridepublic void run() {}});

                    */
                if (response.isSuccessful()) {
                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            projectDao.insertAll(response.body());
                        }
                    });

                    /*
                    for (Project project : response.body()) {

                        Log.e("ProjectRepository", "> " + project.name);

                        AsyncTask.execute(new Runnable() {
                            @Override
                            public void run() {
                                projectDao.insertProject(project);
                            }
                        });

                    }
                    //projectDao.insertProject(project);
                    */
                }


//                for (Project project: data.getValue()) {
//
//                    projectDao.insertProject(project);
//                    //projectDao.save(project);
//
//                }
            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
                // Handle failures inside here
                Log.e(TAG, t.getMessage());
            }
        });

        // ------------------------------------------
        /*
        final Response[] response = {null};
        executor.execute(() -> {
            // running in a background thread
            // check if user was fetched recently
            // boolean userExists = userDao.hasUser(FRESH_TIMEOUT);
            LiveData<List<Project>> userExists = projectDao.getAll();

            try {
                response[0] = gitHubService.getProjectList(userId).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.e("ProjectRepository", response[0].toString());


        });
        */
        // ------------------------------------


        //projectDao.save((Project) response[0].body());
    }

}
