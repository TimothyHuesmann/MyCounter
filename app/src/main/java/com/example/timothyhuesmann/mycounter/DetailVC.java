package com.example.timothyhuesmann.mycounter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailVC extends Activity {

    TextView champLabel;
    TextView option1;
    TextView option2;
    TextView option3;
    TextView choiceTF;
    Button detailBackButton;
    String champ1Name;
    String champ1Record;
    String champ2Name;
    String champ2Record;
    String champ3Name;
    String champ3Record;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_vc);
        Intent intent  = getIntent();
        final String data = intent.getStringExtra("data");
        final String choice = intent.getStringExtra("choice");
        final String champ = intent.getStringExtra("champ");
        choiceTF = (TextView) findViewById(R.id.whichLabel);
        option1 = (TextView) findViewById(R.id.option1);
        option2 = (TextView) findViewById(R.id.option2);
        option3 = (TextView) findViewById(R.id.option3);
        detailBackButton = (Button) findViewById(R.id.detailBackButton);
        detailBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        champLabel = (TextView) findViewById(R.id.champLabel);
        String tempLabel = "Playing " + choice + ":";
        choiceTF.setText(tempLabel);
        try {
            JSONObject dataJSON = new JSONObject(data);
            champLabel.setText(champ);
            JSONObject champ1 = dataJSON.getJSONObject("0");
            String champ1Name = champ1.getString("name");
            String champ1Record = champ1.getString("record");
            System.out.println(champ1);
            System.out.println(champ1Name);
            System.out.println(champ1Record);
            if(!dataJSON.isNull("1")) {
                JSONObject champ2 = dataJSON.getJSONObject("1");
                champ2Name = champ2.getString("name");
                champ2Record = champ2.getString("record");
            }
            if(!dataJSON.isNull("2")) {
                JSONObject champ3 = dataJSON.getJSONObject("2");
                champ3Name = champ3.getString("name");
                champ3Record = champ3.getString("record");
            }
            String tempLabel1 = "1: " + champ1Name + "   ->   " + champ1Record + " Win(s)";
            option1.setText(tempLabel1);
            if(champ2Name != null) {
                String tempLabel2 = "2: " + champ2Name + "   ->   " + champ2Record + " Win(s)";
                option2.setText(tempLabel2);
            }
            else {
                option2.setText("");
            }
            if(champ3Name != null) {
                String tempLabel3 = "3: " + champ3Name + "   ->   " + champ3Record + " Win(s)";
                option3.setText(tempLabel3);
            }
            else{
                option3.setText("");
            }
        }
        catch(Exception e)
        {
            System.out.println("WHY");
        }


    }

}
