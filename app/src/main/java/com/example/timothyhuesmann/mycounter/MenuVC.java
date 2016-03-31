package com.example.timothyhuesmann.mycounter;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;

public class MenuVC extends ActionBarActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_vc);
        Intent intent = getIntent();
        String statistics = intent.getStringExtra("statsJSON");
        System.out.println(statistics);

    }

}
