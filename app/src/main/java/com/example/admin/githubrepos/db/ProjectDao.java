package com.example.admin.githubrepos.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.admin.githubrepos.service.model.Project;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;


/**
 * Created by Admin on 15.3.18.
 */
@Dao
public interface ProjectDao {
    @Insert(onConflict = REPLACE)
    void save(Project project);

    @Query("SELECT * FROM project")
    LiveData<List<Project>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProject(Project project);

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Project> allProjects);

}
