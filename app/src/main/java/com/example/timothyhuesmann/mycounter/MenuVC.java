package com.example.timothyhuesmann.mycounter;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MenuVC extends ActionBarActivity
{
    Button logoutButton;
    TextView welcomeTF;
    Button counterAgainstButton;
    Button counterWithButton;
    Button statsButton;
    String username;
    String statistics;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_vc);
        Intent intent = getIntent();
        statistics = intent.getStringExtra("stats");
        username = intent.getStringExtra("username");
        System.out.println(statistics);
        welcomeTF = (TextView) findViewById(R.id.welcomeTextField);
        welcomeTF.setText("Welcome, " + username);
        addListenerOnButton();
    }

    public void addListenerOnButton()
    {
        logoutButton = (Button) findViewById(R.id.logoutButton);
        counterAgainstButton = (Button) findViewById(R.id.counterAgainstButton);
        counterWithButton = (Button) findViewById(R.id.counterWithButton);
        statsButton = (Button) findViewById(R.id.statsButton);

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

        statsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(), PersonalStatsVC.class);
                i.putExtra("stats", statistics);
                startActivity(i);
            }
        });
    }

}
