package com.example.timothyhuesmann.mycounter;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class MenuVC extends ActionBarActivity
{
    Button logoutButton;
    TextView welcomeTF;
    Button counterAgainstButton;
    Button counterWithButton;
    Button statsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_vc);
        Intent intent = getIntent();
        String statistics = intent.getStringExtra("statsJSON");
        String username = intent.getStringExtra("username");
        System.out.println(statistics);
        welcomeTF = (TextView) findViewById(R.id.welcomeTextField);
        welcomeTF.setText("Welcome," + username);
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

            }
        });

        counterWithButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        statsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
    }

}
