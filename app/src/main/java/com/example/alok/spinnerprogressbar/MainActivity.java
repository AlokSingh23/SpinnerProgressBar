package com.example.alok.spinnerprogressbar;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ArrayAdapter<CharSequence> adapter;
    Spinner spinner;
    private ProgressBar progressbar;
    private int protressStatus=0;
    private TextView textView;
    private Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner) findViewById(R.id.spinner);


    adapter =ArrayAdapter.createFromResource(this,R.array.spinner1,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        progressbar=(ProgressBar)findViewById(R.id.progress1);
        progressbar.setVisibility(View.INVISIBLE);
}


    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        // TextView mytext=(TextView)view;
        // Toast.makeText(this,"you selected"+mytext.getText(),Toast.LENGTH_LONG).show();
        protressStatus = 0;
        if (i > 0){
            i--;
            progressbar.setVisibility(View.VISIBLE);
        textView = (TextView) findViewById(R.id.text1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (protressStatus < 100) {
                    protressStatus += 1;
                    if(protressStatus>=100)
                        progressbar.setVisibility(View.INVISIBLE);
                        //progressbar.setVisibility(View.GONE);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressbar.setProgress(protressStatus);
                            textView.setText(protressStatus + "/" + progressbar.getMax());
                        }
                    });
                   try {
                        Thread.sleep(500);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }//progressbar.setVisibility(View.GONE);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



}
