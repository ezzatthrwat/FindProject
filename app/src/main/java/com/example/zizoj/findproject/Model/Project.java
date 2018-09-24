package com.example.zizoj.findproject.Model;

import java.io.Serializable;

public class Project implements Serializable {

    private int id;
    private String studentName1 ,studentName2, studentName3,studentName4,
            studentName5,studentName6,projectDescrip , discription ,year;

    public Project(int id, String studentName1,String studentName2 ,String studentName3,
                   String studentName4,String studentName5,
                   String studentName6,String Discription, String projectDescrip , String year) {
        this.id = id;

        this.studentName1 = studentName1;
        this.studentName2 = studentName2;
        this.studentName3 = studentName3;
        this.studentName4 = studentName4;
        this.studentName5 = studentName5;
        this.studentName6 = studentName6;

        this.discription = Discription;

        this.projectDescrip = projectDescrip;
        this.year = year;
    }

    public Project(){

    }

    public int getId() {
        return id;
    }



    public  String getStudentName1() {
        return studentName1;
    }
    public String getStudentName2() {
        return studentName2;
    }
    public String getStudentName3() {
        return studentName3;
    }
    public String getStudentName4() {
        return studentName4;
    }
    public String getStudentName5() {
        return studentName5;
    }
    public String getStudentName6() {
        return studentName6;
    }
    public String getDiscription() {
        return discription;
    }
    public String getProjectDescrip() {
        return projectDescrip;
    }

    public String getYear() {
        return year;
    }

}
