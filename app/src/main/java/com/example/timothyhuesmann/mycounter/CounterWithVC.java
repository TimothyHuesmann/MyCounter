package com.example.timothyhuesmann.mycounter;

import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;
import android.widget.LinearLayout;
import com.firebase.client.Firebase;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;

public class CounterWithVC extends Activity
{
    String[] champNames = {"Aatrox", "Ahri", "Akali", "Alistar", "Amumu", "Anivia", "Annie", "Ashe", "Aurelion Sol", "Azir",
            "Bard", "Blitzcrank", "Brand", "Braum", "Caitlyn", "Cassiopeia", "Cho Gath", "Corki", "Darius",
            "Diana", "Dr Mundo", "Draven", "Ekko", "Elise", "Evelynn", "Ezreal", "Fiddlesticks", "Fiora",
            "Fizz", "Galio", "Gangplank", "Garen", "Gnar", "Gragas", "Graves", "Hecarim", "Heimerdinger", "Illaoi",
            "Irelia", "Janna", "Jarvan", "Jax", "Jayce", "Jhin", "Jinx", "Kalista", "Karma", "Karthus", "Kassadin"
            , "Katarina", "Kayle", "Kennen", "Kha'Zix", "Kindred", "Kog'Maw", "LeBlanc", "Lee Sin", "Leona", "Lissandra",
            "Lucian", "Lulu", "Lux", "Malphite", "Malzahar", "Maokai", "Master Yi", "Miss Fortune", "Mordekaiser",
            "Morgana", "Nami", "Nasus", "Nautilus", "Nidalee", "Nocturne", "Nunu", "Olaf", "Orianna", "Pantheon",
            "Poppy", "Quinn", "Rammus", "Renekton", "Rek'Sai", "Rengar", "Riven", "Rumble", "Ryze", "Sejuani",
            "Shaco", "Shen", "Shyvana", "Singed", "Sion", "Sivir", "Skarner", "Sona", "Soraka", "Swain", "Syndra", "Tahm Kench"
            , "Talon", "Taric", "Teemo", "Thresh", "Tristana", "Trundle", "Tryndamere", "Twisted Fate", "Twitch"
            , "Udyr", "Urgot", "Varus", "Vayne", "Veigar", "Vel'Koz", "Vi", "Viktor", "Vladimir", "Volibear"
            , "Warwick", "Wukong", "Xerath", "Xin Zhao", "Yasuo", "Yorick", "Zac", "Zed", "Ziggs", "Zilean", "Zyra"};
    Button[] theButtons = new Button[champNames.length];
    LinearLayout linear;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_with_vc);

        Firebase.setAndroidContext(this);
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

                }
            });

            linear.addView(theButtons[i]);
        }

    }
}


