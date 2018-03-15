package com.example.admin.githubrepos.view.ui;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.admin.githubrepos.R;
import com.example.admin.githubrepos.service.repository.GitHubService;
import com.example.admin.githubrepos.service.repository.ProjectRepository;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements ProjectsListFragment.OnFragmentInteractionListener, ProjectFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // provideGithubService();


        ProjectsListFragment projectListFragment = new ProjectsListFragment();


        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, projectListFragment).commit();
        if (savedInstanceState == null) {
        }
    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
