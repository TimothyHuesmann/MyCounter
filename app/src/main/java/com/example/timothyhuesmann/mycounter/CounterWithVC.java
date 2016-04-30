package com.example.timothyhuesmann.mycounter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
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
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;

public class CounterWithVC extends Activity
{
    String[] champNames = {"Aatrox", "Ahri", "Akali", "Alistar", "Amumu", "Anivia", "Annie", "Ashe", "AurelionSol", "Azir",
            "Bard", "Blitzcrank", "Brand", "Braum", "Caitlyn", "Cassiopeia", "ChoGath", "Corki", "Darius",
            "Diana", "Dr Mundo", "Draven", "Ekko", "Elise", "Evelynn", "Ezreal", "Fiddlesticks", "Fiora",
            "Fizz", "Galio", "Gangplank", "Garen", "Gnar", "Gragas", "Graves", "Hecarim", "Heimerdinger", "Illaoi",
            "Irelia", "Janna", "Jarvan", "Jax", "Jayce", "Jhin", "Jinx", "Kalista", "Karma", "Karthus", "Kassadin"
            , "Katarina", "Kayle", "Kennen", "KhaZix", "Kindred", "KogMaw", "LeBlanc", "Lee Sin", "Leona", "Lissandra",
            "Lucian", "Lulu", "Lux", "Malphite", "Malzahar", "Maokai", "Master Yi", "Miss Fortune", "Mordekaiser",
            "Morgana", "Nami", "Nasus", "Nautilus", "Nidalee", "Nocturne", "Nunu", "Olaf", "Orianna", "Pantheon",
            "Poppy", "Quinn", "Rammus", "Renekton", "Rek'Sai", "Rengar", "Riven", "Rumble", "Ryze", "Sejuani",
            "Shaco", "Shen", "Shyvana", "Singed", "Sion", "Sivir", "Skarner", "Sona", "Soraka", "Swain", "Syndra", "TahmKench"
            , "Talon", "Taric", "Teemo", "Thresh", "Tristana", "Trundle", "Tryndamere", "TwistedFate", "Twitch"
            , "Udyr", "Urgot", "Varus", "Vayne", "Veigar", "VelKoz", "Vi", "Viktor", "Vladimir", "Volibear"
            , "Warwick", "Wukong", "Xerath", "XinZhao", "Yasuo", "Yorick", "Zac", "Zed", "Ziggs", "Zilean", "Zyra"};
    Button[] theButtons = new Button[champNames.length];
    Button backButton;
    LinearLayout linear;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_with_vc);
        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");
        Firebase.setAndroidContext(this);
        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        linear = (LinearLayout) findViewById(R.id.withLinear);
        LayoutParams param = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
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
                    String dataUrl = "https://mycounter-app.herokuapp.com/counterWith/" + username + '/' + button.getText().toString();
                    System.out.println(dataUrl);
                    // Instantiate the RequestQueue.
                    RequestQueue queue = Volley.newRequestQueue(CounterWithVC.this);


// Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, dataUrl,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    System.out.println(response);
                                    if (response.equals("No Data Available")) {
                                        new AlertDialog.Builder(CounterWithVC.this)
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

            linear.addView(theButtons[i]);
        }

    }
}


