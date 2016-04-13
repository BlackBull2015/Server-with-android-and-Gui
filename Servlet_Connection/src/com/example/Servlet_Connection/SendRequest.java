package com.example.Servlet_Connection;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Eric on 4/9/2016.
 */
public class SendRequest extends Activity {
    JSONObject json;
    Intent inte;
    //String baseUrl = "http://10.12.2.169:8080/VerifyPhone";
    String baseUrl = "http://192.168.1.16:8080/VerifyPhone";
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.login);
            json  = new JSONObject();
            inte = new Intent(this,DisplayDb.class);
        }//on create

    public void Submit (View v) {
            EditText login = (EditText) findViewById(R.id.editText2);
            EditText pass = (EditText) findViewById(R.id.editText3);
            try {
                json.put("login", login.getText().toString());
                json.put("pass", pass.getText().toString());
                new HttpAsyncTask().execute("run");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String jsonString = "";
            try {
                jsonString = HttpUtils.urlContentPost(baseUrl, "login", json.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonString;
        }//do in background
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

            TextView txtv = (TextView)findViewById(R.id.textView);
            Log.e("Send", result);

            if(result.equalsIgnoreCase("true")){
                startActivity(inte);
            }else if(result.equalsIgnoreCase("false")){
                txtv.setText("No user found");
            }else{
                txtv.setText("Connection error");
            }
        }//on post execute
    }//http async task


}


