package com.example.julqa_000.eduvisualize;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class SignUp extends AppCompatActivity {
    EditText name, password, confirmPassword, adminCode;
    Button signUp;
    String getvalue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name = findViewById(R.id.signUpName);
        password = findViewById(R.id.signUpPassword);
        confirmPassword = findViewById(R.id.signUpConfirmPassword);
        adminCode = findViewById(R.id.signUpadminCode);
        signUp = findViewById(R.id.signUpBtnSign);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
         getvalue = bundle.getString("radioValue");
        Toast.makeText(SignUp.this, getvalue, Toast.LENGTH_LONG).show();

        if (getvalue.equals("student")) {
            adminCode.setVisibility(View.INVISIBLE);
        }

    }
    public void signUpMethod(View view) {
        if (getvalue.equals("student")) {
            if (name.getText().toString().isEmpty() || password.getText().toString().isEmpty() || confirmPassword.getText().toString().isEmpty()) {
                Toast.makeText(SignUp.this, "please enter required fields", Toast.LENGTH_LONG).show();
            } else


                new SignUp.ConnectToRemote().execute("http://zulkarnainfaizan.000webhostapp.com/ProMajor3D/AndroidPhp/signup.php", name.getText().toString(), password.getText().toString());
        }else{
            if (name.getText().toString().isEmpty() || password.getText().toString().isEmpty() || confirmPassword.getText().toString().isEmpty()||adminCode.getText().toString().isEmpty()) {
                Toast.makeText(SignUp.this, "please enter required fields", Toast.LENGTH_LONG).show();
            } else


                new SignUp.ConnectToRemote().execute("http://zulkarnainfaizan.000webhostapp.com/ProMajor3D/AndroidPhp/signup.php", name.getText().toString(), password.getText().toString());

        }
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
                urlToHit = params[0] + "?userName=" + URLEncoder.encode(params[1], "utf-8") + "&userPass="+ URLEncoder.encode(params[2],"utf-8");
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
                while ((readFromRemote = reader.readLine()) != null) {
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

            if (s.equalsIgnoreCase("successfully logged in... as student") || s.equalsIgnoreCase("successfully logged in... as admin")) {
                startActivity(new Intent(SignUp.this, Dashboard.class));
            }                else if(s.equals("")) {
                Toast.makeText(SignUp.this, "Temporary problem while connecting to server", Toast.LENGTH_LONG).show();
            }

            else
                Toast.makeText(SignUp.this, s, Toast.LENGTH_LONG).show();
        }

    }
}
