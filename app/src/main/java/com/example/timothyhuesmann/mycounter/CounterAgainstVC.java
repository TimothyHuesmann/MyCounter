package com.example.timothyhuesmann.mycounter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.client.Firebase;

public class CounterAgainstVC extends Activity
{
    String[] champNames = {"Aatrox", "Ahri", "Akali", "Alistar", "Amumu", "Anivia", "Annie", "Ashe", "AurelionSol", "Azir",
                            "Bard", "Blitzcrank", "Brand", "Braum", "Caitlyn", "Cassiopeia", "ChoGath", "Corki", "Darius",
                            "Diana", "DrMundo", "Draven", "Ekko", "Elise", "Evelynn", "Ezreal", "Fiddlesticks", "Fiora",
                            "Fizz", "Galio", "Gangplank", "Garen", "Gnar", "Gragas", "Graves", "Hecarim", "Heimerdinger", "Illaoi",
                            "Irelia", "Janna", "Jarvan", "Jax", "Jayce", "Jhin", "Jinx", "Kalista", "Karma", "Karthus", "Kassadin"
                            , "Katarina", "Kayle", "Kennen", "Kha'Zix", "Kindred", "KogMaw", "LeBlanc", "Lee Sin", "Leona", "Lissandra",
                            "Lucian", "Lulu", "Lux", "Malphite", "Malzahar", "Maokai", "Master Yi", "MissFortune", "Mordekaiser",
                            "Morgana", "Nami", "Nasus", "Nautilus", "Nidalee", "Nocturne", "Nunu", "Olaf", "Orianna", "Pantheon",
                            "Poppy", "Quinn", "Rammus", "Renekton", "RekSai", "Rengar", "Riven", "Rumble", "Ryze", "Sejuani",
                            "Shaco", "Shen", "Shyvana", "Singed", "Sion", "Sivir", "Skarner", "Sona", "Soraka", "Swain", "Syndra", "Tahm Kench"
                            , "Talon", "Taric", "Teemo", "Thresh", "Tristana", "Trundle", "Tryndamere", "TwistedFate", "Twitch"
                            , "Udyr", "Urgot", "Varus", "Vayne", "Veigar", "Vel'Koz", "Vi", "Viktor", "Vladimir", "Volibear"
                            , "Warwick", "Wukong", "Xerath", "XinZhao", "Yasuo", "Yorick", "Zac", "Zed", "Ziggs", "Zilean", "Zyra"};
    Button[] theButtons = new Button[champNames.length];
    LinearLayout layout;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_against_vc);
        Firebase.setAndroidContext(this);
        Intent intent = getIntent();
        backButton = (Button) findViewById(R.id.withBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final String username = intent.getStringExtra("username");
        layout = (LinearLayout) findViewById(R.id.againstLinear);
        ViewGroup.LayoutParams param = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < theButtons.length; i++) {
            theButtons[i] = new Button(this);
            theButtons[i].setHeight(50);
            theButtons[i].setWidth(250);
            theButtons[i].setTag(champNames[i]);
            theButtons[i].setText(champNames[i]);
            theButtons[i].setLayoutParams(param);
            theButtons[i].setId(i);
            theButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("Trying to get Data");
                    final Button button = (Button) v;
                    String dataUrl = "https://mycounter-app.herokuapp.com/counterAgainst/" + username + '/' + button.getText().toString();
                    // Instantiate the RequestQueue.
                    RequestQueue queue = Volley.newRequestQueue(CounterAgainstVC.this);


// Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, dataUrl,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if (response.equals("No Data Available")) {
                                        new AlertDialog.Builder(CounterAgainstVC.this)
                                                .setMessage("No Data Currently Available")
                                                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {

                                                    }
                                                })
                                                .show();
                                    } else {
                                        Intent intent = new Intent(getApplicationContext(), DetailVC.class);
                                        intent.putExtra("choice", "With");
                                        intent.putExtra("champ", button.getText().toString());
                                        intent.putExtra("data", response);
                                        startActivity(intent);
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System.out.println("Error:" + error);
                        }
                    });
                    stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    queue.add(stringRequest);

                }
            });

            layout.addView(theButtons[i]);
        }
    }



}
