package com.example.dailyreportmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class addReport extends AppCompatActivity {

    DbHelper dbHelper;

    int id;

    TextView txtName;
    TextView txtRoll;

    EditText line;
    Switch sabki;
    Switch mazal;

    public int [] PSP = {
            1,
            150,
            261,
            387,
            520,
            644,
            756,
            905,
            1048,
            1208,
            1336,
            1488,
            1659,
            1817,
            2044,
            2231,
            2502,
            2694,
            2899,
            3244,
            3412,
            3595,
            3761,
            4127,
            4304,
            4554,
            4755,
            5160,
            5306,
            5748,
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_report);

        dbHelper=new DbHelper(this);

        txtName=findViewById(R.id.textViewName);
        txtRoll=findViewById(R.id.textViewRoll);

        line=findViewById(R.id.editTextLines);
        sabki=findViewById(R.id.switchSabki);
        mazal=findViewById(R.id.switchMazal);

        Intent intent=this.getIntent();
        txtName.setText(intent.getStringExtra("name").toString());
        //txtRoll.setText(intent.getStringExtra("rollNum").toString());
        id=intent.getIntExtra("id",0);


    }


    public void enterClicked(View view){
        if(TextUtils.isEmpty(line.getText().toString())){
            return;
        }
        int lines=Integer.valueOf(line.getText().toString());


        String sbki=String.valueOf(sabki.isChecked());
        boolean mnzl=mazal.isChecked();

        Report report=dbHelper.getReport(id+1);
        lines+=report.lines;

        int pra=getPara(lines);

        int mnzal=report.manzal;

        if(mnzl){
            mnzal=(report.manzal+1)%pra;
        }

        //E/SQLiteDatabase: Error inserting Sabak=0 Sabaki=false Manzal=0 StudentID=1 ParaNumber=0
        Report rep=new Report(id,pra,lines,sbki,mnzal);

        if(dbHelper.addReport(rep)){
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }
    }

    public int getPara(int l){
        for (int i=0;i<PSP.length;i++){
            if(l<=PSP[i]){
                return i;
            }
        }
        return 0;
    }

}