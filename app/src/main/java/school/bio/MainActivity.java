package school.bio;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button bezstavovce = (Button) findViewById(R.id.dankovButton);
        final Button organizmy = (Button) findViewById(R.id.buttonTohoDruheho);
        final Button test = (Button) findViewById(R.id.test);


        bezstavovce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    bezstavovce.setBackground(getDrawable(R.drawable.menu_buttons_green));
                } else {
                    bezstavovce.setBackgroundDrawable(getResources().getDrawable(R.drawable.menu_buttons_green));
                }
                Intent chrobakyVoVode = new Intent(MainActivity.this, Bezstavovce.class);
                startActivity(chrobakyVoVode);
            }
        });

        organizmy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    organizmy.setBackground(getDrawable(R.drawable.menu_buttons_green));
                } else {
                    organizmy.setBackgroundDrawable(getResources().getDrawable(R.drawable.menu_buttons_green));
                }
                Intent organizmy = new Intent(MainActivity.this, Organizmy.class);
                startActivity(organizmy);
            }
        });


        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer score = 9;

                SharedPreferences preferences = getSharedPreferences("PlayerScore", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putInt("score", score).apply();

                Intent scoreboard = new Intent(MainActivity.this, Score.class);
                startActivity(scoreboard);
            }
        });

    }
}
