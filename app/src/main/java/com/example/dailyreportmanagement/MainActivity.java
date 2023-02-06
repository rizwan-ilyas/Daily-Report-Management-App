package com.example.dailyreportmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    DbHelper dbHelper;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper=new DbHelper(this);
        spinner=findViewById(R.id.spinner);

        loadStudents();
    }

    public void loadStudents(){
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,dbHelper.getStudents());
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        //System.out.println(dbHelper.getStudents().get(0));

    }

    public void addClicked(View view){
        Intent intent=new Intent(this,addStudent.class);
        startActivity(intent);
    }
    public void deleteClicked(View view){

    }
    public void addReportClicked(View view){


        Intent intent=new Intent(this,addReport.class);
        intent.putExtra("id",(int)spinner.getSelectedItemId());
        intent.putExtra("name",spinner.getSelectedItem().toString());
        startActivity(intent);
    }
    public void getReportClicked(View view){

    }






}