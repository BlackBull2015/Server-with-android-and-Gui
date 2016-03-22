package com.example.Servlet_Connection;

import android.app.Activity;
import android.content.*;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Eric on 12/1/2015.
 */
public class main extends Activity {
    int[] numbers = new int[5];

    Intent intentCallService5;
    BroadcastReceiver receiver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        intentCallService5 = new Intent(this, MyService5Async.class);

        IntentFilter filter5 = new IntentFilter("NameOfIntent");

        receiver = new MyEmbeddedBroadcastReceiver();
        registerReceiver(receiver, filter5);





    }//on create


    Button asd = (Button)findViewById(R.id.button3);


    public void sendNums (View v){

            SharedPreferences myprefs = getSharedPreferences("my_preferences", Activity.MODE_PRIVATE);
        JSONObject json = new JSONObject();
        try {
            json.put("one",myprefs.getInt("num1",0));
            json.put("two",myprefs.getInt("num2",0));
            json.put("three",myprefs.getInt("num3",0));
            json.put("four",myprefs.getInt("num4",0));
            json.put("five",myprefs.getInt("num5",0));
            String baseUrl = "http://192.168.0.101:8080/MinMax";
            // String baseUrl = "http://192.168.1.101:8080/MinMax";
            new HttpAsyncTask().execute(baseUrl, json.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }//send nums

    public void start (View v) throws InterruptedException {
        startService(intentCallService5);

       Button btn = (Button)findViewById(R.id.button3);
       btn.setClickable(true);

    }//start

    public void stop (View v) throws InterruptedException {
        stopService(intentCallService5);

        SharedPreferences myprefs = getSharedPreferences("my_preferences", Activity.MODE_PRIVATE);


        SharedPreferences.Editor edit = myprefs.edit();
        edit.putInt("num1",numbers[0]);
        edit.putInt("num2",numbers[1]);
        edit.putInt("num3",numbers[2]);
        edit.putInt("num4",numbers[3]);
        edit.putInt("num5",numbers[4]);
        edit.commit();

        Button bt1 = (Button)findViewById(R.id.button);
        bt1.setClickable(true);

    }//stop

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            String jsonString = "";

            try {
                jsonString = HttpUtils.urlContentPost(urls[0], "num", urls[1]);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(jsonString);
            return jsonString;
        }//do in background
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
           JSONObject jsonResult = null;
            try {
                jsonResult = new JSONObject(result);

                String num1 = jsonResult.getString("max");
                String num2 = jsonResult.getString("min");
                String sum = jsonResult.getString("sum");

                TextView view1 = (TextView)findViewById(R.id.textView2);

                view1.setText("Min is: "+num1);
                view1.append("\nMax is: "+num2);
                view1.append("\nAnd sum is: "+sum);


            } catch (JSONException e) {
                e.printStackTrace();
            }

            Toast.makeText(getBaseContext(), "Data Sent!", Toast.LENGTH_LONG).show();
        }//on post execute
    }//http async task

    public class MyEmbeddedBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            TextView txt1 = (TextView)findViewById(R.id.textView);

            if (intent.getAction().equals("NameOfIntent")) {

                numbers = intent.getIntArrayExtra("array");
                txt1.setText("given numbers are: "+numbers[0]+", "+numbers[1]+", "+numbers[2]+", "+numbers[3]+", "+numbers[4]);

            }
        }//onReceive
    }// MyEmbeddedBroadcastReceiver

}
