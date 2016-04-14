package school.bio;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.TypedValue;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by Daniel on 28-Mar-16.
 */

public class Score extends Activity {

    ProgressBar progressBarScore;
    ObjectAnimator animation;
    int score, pbWidth;
    CountDownTimer timer;
    TextView scoreTW, scoreboard, slothstd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scoreboard);

        progressBarScore = (ProgressBar) findViewById(R.id.progressbarScore);

        scoreTW = (TextView) findViewById(R.id.scoreTW);
        scoreboard = (TextView) findViewById(R.id.scoreboard);
        slothstd = (TextView) findViewById(R.id.slothstd);

        Typeface canter = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/canter.otf");   // fonterino
        Typeface verano = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Verano.otf");   // -- || --

        scoreboard.setTextSize(TypedValue.COMPLEX_UNIT_SP, 80);
        scoreboard.setTypeface(canter);

        slothstd.setTypeface(canter);

        SharedPreferences preferences = getSharedPreferences("PlayerScore", MODE_PRIVATE);
        score = preferences.getInt("score",0);
        scoreTW.setText(String.valueOf(score));
        score *= 10000;
        scoreTW.setTypeface(verano);

        pbWidth = progressBarScore.getWidth();

        timer = new CountDownTimer(700, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                animation = ObjectAnimator.ofInt(progressBarScore, "progress", 0, score);
                animation.setDuration(3000); //in milliseconds bruv
                animation.setInterpolator(new DecelerateInterpolator());
                animation.start();
            }
        }.start();


    }

    @Override
    public void onBackPressed() {

        SharedPreferences preferences = getSharedPreferences("PlayerScore", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear().apply();

        Intent tomenu = new Intent(Score.this, MainActivity.class);
        finish();
        startActivity(tomenu);

        progressBarScore.clearAnimation();
        animation.cancel();

    }
}
