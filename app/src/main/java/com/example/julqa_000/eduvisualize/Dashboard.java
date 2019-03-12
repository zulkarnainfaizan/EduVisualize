package com.example.julqa_000.eduvisualize;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }
    public void registrationMethod(View view){
        startActivity(new Intent(Dashboard.this,Registeration.class));
    }
    public void attendanceMethod(View view){
        startActivity(new Intent(Dashboard.this,Attendance.class));
    }
    public void progressReportMethod(View view){
        startActivity(new Intent(Dashboard.this,ProgressReport.class));
    }
    public void syllabusStatusMethod(View v){
        startActivity(new Intent(Dashboard.this,SyallabusStatus.class));}
    public void studentDetailsMethod(View view){
        startActivity(new Intent(Dashboard.this,StudentModule.class));
    }
    public  void dashboardMethod(View view){
        startActivity(new Intent(Dashboard.this,Dashboard.class));
    }

}
