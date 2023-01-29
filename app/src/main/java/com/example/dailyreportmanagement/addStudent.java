package com.example.dailyreportmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class addStudent extends AppCompatActivity {

    EditText name;
    EditText fname;
    EditText rollNum;

    DbHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        name=findViewById(R.id.editTextName);
        fname=findViewById(R.id.editTextFatherName);
        rollNum=findViewById(R.id.editTextRollNumber);
        dbhelper=new DbHelper(this);
    }

    public void addButtonClicked(View view){
        if(TextUtils.isEmpty(name.getText().toString())){
            name.setError("You don't enter Name");
            return;
        }if(TextUtils.isEmpty(fname.getText().toString())){
            name.setError("You don't enter Father Name");
            return;
        }if(TextUtils.isEmpty(rollNum.getText().toString())){
            name.setError("You don't enter Roll #");
            return;
        }

        Student student=new Student(name.getText().toString(),fname.getText().toString(),rollNum.getText().toString());
        if(dbhelper.addStudent(student)){
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this,"Student not added.!",Toast.LENGTH_SHORT).show();
        }

    }



}