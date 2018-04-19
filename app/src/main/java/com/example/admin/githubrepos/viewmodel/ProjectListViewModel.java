package com.example.admin.githubrepos.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.admin.githubrepos.db.AppDatabase;
import com.example.admin.githubrepos.db.ProjectDao;
import com.example.admin.githubrepos.service.model.Project;
import com.example.admin.githubrepos.service.repository.GitHubService;
import com.example.admin.githubrepos.service.repository.ProjectRepository;
// import com.example.admin.githubrepos.service.repository.ProjectRepository;

import java.util.List;
import java.util.concurrent.Executor;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Admin on 2/17/2018.
 * <p>
 * This is a ViewModel
 */

public class ProjectListViewModel extends AndroidViewModel {

    private final LiveData<List<Project>> projectListObservable;
   private AppDatabase appDatabase;
    private ProjectDao projectDao;

    private ProjectRepository projectRepository;

    // The contructor matching super

    public ProjectListViewModel(Application application) {
        super(application);

        // If any transformation is needed, this can be simply done by Transformations class ...

        // Below is a query to get the list of projects of the user given in the parameter
        // projectListObservable = projectRepository.getProjectList("Google");


        appDatabase = AppDatabase.getDatabase(this.getApplication());
        /*
        try {
            projectDao = ProjectDao.class.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        */



        projectRepository = new ProjectRepository(provideGithubService(), appDatabase.projectDao());


        projectListObservable = projectRepository.getProjectList("Jerry-goodboy");
        // projectListObservable = projectRepository.getProjectList("leebyron");
        //projectListObservable = projectRepository.getProjectList("google");
        //projectListObservable = appDatabase.projectDao().getAll();
    }


    // Below we are exposing the LiveData Projects query so that the UI can observe it
    // When this method is called, It will return the data of the query of the returned object
    public LiveData<List<Project>> getProjectListObservable() {

        return projectListObservable;
    }


    private GitHubService provideGithubService() {
        return new Retrofit.Builder()
                .baseUrl(GitHubService.HTTPS_API_GITHUB_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GitHubService.class);
    }
}
