package com.example.admin.githubrepos.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.admin.githubrepos.service.model.Project;
import com.example.admin.githubrepos.service.repository.ProjectRepository;

import java.util.List;

/**
 * Created by Admin on 2/17/2018.
 *
 * This is a ViewModel
 *
 *
 */

public class ProjectListViewModel extends AndroidViewModel {

    private final LiveData<List<Project>> projectListObservable;

    // The contructor matching super

    public ProjectListViewModel(ProjectRepository projectRepository) {
        super(projectRepository);

        // If any transformation is needed, this can be simply done by Transformations class ...

        // Below is a query to get the list of projects of the usre given in the parameter
        projectListObservable = projectRepository.getProjectList("Google");

    }


    // Below we are exposing the LiveData Projects query so that the UI can observe it
    // When this method is called, It will return the data of the query of the returned object
    public  LiveData<List<Project>> getProjectListObservable(){

        return  projectListObservable;
    }

}
