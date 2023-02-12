package com.example.dailyreportmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class DbHelper extends SQLiteOpenHelper {

    public static final String dbName="Students.db";
    public static final String studentTableName="Students";
    public static final String reportTableName="Report";

    public static final String columnID="id";
    public static final String columnName="name";
    public static final String columnFatherName="fathername";
    public static final String columnRollNumber="RollNumber";

    public static final String ReportColumnID="id";
    public static final String ReportColumnStudentID="StudentID";
    public static final String ReportColumnDate="Date";
    public static final String ReportColumnPara="ParaNumber";
    public static final String ReportColumnSabak="Sabak";
    public static final String ReportColumnSabaki="Sabaki";
    public static final String ReportColumnManzal="Manzal";


    public DbHelper(@Nullable Context context) {
        super(context, dbName, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String studentTable="CREATE TABLE IF NOT EXISTS "+ studentTableName + " ("
                + columnID + " integer primary key autoincrement, "
                + columnName + " varchar(50), "
                + columnFatherName + " varchar(50), "
                + columnRollNumber + " varchar(6)"
                + ")";

        String reportTable = "CREATE TABLE IF NOT EXISTS " + reportTableName + "("
                + ReportColumnID + " integer primary key autoincrement, "
                + ReportColumnStudentID + " integer not null, "//Foreign key refrences " + studentTableName + "("+ columnID +"),"
                + ReportColumnDate + " varchar(10) not null, "
                + ReportColumnPara + " integer, "
                + ReportColumnSabak + " integer, "
                + ReportColumnSabaki + " varchar(10), "
                + ReportColumnManzal + " integer, "
                + "CONSTRAINT FK_StudentID FOREIGN KEY ("+ReportColumnStudentID+")" + " REFERENCES "+ studentTableName +"(" + columnID +"))";

        String setIndex = "Create Index idx_stdID"
                + " ON " + reportTableName + " (" + ReportColumnStudentID + ") ";

        sqLiteDatabase.execSQL(studentTable);
        sqLiteDatabase.execSQL(reportTable);
        sqLiteDatabase.execSQL(setIndex);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //String delStudent="drop table if exists "+ studentTableName;
        String delReport="drop table if exists "+ reportTableName;
        //sqLiteDatabase.execSQL(delStudent);
        sqLiteDatabase.execSQL(delReport);
        onCreate(sqLiteDatabase);
    }

    public boolean addStudent(Student s){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(columnName,s.name);
        values.put(columnFatherName,s.father_name);
        values.put(columnRollNumber,s.rollnum);

        if(db.insert(studentTableName,null,values)!=-1){
            db.close();
            return true;
        }db.close();
        return false;

    }

    public List<String> getStudents(){
        String query="select * from "+studentTableName + ";";
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        //List<Student> list=null;
        List<String> list=new ArrayList<String>();
        if(cursor.moveToFirst()){
            do{
                //list.add(new Student(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
                list.add(cursor.getString(1));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    public List<Student> getStudentsData(){
        String query="select * from "+studentTableName + ";";
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        //List<Student> list=null;
        List<Student> list=new ArrayList<Student>();
        if(cursor.moveToFirst()){
            do{
                list.add(new Student(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
                //list.add(cursor.getString(1));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    public boolean addReport(Report report){
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-mm-yyyy");
        String date=new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(ReportColumnStudentID,report.StdID);
        values.put(ReportColumnDate,date);
        values.put(ReportColumnPara,report.para);
        values.put(ReportColumnSabak,report.lines);
        values.put(ReportColumnSabaki,report.sabki);
        values.put(ReportColumnManzal,report.manzal);

        if(db.insert(reportTableName,null,values)!=-1){
            return true;
        }else{
            return false;
        }
    }

    public Report getReport(long id){
        SQLiteDatabase db=this.getWritableDatabase();
        String sql="select * "
                + "from "+reportTableName
                + " where "+ ReportColumnStudentID
                + "="+id + " Order by "+ ReportColumnID +" desc limit 1;";
        //db.execSQL(sql);
        Cursor cursor= db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            Report r=new Report(cursor.getInt(0),cursor.getInt(1),cursor.getInt(3),cursor.getInt(4),cursor.getString(5),cursor.getInt(6));
            db.close();
            return r;
        }else{
            db.close();
            Report report=new Report(0,0,0,"false",0);
            return report;
        }
    }

    public List<Report> getReportsData(long id){
        SQLiteDatabase db=this.getReadableDatabase();
        String sql="select * "
                + "from "+reportTableName
                + " where "+ ReportColumnStudentID
                + "="+id + " Order by "+ ReportColumnID;
        //db.execSQL(sql);
        Cursor cursor= db.rawQuery(sql,null);
        List<Report> list=new ArrayList<Report>();
        if(cursor.moveToFirst()){
            do {
                Report r = new Report(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4), cursor.getString(5), cursor.getInt(6));
                list.add(r);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }





}
