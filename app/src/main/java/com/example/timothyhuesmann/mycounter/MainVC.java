package com.example.timothyhuesmann.mycounter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Spinner;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.DefaultRetryPolicy;
import android.content.Intent;


import com.firebase.client.Firebase;



public class MainVC extends ActionBarActivity
{

    Button enterButton_MainVC;
    EditText usernameTF_MainVC;
    Spinner regionSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_vc);
        Firebase.setAndroidContext(this);
        regionSpinner = (Spinner) findViewById(R.id.regionSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.regions, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        regionSpinner.setAdapter(adapter);
        addListenerOnButton();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_vc, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addListenerOnButton()
    {
        enterButton_MainVC = (Button) findViewById(R.id.enterButton_MainVC);
        usernameTF_MainVC = (EditText) findViewById(R.id.usernameTF_MainVC);

        enterButton_MainVC.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (usernameTF_MainVC.getText().toString().isEmpty())
                {
                    new AlertDialog.Builder(MainVC.this)
                            .setMessage("Error: Username not Entered")
                            .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();
                }
                else
                {
                    String dataUrl = "https://mycounter-app.herokuapp.com/login/" + usernameTF_MainVC.getText().toString() + '/' + regionSpinner.getSelectedItem().toString();
                    // Instantiate the RequestQueue.
                            RequestQueue queue = Volley.newRequestQueue(MainVC.this);



// Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, dataUrl,
                            new Response.Listener<String>()
                            {
                                @Override
                                public void onResponse(String response)
                                {
                                    Intent intent = new Intent(getApplicationContext(), MenuVC.class);
                                    if(response != null) {
                                        intent.putExtra("username", usernameTF_MainVC.getText().toString());
                                        intent.putExtra("stats", response);
                                        startActivity(intent);
                                    }
                                    else{
                                        new AlertDialog.Builder(MainVC.this)
                                            .setMessage("Error Retrieving Information, Please confirm you have a LoL account and try again.")
                                                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {

                                                    }
                                                });
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                           System.out.println("Error:"+ error);
                        }
                    });
                    stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
// Add the request to the RequestQueue.
                    queue.add(stringRequest);
                }
            }
        });
    }



}
