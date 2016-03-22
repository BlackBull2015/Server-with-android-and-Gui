package com.example.Servlet_Connection;

//------------------------------------------------------------------------
//Intensive CPU service running its heavy duty task in an 
//AsyncTask object. Uses 'Message handling' for synchronization.


import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;

import java.util.Random;

public class MyService5Async extends Service {

	boolean running = true;

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}//i binder

	@Override
	public void onCreate() {
		super.onCreate();
		new GetNumbers().execute();
	}//on create

	public class GetNumbers extends AsyncTask <Integer, int[], int[]> {

		@Override
		protected int[] doInBackground(Integer... params) {
			Random rand = new Random();

			while(running) {
				int[] array = new int[5];
				array[0] = rand.nextInt(1000);
				array[1] = rand.nextInt(1000);
				array[2] = rand.nextInt(1000);
				array[3] = rand.nextInt(1000);
				array[4] = rand.nextInt(1000);

				publishProgress(array);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
				return null;
		}//do in background

		@Override
		protected void onProgressUpdate(int[]... values) {
			super.onProgressUpdate(values);

			Intent intentFilter5 = new Intent("NameOfIntent");
			intentFilter5.putExtra("array",values[0]);
			sendBroadcast(intentFilter5);
		}//on progress update

		@Override
		protected void onPostExecute(int[] ints) {
			super.onPostExecute(ints);

		}//on post execute

	}// get numbers async task

	@Override
	public void onDestroy() {
		super.onDestroy();
		running = false;
	}//on destroy
}//MyService5

