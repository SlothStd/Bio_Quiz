package school.bio;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Typeface typeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/canter.otf");

        final TextView quiz = (TextView) findViewById(R.id.quiz);
        final Button bezstavovce = (Button) findViewById(R.id.dankovButton);
        final Button organizmy = (Button) findViewById(R.id.buttonTohoDruheho);

        bezstavovce.setTypeface(typeface);
        bezstavovce.setTextSize(30);
        organizmy.setTypeface(typeface);
        organizmy.setTextSize(30);
        quiz.setTypeface(typeface);
        quiz.setTextSize(50);


        bezstavovce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent chrobakyVoVode = new Intent(MainActivity.this, Bezstavovce.class);
                finish();
                startActivity(chrobakyVoVode);

            }
        });

        organizmy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent organizmy = new Intent(MainActivity.this, Organizmy.class);
                finish();
                startActivity(organizmy);

            }
        });



    }
}
