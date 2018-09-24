package com.example.zizoj.findproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zizoj.findproject.Database.DataBaseHelper;
import com.example.zizoj.findproject.Model.Project;
import com.example.zizoj.findproject.adapter.ListProjectAdapter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class needed_activity extends AppCompatActivity{

    Toolbar toolbar;
    RecyclerView recyclerView1 ;
    ListProjectAdapter adapter1 ;
    public List<Project> mProjectList = new ArrayList<>() ;
    TextView textEmpty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_needed_activity);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textEmpty = (TextView) findViewById(R.id.emtyimg);

        recyclerView1 = (RecyclerView)findViewById(R.id.Needed_Desktop_Projects) ;
        recyclerView1.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView1.setLayoutManager(layoutManager);

    }
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search_2, menu);

        MenuItem item = menu.findItem(R.id.action_filter_search_2);

        final android.widget.SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {

                if (!searchView.isIconified()){
                    searchView.setIconified(true);
                }
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                if (TextUtils.isEmpty(s)){
                    recyclerView1.setVisibility(View.GONE);
                    textEmpty.setVisibility(View.VISIBLE);
                }else if(!TextUtils.isEmpty(s)){
                    textEmpty.setVisibility(View.GONE);
                    viewDatabase1();
                   recyclerView1.setVisibility(View.VISIBLE);
                    final List<Project> filtermodellist = filter1(mProjectList,s);
                    adapter1.setFilter(filtermodellist);
                }
                return true;
            }
        });
        return true;
    }



    public void viewDatabase1(){

        DataBaseHelper myDbHelper = new DataBaseHelper(this);

        try {

            myDbHelper.createDataBase();
            myDbHelper.openDataBase();

            if (myDbHelper.copyDataBase()) {
                Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();
            }

            mProjectList = myDbHelper.selectAll("SELECT * FROM DesktopProjects","SELECT * FROM EmbededProjects",
                    "SELECT * FROM MobProjects","SELECT * FROM WebProjects");

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        } catch (SQLException sqle) {
            try {
                throw sqle;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


        adapter1 = new ListProjectAdapter(this, mProjectList);


        recyclerView1.setAdapter(adapter1);

    }




    private List<Project> filter1 (List<Project> p1 , String Query){

        Query = Query.toLowerCase();
        final List<Project> filteredModeList = new ArrayList<>();
        for(Project model:p1){

            final String  text = model.getDiscription().toLowerCase();
            if (text.contains(Query)){
                filteredModeList.add(model);
            }
        }
        return filteredModeList;

    }

}
