 package com.example.julqa_000.eduvisualize;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

 public class StudentModule extends AppCompatActivity {
//     static TextView  dataDb;
       ListView listLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_module);
//        setContentView(R.layout.activity_chart_registration);
//        dataDb=findViewById(R.id.dataDB);
        listLog = findViewById(R.id.listViewInserted);

//        WebView webView=findViewById(R.id.webViewId);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setBuiltInZoomControls(true);
//
//        webView.setWebChromeClient(new WebChromeClient());
////        setContentView(webView);
//        webView.loadUrl("http://zulkarnainfaizan.000webhostapp.com/ProMajor3D/php/mainphp.php");
        new StudentModule.ConnectToRemote().execute("http://zulkarnainfaizan.000webhostapp.com/ProMajor3D/AndroidPhp/mainphp.php");


    }

     class ConnectToRemote extends AsyncTask<String, String, String> {
        String data="";
         HashMap<String,String> arrayLog=new HashMap<>();
         @Override
         protected String doInBackground(String... params) {

             URL url;
             HttpURLConnection connection;
             StringBuilder builder = new StringBuilder();
             String readFromRemote;

             //Add the username to URL
             String urlToHit = null;
//             try {
                 urlToHit = params[0];
//             } catch (UnsupportedEncodingException e) {
//                 Log.e("USException", e.getMessage());
//             }

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
                     data=data+readFromRemote;
                 }



//                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))
             } catch (MalformedURLException e) {
                 Log.e("ExceptionMUE", e.getMessage());
             } catch (IOException e) {
                 Log.e("ExceptionIO", e.getMessage());
//             } catch (JSONException e) {
//                 e.printStackTrace();
             }


             return data;
         }

         @Override
         protected void onPostExecute(String s) {

//             Log.e("responseFromServer", s);
//
//             //DIsplay the data from remote URL on a TOAST
//
//             if (s.equalsIgnoreCase("member registered successfully")) {
////                startActivity(new Intent(StudentModule.this, Dashboard.class));
//                 Toast.makeText(StudentModule.this, s, Toast.LENGTH_LONG).show();

//             } else
             try {
                 JSONArray JA = new JSONArray(s);
                 for (int i = 0; i < JA.length(); i++) {
                     JSONObject jo = (JSONObject) JA.get(i);

                     String nameFromTable = jo.get("studentname").toString();
                     String rollFromTable = jo.get("enroll").toString();
                     arrayLog.put("Name: " + nameFromTable, "Enroll: " + rollFromTable);
//                     Toast.makeText(StudentModule.this, nameFromTable, Toast.LENGTH_LONG).show();

//                     Toast.makeText(StudentModule.this, nameFromTable, Toast.LENGTH_LONG).show();

                 }

         } catch (JSONException e) {
                 e.printStackTrace();

             }
         final    List<HashMap<String,String>> listItems=new ArrayList<>();
          final   SimpleAdapter adapter = new SimpleAdapter( StudentModule.this,listItems, R.layout.extra_list_sub_item_view,new String[]{"first","second"},
                     new int[]{R.id.etv1,R.id.etv2});

             Iterator it= arrayLog.entrySet().iterator();
             while (it.hasNext()){
                 HashMap<String,String> resultMap= new HashMap<>();
                 Map.Entry pair=(Map.Entry)it.next();
                 resultMap.put("first",pair.getKey().toString());
                 resultMap.put("second",pair.getValue().toString());
                 listItems.add(resultMap);
             }
             listLog.setAdapter(adapter);
             listLog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                 @Override
                 public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                     ////////////////////tricky part/////////////
                     HashMap<String,String> my = (HashMap<String,String>)adapter.getItem(i);
                     //  my.remove(my);
                     String myyy= my.get("second");
                     //////////////////lastpoint////////
                    String mm=myyy.substring(7);

                     Toast.makeText(StudentModule.this, mm, Toast.LENGTH_LONG).show();
                     setContentView(R.layout.activity_chart_registration);
                     ProgressBar bar=findViewById(R.id.progressBar2);

                     WebView webView=findViewById(R.id.webViewId);
                     webView.getSettings().setJavaScriptEnabled(true);
                     webView.getSettings().setBuiltInZoomControls(true);

                     webView.setWebChromeClient(new WebChromeClient());

//        setContentView(webView);
//                     webView.loadUrl("http://zulkarnainfaizan.000webhostapp.com/ProMajor3D/php/retrievereg.php");
//                     new StudentModule.ConnectToRemote().execute("http://zulkarnainfaizan.000webhostapp.com/ProMajor3D/php/collection.php" +"?enroll=" + URLEncoder.encode(params[0], "utf-8"));

                     webView.loadUrl("http://zulkarnainfaizan.000webhostapp.com/ProMajor3D/php/collective.php?enroll="+mm);
bar.setVisibility(View.INVISIBLE);



//                     adapter.notifyDataSetChanged();

                     Log.e("status", "item clicked...");

                     //Show a Toast displaying the item has been clicked
//                     Toast.makeText(DeleteDatabase.this, myyy + " Item deleted", Toast.LENGTH_SHORT).show();
                 }
             });

         }

     }
}
