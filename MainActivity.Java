package com.example.assignment2final;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String DEBUG_TAG = "MainActivity";

    float inpSaves,inpShot;
    float SavesAvg, ShotsAvg;
    String sSaves, sShots;


    float avgDiv;

    EditText editTextSaves, editTextShots;
    TextView  textViewSaves, textViewShots, editTextWL;

    MyDatabase db;


    RecyclerView recyclerView;
    //Arraylist Stuff
    private List<String> statsNum = new ArrayList<String>();
    private ArrayList<String> stats;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextShots = (EditText) findViewById(R.id.editTextShots);
        editTextSaves = (EditText) findViewById(R.id.editTextSaves);
        editTextWL = (EditText) findViewById(R.id.editTextWL);

        textViewShots = (TextView) findViewById(R.id.textViewShots);
        textViewSaves = (TextView) findViewById(R.id.textViewSaves);

        recyclerView = findViewById(R.id.recycler_view);

        db = new MyDatabase(this);

    }
    @Override
    public void onClick(View v) {
        Log.d(DEBUG_TAG,"onClick: called");
        //WL
        String WL = editTextWL.getText().toString();
        //shots
        String shots = editTextShots.getText().toString();
        sShots = shots; //String Val
        if (!"".equals(shots)) {
            inpShot = Integer.parseInt(shots); //Int Val
        }

        //Saves
        String save = editTextSaves.getText().toString();
        sSaves = save;//String Val
        if (!"".equals(save)) {
            inpSaves = Integer.parseInt(save);//Int Val
        }
        editTextSaves.setText("");


        //...
        long id = db.insertData(WL, shots, save);
        if (id < 0)
        {
            Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
        }
        //...
        editTextShots.setText("");
        editTextSaves.setText("");
        editTextWL.setText("");
        //...





        avgDiv = avgDiv + 1;

        //initRecyclerView();

    }

    private void initRecyclerView(){
        for (int i = 1; i <statsNum.size(); i++){
            stats.add(statsNum.get(i));
        }
        Log.d(DEBUG_TAG,"initRecyclerView: called");
        //puts each of the items in the lsit a functianltiy
        //(Context context, float saves, float shots){

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,stats,statsNum);
        //sets up recyclerview
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


}
