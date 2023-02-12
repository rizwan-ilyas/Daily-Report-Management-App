package com.example.dailyreportmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class RecyclerViewActivity extends AppCompatActivity {

    Intent intent;
    DbHelper dbHelper;

    RecyclerView rcview;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layout;

    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        rcview=findViewById(R.id.recyclerView);
        layout=new LinearLayoutManager(RecyclerViewActivity.this);

        rcview.setLayoutManager(layout);




        intent=getIntent();
        dbHelper=new DbHelper(this);

        //adapter=new recyclerViewAdapter(dbHelper.getStudentsData());
        String identifier=intent.getStringExtra("identifier");
        id=intent.getLongExtra("id",0);
        loadData(identifier);
        rcview.setAdapter(adapter);


    }

    public void loadData(String Identifier){
        if(Identifier.equals("students")){
            //dbHelper.getStudents();
            adapter=new studentsAdapter(dbHelper.getStudentsData());
        }else if(Identifier.equals("reports")){
            adapter=new reportViewAdapter(dbHelper.getReportsData(id));
        }


    }


}