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

public class ProgressReport extends AppCompatActivity {
    EditText stdName,stdEnroll, jG,jA,nG,nA,mG,mA,wG,wA,aG,aA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_report);
        stdName=findViewById(R.id.attName);
        stdEnroll=findViewById(R.id.attEnroll);
        jG=findViewById(R.id.jG);
        jA=findViewById(R.id.jA);
        nG=findViewById(R.id.nG);
        nA=findViewById(R.id.nA);
        mG =findViewById(R.id.mG);
        mA=findViewById(R.id.mA);
        wG =findViewById(R.id.wG);
        wA=findViewById(R.id.wA);
        aG=findViewById(R.id.aG);
        aA=findViewById(R.id.aA);

    }
    public void insertMethod(View view){
        if (stdName.getText().toString().isEmpty() || stdEnroll.getText().toString().isEmpty() || jG.getText().toString().isEmpty()||
                jA.getText().toString().isEmpty()||nG.getText().toString().isEmpty()||nA.getText().toString().isEmpty()||mG.getText().toString().isEmpty()
                ||mA.getText().toString().isEmpty()||wG.getText().toString().isEmpty()||wA.getText().toString().isEmpty()||aG.getText().toString().isEmpty()
                ||aA.getText().toString().isEmpty()) {
            Toast.makeText(ProgressReport.this, "please enter required fields", Toast.LENGTH_LONG).show();
        } else


            new ProgressReport.ConnectToRemote().execute("http://zulkarnainfaizan.000webhostapp.com/ProMajor3D/AndroidPhp/insertProgress.php",
                    stdName.getText().toString(),
                    stdEnroll.getText().toString(),jG.getText().toString(),jA.getText().toString(),nG.getText().toString(),nA.getText().toString()
                    ,mG.getText().toString(),mA.getText().toString(),wG.getText().toString(),wA.getText().toString(),aG.getText().toString(),
                    aA.getText().toString());

    }
    public  void  visualizeMethod(View view){
        setContentView(R.layout.activity_chart_registration);

        WebView webView=findViewById(R.id.webViewId);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);

        webView.setWebChromeClient(new WebChromeClient());
//        setContentView(webView);
        webView.loadUrl("http://zulkarnainfaizan.000webhostapp.com/ProMajor3D/php/retrieveProgress.php");

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
                urlToHit = params[0] + "?std_name=" + URLEncoder.encode(params[1], "utf-8") +
                        "&std_roll="+ URLEncoder.encode(params[2],"utf-8")+"&jG="+ URLEncoder.encode(params[3],"utf-8")+"&jA="
                        + URLEncoder.encode(params[4],"utf-8")+"&nG="+ URLEncoder.encode(params[5],"utf-8")+"&nA="+ URLEncoder.encode(params[6],"utf-8")
                        +"&mG="+ URLEncoder.encode(params[7],"utf-8")+"&mA="+ URLEncoder.encode(params[8],"utf-8")+"&wG="+ URLEncoder.encode(params[9],"utf-8")
                        +"&wA="+ URLEncoder.encode(params[10],"utf-8")+"&aG="+ URLEncoder.encode(params[11],"utf-8")+"&aA="+ URLEncoder.encode(params[12],"utf-8");
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
                Toast.makeText(ProgressReport.this, s, Toast.LENGTH_LONG).show();

            } else
                Toast.makeText(ProgressReport.this, s, Toast.LENGTH_LONG).show();
        }

    }
}
