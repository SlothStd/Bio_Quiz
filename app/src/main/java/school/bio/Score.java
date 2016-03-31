package school.bio;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.CountDownTimer;
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
    int score,pbScore, pbWidth;
    String scoreValue;
    RelativeLayout relativeLayout;
    CountDownTimer timer;
    TextView scoreTW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scoreboard);

        progressBarScore = (ProgressBar) findViewById(R.id.progressbarScore);
        scoreTW = (TextView) findViewById(R.id.scoreTW);

        SharedPreferences preferences = getSharedPreferences("PlayerScore", MODE_PRIVATE);
        score = preferences.getInt("score",0);
        scoreTW.setText(String.valueOf(score));
        score *= 10000;

        Toast.makeText(Score.this, "The score at the end of the game is " + score, Toast.LENGTH_SHORT).show();

        pbWidth = progressBarScore.getWidth();

        timer = new CountDownTimer(700, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                animation = ObjectAnimator.ofInt(progressBarScore, "progress", 0, score);
                animation.setDuration(2000); //in milliseconds bruv
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
