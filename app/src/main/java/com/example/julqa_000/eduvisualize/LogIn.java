package com.example.julqa_000.eduvisualize;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class LogIn extends AppCompatActivity {
    EditText etName,etPassword,etAdminSignUp;
    RadioButton radioButton1,radioButton2;
    Button btLogIn,btSignIn;
    String varradio="admin";
    String uname;
    String upass;

//final int flag=10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        etName=findViewById(R.id.logInName);
        etPassword=findViewById(R.id.logInPassword);
        radioButton1=findViewById(R.id.logInR1);
        radioButton2=findViewById(R.id.logInR2);
        btLogIn=findViewById(R.id.logInBtnLog);
        btSignIn=findViewById(R.id.logInBtnSign);
        ///////////////////
        etAdminSignUp=findViewById(R.id.signUpadminCode);
        ////////////////
      SharedPreferences  pref = getSharedPreferences("faizan", 0);
//        if (pref.getString("s1", "").equals("") & pref.getString("s2", "").equals(""))
//            setSharedPreferences();
//        } else  {

            autoLogin();

//        }
        ////shrd

        /////shrd

        final String radioValueAdmin=radioButton1.getText().toString();
        final String radioValueStudent=radioButton2.getText().toString();
        radioButton1.setChecked(true);
        //////shared

        ////sh

        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (radioButton1.isChecked()) {
                    radioButton2.setChecked(false);
                    varradio=radioValueAdmin;
                }
                else if(radioButton2.isChecked()){
                    radioButton1.isChecked();
                    radioButton2.setChecked(false);

                }
            }
        });
        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (radioButton2.isChecked()) {
                    radioButton1.setChecked(false);
                    varradio=radioValueStudent;

                }
                else if(radioButton1.isChecked()){
                    radioButton2.isChecked();
                    radioButton1.setSelected(false);
                }

            }
        });



    }
    public void setSharedPreferences() {
//        new ConnectToRemote().execute("http://zulkarnainfaizan.000webhostapp.com/ProMajor3D/AndroidPhp/login.php", etName.getText().toString(), etPassword.getText().toString(), varradio);

//        if(flag==12){
//            Toast.makeText(LogIn.this,flag,Toast.LENGTH_LONG).show();

//        EditText et = findViewById(R.id.logInName);
//        EditText et2 = findViewById(R.id.logInPassword);

            SharedPreferences pref = getSharedPreferences("faizan", MODE_PRIVATE);

            SharedPreferences.Editor editio = pref.edit();
            editio.putString("s1",uname);
            editio.putString("s2",upass);
            editio.commit();
//                    String m = pref.getString("s1", "");
//                    btn.setText(m);
            startActivity(new Intent(LogIn.this,Dashboard.class));
//        }
    }
    public void autoLogin() {
        SharedPreferences pref = getSharedPreferences("faizan", MODE_PRIVATE);

//        String pass = pref.getString("s2", "");
//        String name = pref.getString("s1", "");

//        EditText et = findViewById(R.id.logInName);
//        EditText et2 = findViewById(R.id.logInPassword);
               if (!pref.getString("s1", "").equals("") &&!pref.getString("s2", "").equals(""))
                   startActivity(new Intent(LogIn.this,Dashboard.class));

//        etName.setText(name);
//        etPassword.setText(pass);

    }
    public void onClick4() {
        startActivity(new Intent(LogIn.this, Dashboard.class));
    }
    public void logInMethod(View view){
         uname = etName.getText().toString();
        Toast.makeText(LogIn.this,uname,Toast.LENGTH_LONG).show();

        upass = etPassword.getText().toString();
        if (etName.getText().toString().isEmpty() ||etPassword.getText().toString().isEmpty()) {
            Toast.makeText(LogIn.this,"please enter required fields",Toast.LENGTH_LONG).show();
        }
        /////shar
        else
        new ConnectToRemote().execute("http://zulkarnainfaizan.000webhostapp.com/ProMajor3D/AndroidPhp/login.php", etName.getText().toString(), etPassword.getText().toString(), varradio);
//setSharedPreferences();

//            new ConnectToRemote().execute("http://zulkarnainfaizan.000webhostapp.com/ProMajor3D/AndroidPhp/login.php", etName.getText().toString(), etPassword.getText().toString(), varradio);
        //   Toast.makeText(LogIn.this,varradio+""+ etName.getText().toString(),Toast.LENGTH_LONG).show();

    }


        class ConnectToRemote extends AsyncTask<String, String, String> {

            @Override
            protected String doInBackground(String... params) {

                URL url;
                HttpURLConnection connection;
                StringBuilder builder = new StringBuilder();
                String readFromRemote;

                //Add the username to URL
                String urlToHit = null;
                try {
                    urlToHit = params[0] + "?username=" + URLEncoder.encode(params[1], "utf-8")+"&userpassword="+ URLEncoder.encode(params[2], "utf-8")+"&usertype="+URLEncoder.encode(params[3], "utf-8");
                } catch (UnsupportedEncodingException e) {
                    Log.e("USException", e.getMessage());
                }

                Log.e("urlToHit", urlToHit);




                try {
                    //pass the url to URL object
                    url = new URL(urlToHit);

                    //open the connection
                    connection = (HttpURLConnection) url.openConnection();

                    //Write the code to fetch response

                    //Get the inputStream
                    InputStream in = connection.getInputStream();

                    //Get the InputStreamReader object
                    InputStreamReader inReader = new InputStreamReader(in);

                    //Pass the InputStreamReader to BufferedReader
                    BufferedReader reader = new BufferedReader(inReader);

                    //Traverse through the result
                    while((readFromRemote = reader.readLine()) != null){
                        builder.append(readFromRemote);
                    }




//                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))
                } catch (MalformedURLException e) {
                    Log.e("ExceptionMUE", e.getMessage());
                } catch (IOException e) {
                    Log.e("ExceptionIO", e.getMessage());
                }


                return builder.toString();
            }

            @Override
            protected void onPostExecute(String s) {

                Log.e("responseFromServer", s);

                //DIsplay the data from remote URL on a TOAST

                if(s.equalsIgnoreCase("successfully logged in... as student")||s.equalsIgnoreCase("successfully logged in... as admin")){
                setSharedPreferences();
                }
                else if(s.equals("")) {
                    Toast.makeText(LogIn.this, "Temporary problem while connecting to server", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(LogIn.this, s, Toast.LENGTH_LONG).show();
            }

    }
    public  void signUpMethod(View view){
        if(varradio.equals("admin")){

            Intent intent=new Intent(LogIn.this,SignUp.class);
            intent.putExtra("radioValue",varradio);
            startActivity(intent);
        }
        else {
            Intent intent=new Intent(LogIn.this,SignUp.class);
            intent.putExtra("radioValue",varradio);
            startActivity(intent);

        }
    }

}
