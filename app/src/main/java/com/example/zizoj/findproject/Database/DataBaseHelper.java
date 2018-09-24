package com.example.zizoj.findproject.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.zizoj.findproject.Model.Project;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zizoj on 25/02/2018.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "projects.sqlite";
    private static String DB_PATH = "/data/data/com.example.zizoj.findproject/databases/";
    private final Context myContext;
    private SQLiteDatabase myDataBase;

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, 4);
        this.myContext = context;
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if(dbExist){
            //do nothing - database already exist
        }else{
            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase(){
        SQLiteDatabase checkDB = null;
        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        }catch(SQLiteException e){
            //database does't exist yet
        }

        if(checkDB != null){
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    public boolean copyDataBase() throws IOException{
        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;
        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);
        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }
        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

        return true;
    }

    public void openDataBase() throws SQLException {

        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

    }

    @Override
    public synchronized void close() {

        if(myDataBase != null)
            myDataBase.close();

        super.close();

    }



    Cursor cursor;
    public List<Project> getListProject(String name,String Sql ,String tablename) throws SQLException {
        Project project = null;
        List<Project> projectList = new ArrayList<Project>();

        if (name == null){
            cursor = myDataBase.rawQuery(Sql,null);
        }else if (name != null) {
            cursor = myDataBase.query(tablename,null,"projectname = ?",new String[]{name},null,null,null);
        }
        cursor.isAfterLast();
        while (cursor.moveToNext()){
            project = new Project(cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("studentname1")),
                    cursor.getString(cursor.getColumnIndex("studentname2")),
                    cursor.getString(cursor.getColumnIndex("studentname3")),
                    cursor.getString(cursor.getColumnIndex("studentname4")),
                    cursor.getString(cursor.getColumnIndex("studentname5")),
                    cursor.getString(cursor.getColumnIndex("studentname6")),
                    cursor.getString(cursor.getColumnIndex("discription")),
                    cursor.getString(cursor.getColumnIndex("projectname")),
                    cursor.getString(cursor.getColumnIndex("year")));
            projectList.add(project);
        }
        cursor.close();
        close();
        return projectList;
    }

    Cursor cursor2;
    public List<Project> selectAll(String Sql1 , String Sql2 , String Sql3 , String sql4) {

        List<Project> projectList1 = new ArrayList<Project>();
        List<Project> projectList2 = new ArrayList<Project>();
        List<Project> projectList3 = new ArrayList<Project>();
        List<Project> projectList4 = new ArrayList<Project>();

        Project project1 = null;
        Project project2 = null;
        Project project3 = null;
        Project project4 = null ;

        cursor2 = myDataBase.rawQuery(Sql1, null);

        cursor2.isAfterLast();
        while (cursor2.moveToNext()) {
            project1 = new Project(cursor2.getInt(cursor2.getColumnIndex("id")),
                    cursor2.getString(cursor2.getColumnIndex("studentname1")),
                    cursor2.getString(cursor2.getColumnIndex("studentname2")),
                    cursor2.getString(cursor2.getColumnIndex("studentname3")),
                    cursor2.getString(cursor2.getColumnIndex("studentname4")),
                    cursor2.getString(cursor2.getColumnIndex("studentname5")),
                    cursor2.getString(cursor2.getColumnIndex("studentname6")),
                    cursor2.getString(cursor2.getColumnIndex("discription")),
                    cursor2.getString(cursor2.getColumnIndex("projectname")),
                    cursor2.getString(cursor2.getColumnIndex("year")));
            projectList1.add(project1);
        }

        cursor2 = myDataBase.rawQuery(Sql2, null);
        cursor2.isAfterLast();
            while (cursor2.moveToNext()) {
                project1 = new Project(cursor2.getInt(cursor2.getColumnIndex("id")),
                        cursor2.getString(cursor2.getColumnIndex("studentname1")),
                        cursor2.getString(cursor2.getColumnIndex("studentname2")),
                        cursor2.getString(cursor2.getColumnIndex("studentname3")),
                        cursor2.getString(cursor2.getColumnIndex("studentname4")),
                        cursor2.getString(cursor2.getColumnIndex("studentname5")),
                        cursor2.getString(cursor2.getColumnIndex("studentname6")),
                        cursor2.getString(cursor2.getColumnIndex("discription")),
                        cursor2.getString(cursor2.getColumnIndex("projectname")),
                        cursor2.getString(cursor2.getColumnIndex("year")));

                projectList2.add(project1);
            }

        cursor2 = myDataBase.rawQuery(Sql3, null);
        cursor2.isAfterLast();
        while (cursor2.moveToNext()) {
            project1 = new Project(cursor2.getInt(cursor2.getColumnIndex("id")),
                    cursor2.getString(cursor2.getColumnIndex("studentname1")),
                    cursor2.getString(cursor2.getColumnIndex("studentname2")),
                    cursor2.getString(cursor2.getColumnIndex("studentname3")),
                    cursor2.getString(cursor2.getColumnIndex("studentname4")),
                    cursor2.getString(cursor2.getColumnIndex("studentname5")),
                    cursor2.getString(cursor2.getColumnIndex("studentname6")),
                    cursor2.getString(cursor2.getColumnIndex("discription")),
                    cursor2.getString(cursor2.getColumnIndex("projectname")),
                    cursor2.getString(cursor2.getColumnIndex("year")));

            projectList3.add(project1);
        }

        cursor2 = myDataBase.rawQuery(sql4, null);
        cursor2.isAfterLast();
        while (cursor2.moveToNext()) {
            project1 = new Project(cursor2.getInt(cursor2.getColumnIndex("id")),
                    cursor2.getString(cursor2.getColumnIndex("studentname1")),
                    cursor2.getString(cursor2.getColumnIndex("studentname2")),
                    cursor2.getString(cursor2.getColumnIndex("studentname3")),
                    cursor2.getString(cursor2.getColumnIndex("studentname4")),
                    cursor2.getString(cursor2.getColumnIndex("studentname5")),
                    cursor2.getString(cursor2.getColumnIndex("studentname6")),
                    cursor2.getString(cursor2.getColumnIndex("discription")),
                    cursor2.getString(cursor2.getColumnIndex("projectname")),
                    cursor2.getString(cursor2.getColumnIndex("year")));

            projectList4.add(project1);
        }

        List<Project>list1 =new ArrayList<Project>();
        List<Project>list2 =new ArrayList<Project>();
        List<Project>list3 =new ArrayList<Project>();
        List<Project>list4 =new ArrayList<Project>();



        projectList3.addAll(projectList4);
        projectList2.addAll(projectList3);
        projectList1.addAll(projectList2);
        list1.addAll(projectList1);


        return list1;


    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
