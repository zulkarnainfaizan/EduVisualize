package com.example.julqa_000.eduvisualize;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
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

public class SyallabusStatus extends AppCompatActivity {
    EditText  jG,nG,mG,wG,aG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syallabus_status);
        jG=findViewById(R.id.jG);

        nG=findViewById(R.id.nG);

        mG =findViewById(R.id.mG);

        wG =findViewById(R.id.wG);

        aG=findViewById(R.id.aG);

    }
    public void insertMethod(View view){
        if ( jG.getText().toString().isEmpty()||
                nG.getText().toString().isEmpty()||mG.getText().toString().isEmpty()
                ||wG.getText().toString().isEmpty()||aG.getText().toString().isEmpty()) {
            Toast.makeText(SyallabusStatus.this, "please enter required fields", Toast.LENGTH_LONG).show();
        } else


            new SyallabusStatus.ConnectToRemote().execute("http://zulkarnainfaizan.000webhostapp.com/ProMajor3D/AndroidPhp/insertSyllabus.php",
                    jG.getText().toString(),nG.getText().toString()
                    ,wG.getText().toString(),mG.getText().toString(),aG.getText().toString()
                    );
    }
    public void syllVisualizeMethod(View view){
        setContentView(R.layout.activity_chart_registration);

        WebView webView=findViewById(R.id.webViewId);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);

        webView.setWebChromeClient(new WebChromeClient());
//        setContentView(webView);
        webView.loadUrl("http://zulkarnainfaizan.000webhostapp.com/ProMajor3D/php/retrieveSyllabus.php");
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
                urlToHit = params[0] +"?javaStats="+ URLEncoder.encode(params[1],"utf-8")+"&netStats="+ URLEncoder.encode(params[2],"utf-8")+
                        "&wireStats="+ URLEncoder.encode(params[3],"utf-8")+"&mulStats="+ URLEncoder.encode(params[4],"utf-8")
                        +"&aiStats="+ URLEncoder.encode(params[5],"utf-8");
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

            if (s.equalsIgnoreCase("member registered successfully")) {
//                startActivity(new Intent(Registeration.this, Dashboard.class));
                Toast.makeText(SyallabusStatus.this, s, Toast.LENGTH_LONG).show();

            } else
                Toast.makeText(SyallabusStatus.this, s, Toast.LENGTH_LONG).show();
        }

    }
}
