package com.example.Servlet_Connection;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Eric on 4/7/2016.
 */
public class DisplayDb extends Activity {
    SQLiteDatabase db;
    String PathToDb = "SSPSQL";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.readdb);
        db = this.openOrCreateDatabase(PathToDb, MODE_PRIVATE, null);

    }//on create

    @Override
    public void onDestroy(){
        super.onDestroy();
    }//on destroy


    public void graph (View v){
        Intent inte;
        inte = new Intent(this,accgraph.class);

        startActivity(inte);

    }

    public void graph2 (View v){
        Intent inte;
        inte = new Intent(this,maggraph.class);

        startActivity(inte);

    }


    public void Update (View v){

        TextView txt = (TextView)findViewById(R.id.textViewdb);
        Log.e("main", "prepare to read database");
        Cursor cur =  db.rawQuery("SELECT * FROM dataRead", null);
        Log.e("main", "Read");
        cur.moveToFirst();
        Log.e("main", "Cursor moved");
        txt.setText("");
        while(!cur.isLast()){
            txt.append("ID" + cur.getString(0)+ "accX"+cur.getString(1)+" AccY "+cur.getString(2)+" AccZ "+cur.getString(3)+" magx "+cur.getString(4)+" magy "+cur.getString(5)+" magz "+cur.getString(6)+" tmp "+cur.getString(7)+"\n");
            cur.moveToNext();
            Log.e("main", "Reading next");

        }
        Log.e("main", "Done");
    }

}
