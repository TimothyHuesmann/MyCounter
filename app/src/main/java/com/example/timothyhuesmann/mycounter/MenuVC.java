package com.example.timothyhuesmann.mycounter;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import org.json.JSONObject;

public class MenuVC extends ActionBarActivity
{
    Button logoutButton;
    TextView welcomeTF;
    Button counterAgainstButton;
    Button counterWithButton;
    String username;
    String statistics;
    TextView killsTF;
    TextView assistsTF;
    TextView turretsTF;
    TextView winsTF;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_vc);
        Intent intent = getIntent();
        statistics = intent.getStringExtra("stats");
        username = intent.getStringExtra("username");
        System.out.println(statistics);
        killsTF = (TextView) findViewById(R.id.killsTF);
        assistsTF = (TextView) findViewById(R.id.assistsTF);
        turretsTF = (TextView) findViewById(R.id.turretsTF);
        winsTF = (TextView) findViewById(R.id.winsTF);
        try {
            JSONObject stats = new JSONObject(statistics);
            JSONObject statistics = stats.getJSONObject("stats");
            String kills = statistics.getString("kills");
            String wins = statistics.getString("wins");
            String turrets = statistics.getString("totalTurrets");
            String assists = statistics.getString("totalAssists");
            String killsLabel = "Kills: " + kills;
            String winsLabel = "Wins: " + wins;
            String turretsLabel = "Turrets Destroyed: " + turrets;
            String assistsLabel = "Assists: " + assists;
            killsTF.setText(killsLabel);
            winsTF.setText(winsLabel);
            assistsTF.setText(assistsLabel);
            turretsTF.setText(turretsLabel);
        }
        catch(Exception e)
        {
            System.out.println("WHY");
        }
        welcomeTF = (TextView) findViewById(R.id.welcomeTextField);
        String temp = "Welcome, " + username;
        welcomeTF.setText(temp);
        addListenerOnButton();
    }

    public void addListenerOnButton()
    {
        logoutButton = (Button) findViewById(R.id.logoutButton);
        counterAgainstButton = (Button) findViewById(R.id.counterAgainstButton);
        counterWithButton = (Button) findViewById(R.id.counterWithButton);

        logoutButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(), MainVC.class);
                startActivity(i);
            }
        });

        counterAgainstButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Instantiate the RequestQueue.
                Intent i = new Intent(getApplicationContext(), CounterAgainstVC.class);
                i.putExtra("username", username);
                startActivity(i);
            }
        });

        counterWithButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(), CounterWithVC.class);
                i.putExtra("username", username);
                startActivity(i);
            }
        });

    }

}
