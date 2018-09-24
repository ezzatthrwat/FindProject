package com.example.zizoj.findproject.Tabs;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zizoj.findproject.Database.DataBaseHelper;
import com.example.zizoj.findproject.Model.Project;
import com.example.zizoj.findproject.R;
import com.example.zizoj.findproject.adapter.ListProjectAdapter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zizoj on 25/02/2018.
 */

public class ItTab extends android.support.v4.app.Fragment {

//  ListView listViewProjects;
    ListProjectAdapter adapter;
    public List<Project> mProjectList = null;
    Context context;

    String name = null ;

    RecyclerView recyclerView ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.it, container, false);

        recyclerView = (RecyclerView)rootView.findViewById(R.id.ItprojectView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

           viewDatabase(name);




        return rootView;
    }


    public List<Project> filterNames (List<Project> p1 , String Query){

        Query = Query.toLowerCase();
        final List<Project> filteredModleList = new ArrayList<>();
        for(Project model:p1){

            final String  text = model.getProjectDescrip().toLowerCase();
            if (text.contains(Query)){
                filteredModleList.add(model);
            }
        }
        adapter.setFilter(filteredModleList);
        return filteredModleList;

    }



    public void viewDatabase(String proName){

        DataBaseHelper myDbHelper = new DataBaseHelper(this.getContext());

        try {

            myDbHelper.createDataBase();
            myDbHelper.openDataBase();

            if (myDbHelper.copyDataBase()) {
                Toast.makeText(getContext(), "done", Toast.LENGTH_SHORT).show();
            }



            mProjectList = myDbHelper.getListProject(proName,"SELECT * FROM DesktopProjects"
                    ,"DesktopProjects");


        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        } catch (SQLException sqle) {
            try {
                throw sqle;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }






        adapter = new ListProjectAdapter(this.getContext(), mProjectList);

        recyclerView.setAdapter(adapter);

    }





    public void it(String searchname){
        final List<Project> filtermodellist2 = filterNames(mProjectList,searchname);

        adapter.setFilter(filtermodellist2);
    }




}
