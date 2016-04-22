package com.example.Servlet_Connection;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.*;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Eric on 4/9/2016.
 */
public class accgraph extends Activity {
    SQLiteDatabase db;
    String PathToDb = "SSPSQL";
    ArrayList<String> id = new ArrayList<>();
    ArrayList<Integer> accx = new ArrayList<>();
    ArrayList<Integer> accy = new ArrayList<>();
    ArrayList<Integer> accz = new ArrayList<>();
    ArrayList<Integer> tmp = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.graph);
        db = this.openOrCreateDatabase(PathToDb, MODE_PRIVATE, null);


        Cursor cur =  db.rawQuery("SELECT * FROM dataRead", null);
        Log.e("main", "Read");
        cur.moveToFirst();
        Log.e("main", "Cursor moved");
        while(!cur.isLast()){
            id.add(cur.getString(0));
            accx.add(Integer.parseInt(cur.getString(1)));
            accy.add(Integer.parseInt(cur.getString(2)));
            accz.add(Integer.parseInt(cur.getString(3)));
            tmp.add(Integer.parseInt(cur.getString(7)));
           // txt.append("accX" + cur.getString(1) + " AccY " + cur.getString(2) + " AccZ " + cur.getString(3) + " magx " + cur.getString(4) + " magy " + cur.getString(5) + " magz " + cur.getString(6) + " tmp " + cur.getString(7) + "\n");
            cur.moveToNext();
        }


        drawGraph1();
        drawTemp();

    }//on create


    public void drawGraph1(){
        LineChart lineChart = (LineChart) findViewById(R.id.chart);
        // creating list of entry

        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<Entry> entries2 = new ArrayList<>();
        ArrayList<Entry> entries3 = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<String>();
        for(int i = 0; i < id.size(); i++){
            entries.add(new Entry(accx.get(i),i));
            entries2.add(new Entry(accy.get(i),i));
            entries3.add(new Entry(accz.get(i),i));
            labels.add(id.get(i));
        }

        LineDataSet dataset = new LineDataSet(entries, "X axis");
        LineDataSet dataset2 = new LineDataSet(entries2, "Y axis");
        LineDataSet dataset3 = new LineDataSet(entries3, "Z axis");

        dataset.setColor(Color.RED);
        dataset.setDrawCubic(true);
        dataset2.setColor(Color.BLUE);
        dataset2.setDrawCubic(true);
        dataset3.setColor(Color.GREEN);
        dataset3.setDrawCubic(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();

        dataSets.add(dataset);
        dataSets.add(dataset2);
        dataSets.add(dataset3);


        LineData data = new LineData(labels, dataSets);
        lineChart.setData(data);
        lineChart.setDescription("Accelerometer readings in XYZ axis");
    }


    public void drawTemp(){
        LineChart lineChart = (LineChart) findViewById(R.id.chart2);
        // creating list of entry
        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<String>();
        for(int i = 0; i < id.size(); i++){
            entries.add(new Entry(tmp.get(i),i));
            labels.add(id.get(i));
        }

        LineDataSet dataset = new LineDataSet(entries, "Temperature");

        dataset.setColor(Color.YELLOW);
        dataset.setDrawCircles(false);

        LineData data = new LineData(labels, dataset);
        lineChart.setData(data);
        lineChart.setDescription("Indicates temperature");
    }

}


