package com.example.Servlet_Connection;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.*;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * Created by Eric on 12/1/2015.
 */
public class main extends Activity {

    BluetoothAdapter mBluetoothAdapter;
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
    BluetoothSocket runningsocket;
    OutputStream outStream;
    InputStream inStream;
    BluetoothDevice masterdevice;
    private static String address = "98:D3:33:80:6E:8F";
    private static final String TAG = "MyActivity";
    String baseUrl = "http://192.168.0.104:8080/MinMax";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();


    }//on create

    public void read (View v){
        new ReadAllData().execute("run");

    }//Read bluetooth

    public void connect (View v) throws InterruptedException {
        new backgroundAsyncTask().execute("run");
    }//start


    private class ReadAllData extends AsyncTask<String, String, String> {

        @Override
        protected void onProgressUpdate(String... value) {
            super.onProgressUpdate(value);

           // txtv.append(value[0]);
        }

        @Override
        protected String doInBackground(String... params) {
            String str = "";
            while(true){

                try {
                    //    Log.e(TAG, "In loop ");
                    int bytesAvailable = inStream.available();
                    //    Log.e(TAG, "Read length to read" + bytesAvailable);
                    if(bytesAvailable > 0) {
                        //Log.e("TAG", Integer.toString(bytesAvailable));
                        byte[] packetBytes = new byte[bytesAvailable];
                        inStream.read(packetBytes);
                        str += new String(packetBytes, StandardCharsets.UTF_8);
                        if(str.contains("end")) {
                            SendData(str);
                           // publishProgress(str);
                            //Log.e("TAG", str + "Thats in creation\n");
                            str = "";
                        }
                       // Log.e("TAG", str);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }// doInBackground

    }// backgroundAsyncTask


    public void SendData(String str){
        String jsonString;
        if(str.length()>10) {
            Log.e(TAG,"in async "+ str);
            String[] info = str.split(" ");
            JSONObject json = new JSONObject();
            try {
                json.put("x", info[2]);
                json.put("y", info[4]);
                json.put("z", info[6]);
                json.put("mx", info[9]);
                json.put("my", info[11]);
                json.put("mz", info[13]);
                json.put("tmp", info[15]);

                // String baseUrl = "http://192.168.1.101:8080/MinMax";
                Log.e(TAG,json.toString());
                jsonString = HttpUtils.urlContentPost(baseUrl, "data", json.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private class backgroundAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() { }
        @Override
        protected String doInBackground(String... params) {

            try {
                masterdevice = mBluetoothAdapter.getRemoteDevice(address);
                runningsocket = masterdevice.createRfcommSocketToServiceRecord(MY_UUID);
                runningsocket.connect();
                outStream = runningsocket.getOutputStream();
                inStream = runningsocket.getInputStream();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }// doInBackground

    }// backgroundAsyncTask




    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            String jsonString = "";

            try {
                jsonString = HttpUtils.urlContentPost(urls[0], "data", urls[1]);
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

           //     String num1 = jsonResult.getString("max");
          //      String num2 = jsonResult.getString("min");
          //      String sum = jsonResult.getString("sum");

                TextView view1 = (TextView)findViewById(R.id.textView2);

                view1.setText("done");
         //       view1.setText("Min is: "+num1);
        //        view1.append("\nMax is: "+num2);
        //        view1.append("\nAnd sum is: "+sum);


            } catch (JSONException e) {
                e.printStackTrace();
            }

            Toast.makeText(getBaseContext(), "Data Sent!", Toast.LENGTH_LONG).show();
        }//on post execute
    }//http async task

}
