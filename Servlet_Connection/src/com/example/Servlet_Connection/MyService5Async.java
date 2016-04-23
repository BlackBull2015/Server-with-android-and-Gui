package com.example.Servlet_Connection;

//------------------------------------------------------------------------
//Intensive CPU service running its heavy duty task in an 
//AsyncTask object. Uses 'Message handling' for synchronization.


import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.UUID;

public class MyService5Async extends Service {

	//Bluetooth settings
	BluetoothAdapter mBluetoothAdapter;
	private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
	BluetoothSocket runningsocket;
	OutputStream outStream;
	InputStream inStream;
	BluetoothDevice masterdevice;
	private static String address = "98:D3:33:80:6E:8F";
	boolean running = true;
	String baseUrl = "http://192.168.0.101:8080/MinMax";
	//String baseUrl = "http://10.12.2.169:8080/MinMax";
	SQLiteDatabase db;
	String PathToDb = "SSPSQL";

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}//i binder

	@Override
	public void onCreate() {
		super.onCreate();
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		db = this.openOrCreateDatabase(PathToDb, MODE_PRIVATE, null);
		db.execSQL("create table IF NOT EXISTS dataRead(\n" +
				"  ID integer PRIMARY KEY autoincrement,\n" +
				"  accx text,\n" +
				"  accy text,\n" +
				"  accz text,\n" +
				"  magx text,\n" +
				"  magy text,\n" +
				"  magz text,\n" +
				"  tmp text,\n" +
				"  time text\n" +
				"  );");
		new Bluetooth().execute();
	}//on createl

	public class Bluetooth extends AsyncTask <String, String, String> {

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

			String str = "";
			while(running){

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
				return null;
		}//do in background

		@Override
		protected void onProgressUpdate(String ... values) {
			super.onProgressUpdate(values);

			//Toast.makeText(getBaseContext(), values[0], Toast.LENGTH_LONG).show();
		}//on progress update

		@Override
		protected void onPostExecute(String ints) {
			super.onPostExecute(ints);

		}//on post execute

	}// get numbers async task

	@Override
	public void onDestroy() {
		super.onDestroy();
		try {
			inStream.close();
			outStream.close();
			runningsocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		running = false;
	}//on destroy

	public void SendData(String str){
		String jsonString;
		if(str.length()>10) {
			//Log.e(TAG, "in async " + str);
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
				Log.e("Service",json.toString());
				jsonString = HttpUtils.urlContentPost(baseUrl, "data", json.toString());
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Log.e("Service", "Json sent");


			db.beginTransaction();
			Log.e("Service", "Starting DB");
			Log.e("Service", str);

			try {
				Log.e("Service", "To execute database");
				db.execSQL("insert into dataRead (accx,accy,accz,magx,magy,magz,tmp) values ('" + info[2] + "','" + info[4] + "','" + info[6] + "','" + info[9] + "','" + info[11] + "','" + info[13] + "','" + info[15] + "' )");
				Log.e("Service", "Db executed");
				db.setTransactionSuccessful();
				Log.e("Service", "Succesfull");

			}catch (SQLiteException e2){
				Log.e("Service", "Crashed");
				//Toast.makeText(getBaseContext(), "Not working", Toast.LENGTH_LONG).show();
			}finally {
				db.endTransaction();
				Log.e("Service", "Done");
				//Toast.makeText(getBaseContext(), "DataBase Done", Toast.LENGTH_LONG).show();
			}

		}
	}

}//MyService5

