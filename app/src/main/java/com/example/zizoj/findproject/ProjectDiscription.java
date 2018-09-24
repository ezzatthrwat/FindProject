package com.example.zizoj.findproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zizoj.findproject.Model.Project;

public class ProjectDiscription extends AppCompatActivity {

    TextView studentname1 , studentname2,studentname3, studentname4,
            studentname5, studentname6,Discription , projectname , years ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_discription);

        studentname1 = (TextView)findViewById(R.id.Studentname1);
        studentname2= (TextView)findViewById(R.id.Studentname2);
        studentname3 = (TextView)findViewById(R.id.Studentname3);
        studentname4 = (TextView)findViewById(R.id.Studentname4);
        studentname5 = (TextView)findViewById(R.id.Studentname5);
        studentname6 = (TextView)findViewById(R.id.Studentname6);
        projectname = (TextView)findViewById(R.id.PROJectname);

        Discription = (TextView)findViewById(R.id.Discription);

        years = (TextView)findViewById(R.id.txtYears);





        Project project = (Project) getIntent().getSerializableExtra("projectData");


        if (project.getStudentName1() != " "){
            studentname1.setVisibility(View.VISIBLE);
            studentname1.setText(project.getStudentName1());
        }  if (project.getStudentName2() != " "){
            studentname2.setVisibility(View.VISIBLE);
            studentname2.setText(project.getStudentName2());
        } if (project.getStudentName3() != " "){
            studentname3.setVisibility(View.VISIBLE);
            studentname3.setText(project.getStudentName3());
        } if (project.getStudentName4() != " "){
            studentname4.setVisibility(View.VISIBLE);
            studentname4.setText(project.getStudentName4());
        } if (project.getStudentName5() != " "){
            studentname5.setVisibility(View.VISIBLE);
            studentname5.setText(project.getStudentName5());
        } if (project.getStudentName6() != " "){
            studentname6.setVisibility(View.VISIBLE);
            studentname6.setText(project.getStudentName6());
        }



        Toast.makeText(this, project.getProjectDescrip(), Toast.LENGTH_SHORT).show();

        projectname.setText(project.getProjectDescrip());
        Discription.setText(project.getDiscription());




        int backgroundColorForViewHolder=0;
        int toYearColor = Integer.valueOf(project.getYear());
        if (toYearColor == 2017) {
            backgroundColorForViewHolder = ColorUtiles
                    .getViewHolderBackgroundColorFromInstance(this, 2017);

        }else if (toYearColor == 2016){
            backgroundColorForViewHolder = ColorUtiles
                    .getViewHolderBackgroundColorFromInstance(this, 2016);

        }else if(toYearColor == 2015){
            backgroundColorForViewHolder = ColorUtiles
                    .getViewHolderBackgroundColorFromInstance(this, 2015);
        }else if(toYearColor == 2014){
            backgroundColorForViewHolder = ColorUtiles
                    .getViewHolderBackgroundColorFromInstance(this, 2014);
        }else if(toYearColor == 2013){
            backgroundColorForViewHolder = ColorUtiles
                    .getViewHolderBackgroundColorFromInstance(this, 2013);
        }

        years.setTextColor(backgroundColorForViewHolder);
        years.setText(project.getYear());

    }
}
