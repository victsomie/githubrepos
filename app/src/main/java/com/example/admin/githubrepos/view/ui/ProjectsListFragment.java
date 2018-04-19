package com.example.admin.githubrepos.view.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.admin.githubrepos.R;
import com.example.admin.githubrepos.service.model.Project;
import com.example.admin.githubrepos.service.repository.GitHubService;
import com.example.admin.githubrepos.service.repository.ProjectRepository;
import com.example.admin.githubrepos.viewmodel.ProjectListViewModel;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProjectsListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ProjectsListFragment extends Fragment {

    private MaterialDialog progressDialog;

    private static final String TAG = ProjectsListFragment.class.getSimpleName();
    ProjectListViewModel allProjects;
    ViewModelProvider.Factory viewModelFactory;


    private OnFragmentInteractionListener mListener;

    public ProjectsListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_project_list, container, false);

        // DUMMY SHOWING OF DATA ON TEXTVIEW
        final TextView tvShowAllData = view.findViewById(R.id.loading_projects);

        progressDialog = new MaterialDialog.Builder(getContext())
                .progress(true, 0)
                .progressIndeterminateStyle(true)
                .cancelable(false)
                .build();

        // GETTING THE DATA FROM THE VIEWMODEL
        // Please note how we are fetching our ViewModel from the ViewProviders.of().get(TheViewModel)

        // ProjectRepository myRepo = new ProjectRepository(provideGithubService());
        //ProjectRepository myRepo = new ProjectRepository(provideGithubService());
        //myRepo.getProjectList("Google");

        // =======++++==++=+==+=+=+++++++++++========
        /*
        (myRepo.getProjectList("Google")).observe(this, new Observer<List<Project>>() {
            @Override
            public void onChanged(@Nullable List<Project> projects) {

                // (projects) parameter comes with all the project of the specifiew Github user

                int counter = 1;
                tvShowAllData.setText("A LIST OD ALL THE PROJECTS \n\n");
                for (Project project : projects ){
                    tvShowAllData.append(counter + " : " + project.full_name + "\n\n");

                }

            }
        });

        */
        // =======++++==++=+==+=+=+++++++++++========


        allProjects = ViewModelProviders.of(getActivity()).get(ProjectListViewModel.class);

        Log.e("ProjectListFragment", String.valueOf(allProjects.getProjectListObservable()));


        // allProjects.getProjectListObservable()
        //allProjects = ViewModelProviders.of((FragmentActivity) getContext()).get(ProjectListViewModel.class);


        // ============================

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
            }
        });

        progressDialog.show();
        allProjects.getProjectListObservable().

                observe(this, new Observer<List<Project>>() {
                    @Override
                    public void onChanged(@Nullable List<Project> projects) {
                        //Log.e(TAG, projects + " --- That the status---");
                        assert projects != null;


                        Log.e(TAG, "Size outside run is ==  " + projects.size());
                        tvShowAllData.setText("A LIST OF ALL THE PROJECTS \n\n");
                        // (projects) parameter comes with all the project of the specifiew Github user


                        final int[] counter = {1};

                        for (Project project : projects) {

                            /*
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                */
                            //Log.e(TAG, "Size inside run is ==  " + projects.size());

                            // Log some stuff
                            //Log.e("ProjectListFragment", project.name + " ------");

                            Log.e(TAG, counter[0] + " : " + project.name + "\n\n");
                            tvShowAllData.append(counter[0] + " : " + project.name + "\n\n");
                            counter[0]++;

                        }
                        progressDialog.dismiss();

                    }
                });
        // ==========================================
        return view;
    }

// TODO: Rename method, update argument and hook method into UI event

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);

    }


    GitHubService provideGithubService() {
        return new Retrofit.Builder()
                .baseUrl(GitHubService.HTTPS_API_GITHUB_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GitHubService.class);
    }
}
