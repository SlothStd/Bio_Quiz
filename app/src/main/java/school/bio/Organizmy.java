package school.bio;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Daniel on 22-Mar-16.
 */
public class Organizmy extends Activity {

    Button first, second, third, fourth;
    ArrayList<Integer> carLogos;
    ArrayList<String> carNames, randCarNames;
    ArrayList<Button> buttonAL;
    ProgressBar time;
    ObjectAnimator animation;
    Integer chosenImage, chosenPos, score;
    String chosenName, checkString, chosenString;
    ImageView image;
    CountDownTimer cdTimer, answerTimer;
    String firstS, secondS, thirdS, fourthS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);

        first = (Button) findViewById(R.id.btn1);
        second = (Button) findViewById(R.id.btn2);
        third = (Button) findViewById(R.id.btn3);
        fourth = (Button) findViewById(R.id.btn4);

        image = (ImageView) findViewById(R.id.imageView);
        time = (ProgressBar) findViewById(R.id.timePB);

        buttonAL = new ArrayList<>();
        buttonAL.add(first);
        buttonAL.add(second);
        buttonAL.add(third);
        buttonAL.add(fourth);

        carLogos = new ArrayList<>();
        carLogos.add(R.drawable.bezstavovce_acropora_millepora);
        carLogos.add(R.drawable.bezstavovce_dazdovka);
        carLogos.add(R.drawable.bezstavovce_meduza_vlasata);
        carLogos.add(R.drawable.bezstavovce_motolica_pecenova);
        carLogos.add(R.drawable.bezstavovce_pasomnica_dlha);
        carLogos.add(R.drawable.bezstavovce_perovnik);
        carLogos.add(R.drawable.bezstavovce_pijavica_lekarska);
        carLogos.add(R.drawable.bezstavovce_ploskula_mliecna);
        carLogos.add(R.drawable.bezstavovce_rak_riecny);
        carLogos.add(R.drawable.bezstavovce_skebla_rybnicna);
        carLogos.add(R.drawable.bezstavovce_stvorhranka_smrtelna);
        carLogos.add(R.drawable.bezstavovce_terebellidae);

        carNames = new ArrayList<>();
        carNames.add("Acropora Millepora");
        carNames.add("Dážďovka");
        carNames.add("Medúza Vlasatá");
        carNames.add("Motolica Pečeňová");
        carNames.add("Pásomnica Dlhá");
        carNames.add("Perovník");
        carNames.add("Pijavica Lekárska");
        carNames.add("Ploskula Mliečna");
        carNames.add("Rad Riečny");
        carNames.add("Šekbla Rybničná");
        carNames.add("Štvorhranka Smrteľná");
        carNames.add("Terebellidae");

        randCarNames = new ArrayList<>();
        randCarNames.add("Acropora Millepora");
        randCarNames.add("Dážďovka");
        randCarNames.add("Medúza Vlasatá");
        randCarNames.add("Motolica Pečeňová");
        randCarNames.add("Pásomnica Dlhá");
        randCarNames.add("Perovník");
        randCarNames.add("Pijavica Lekárska");
        randCarNames.add("Ploskula Mliečna");
        randCarNames.add("Rad Riečny");
        randCarNames.add("Šekbla Rybničná");
        randCarNames.add("Štvorhranka Smrteľná");
        randCarNames.add("Terebellidae");
        randCarNames.add("Krtko");
        randCarNames.add("Slimák");
        randCarNames.add("Osa");
        randCarNames.add("Kresáňová");
        randCarNames.add("Martinkovičová");
        randCarNames.add("Zemiak");
        randCarNames.add("kôň");
        randCarNames.add("antilopa");
        randCarNames.add("medveď");
        randCarNames.add("Hlavinková");
        randCarNames.add("Pes");
        randCarNames.add("Tvoja mama");
        randCarNames.add("Moja mama");

        ///↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ priradovanie do arraye ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑///

        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                first.setEnabled(false);
                second.setEnabled(false);
                third.setEnabled(false);
                fourth.setEnabled(false);

                cdTimer.cancel();
                animation.cancel();

                SharedPreferences preferences = getSharedPreferences("PlayerScore", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                try {
                    score = preferences.getInt("score", 0);
                } catch (NullPointerException e) {
                }

                checkString = first.getText().toString();

                firstS = first.getText().toString();
                secondS = second.getText().toString();
                thirdS = third.getText().toString();
                fourthS = fourth.getText().toString();

                if (firstS.equals(chosenString)) {
                    first.setBackgroundResource(R.drawable.button_correct);
                    second.setBackgroundResource(R.drawable.button_wrong);
                    third.setBackgroundResource(R.drawable.button_wrong);
                    fourth.setBackgroundResource(R.drawable.button_wrong);
                } else if (secondS.equals(chosenString)) {

                    second.setBackgroundResource(R.drawable.button_correct);
                    first.setBackgroundResource(R.drawable.button_wrong);
                    third.setBackgroundResource(R.drawable.button_wrong);
                    fourth.setBackgroundResource(R.drawable.button_wrong);

                } else if (thirdS.equals(chosenString)) {

                    third.setBackgroundResource(R.drawable.button_correct);
                    first.setBackgroundResource(R.drawable.button_wrong);
                    second.setBackgroundResource(R.drawable.button_wrong);
                    fourth.setBackgroundResource(R.drawable.button_wrong);

                } else if (fourthS.equals(chosenString)) {
                    fourth.setBackgroundResource(R.drawable.button_correct);
                    first.setBackgroundResource(R.drawable.button_wrong);
                    second.setBackgroundResource(R.drawable.button_wrong);
                    third.setBackgroundResource(R.drawable.button_wrong);
                }

                answerTimer = new CountDownTimer(1000, 4000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {

                        first.setEnabled(true);
                        second.setEnabled(true);
                        third.setEnabled(true);
                        fourth.setEnabled(true);

                        if (checkString == chosenString) {

                            SharedPreferences preferences = getSharedPreferences("PlayerScore", MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            score++;
                            editor.putInt("score", score).apply();

                            if (carNames.size() > 0) {
                                theGame();
                            } else {
                                Intent score = new Intent(Organizmy.this, Score.class);
                                Organizmy.this.finish();
                                startActivity(score);
                            }

                        } else {

                            if (carNames.size() > 0) {
                                theGame();
                            } else {
                                Intent score = new Intent(Organizmy.this, Score.class);
                                Organizmy.this.finish();
                                startActivity(score);
                            }
                        }
                    }
                }.start();
            }
        });

        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                first.setEnabled(false);
                second.setEnabled(false);
                third.setEnabled(false);
                fourth.setEnabled(false);

                checkString = second.getText().toString();

                cdTimer.cancel();
                animation.cancel();

                SharedPreferences preferences = getSharedPreferences("PlayerScore", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                try {
                    score = preferences.getInt("score", 0);
                } catch (NullPointerException e) {
                }


                firstS = first.getText().toString();
                secondS = second.getText().toString();
                thirdS = third.getText().toString();
                fourthS = fourth.getText().toString();

                if (firstS.equals(chosenString)) {
                    first.setBackgroundResource(R.drawable.button_correct);
                    second.setBackgroundResource(R.drawable.button_wrong);
                    third.setBackgroundResource(R.drawable.button_wrong);
                    fourth.setBackgroundResource(R.drawable.button_wrong);
                } else if (secondS.equals(chosenString)) {

                    second.setBackgroundResource(R.drawable.button_correct);
                    first.setBackgroundResource(R.drawable.button_wrong);
                    third.setBackgroundResource(R.drawable.button_wrong);
                    fourth.setBackgroundResource(R.drawable.button_wrong);

                } else if (thirdS.equals(chosenString)) {

                    third.setBackgroundResource(R.drawable.button_correct);
                    first.setBackgroundResource(R.drawable.button_wrong);
                    second.setBackgroundResource(R.drawable.button_wrong);
                    fourth.setBackgroundResource(R.drawable.button_wrong);

                } else if (fourthS.equals(chosenString)) {
                    fourth.setBackgroundResource(R.drawable.button_correct);
                    first.setBackgroundResource(R.drawable.button_wrong);
                    second.setBackgroundResource(R.drawable.button_wrong);
                    third.setBackgroundResource(R.drawable.button_wrong);
                }

                answerTimer = new CountDownTimer(1000, 4000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {

                        first.setEnabled(true);
                        second.setEnabled(true);
                        third.setEnabled(true);
                        fourth.setEnabled(true);

                        if (checkString == chosenString) {

                            SharedPreferences preferences = getSharedPreferences("PlayerScore", MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();

                            score++;
                            editor.putInt("score", score).apply();

                            if (carNames.size() > 0) {
                                theGame();
                            } else {
                                Intent score = new Intent(Organizmy.this, Score.class);
                                Organizmy.this.finish();
                                startActivity(score);
                            }

                        } else {

                            if (carNames.size() > 0) {
                                theGame();
                            } else {
                                Intent score = new Intent(Organizmy.this, Score.class);
                                Organizmy.this.finish();
                                startActivity(score);
                            }
                        }
                    }

                }.start();

            }

        });

        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                first.setEnabled(false);
                second.setEnabled(false);
                third.setEnabled(false);
                fourth.setEnabled(false);

                checkString = third.getText().toString();

                cdTimer.cancel();
                animation.cancel();

                SharedPreferences preferences = getSharedPreferences("PlayerScore", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                try {
                    score = preferences.getInt("score", 0);
                } catch (NullPointerException e) {
                }


                firstS = first.getText().toString();
                secondS = second.getText().toString();
                thirdS = third.getText().toString();
                fourthS = fourth.getText().toString();

                if (firstS.equals(chosenString)) {
                    first.setBackgroundResource(R.drawable.button_correct);
                    second.setBackgroundResource(R.drawable.button_wrong);
                    third.setBackgroundResource(R.drawable.button_wrong);
                    fourth.setBackgroundResource(R.drawable.button_wrong);
                } else if (secondS.equals(chosenString)) {

                    second.setBackgroundResource(R.drawable.button_correct);
                    first.setBackgroundResource(R.drawable.button_wrong);
                    third.setBackgroundResource(R.drawable.button_wrong);
                    fourth.setBackgroundResource(R.drawable.button_wrong);

                } else if (thirdS.equals(chosenString)) {

                    third.setBackgroundResource(R.drawable.button_correct);
                    first.setBackgroundResource(R.drawable.button_wrong);
                    second.setBackgroundResource(R.drawable.button_wrong);
                    fourth.setBackgroundResource(R.drawable.button_wrong);

                } else if (fourthS.equals(chosenString)) {
                    fourth.setBackgroundResource(R.drawable.button_correct);
                    first.setBackgroundResource(R.drawable.button_wrong);
                    second.setBackgroundResource(R.drawable.button_wrong);
                    third.setBackgroundResource(R.drawable.button_wrong);
                }

                answerTimer = new CountDownTimer(1000, 4000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {

                        first.setEnabled(true);
                        second.setEnabled(true);
                        third.setEnabled(true);
                        fourth.setEnabled(true);

                        if (checkString == chosenString) {

                            SharedPreferences preferences = getSharedPreferences("PlayerScore", MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();

                            score++;
                            editor.putInt("score", score).apply();

                            if (carNames.size() > 0) {
                                theGame();
                            } else {
                                Intent score = new Intent(Organizmy.this, Score.class);
                                Organizmy.this.finish();
                                startActivity(score);
                            }

                        } else {

                            if (carNames.size() > 0) {
                                theGame();
                            } else {
                                Intent score = new Intent(Organizmy.this, Score.class);
                                Organizmy.this.finish();
                                startActivity(score);
                            }
                        }
                    }

                }.start();

            }

        });

        fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                first.setEnabled(false);
                second.setEnabled(false);
                third.setEnabled(false);
                fourth.setEnabled(false);

                checkString = fourth.getText().toString();

                cdTimer.cancel();
                animation.cancel();

                SharedPreferences preferences = getSharedPreferences("PlayerScore", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                try {
                    score = preferences.getInt("score", 0);
                } catch (NullPointerException e) {
                }

                firstS = first.getText().toString();
                secondS = second.getText().toString();
                thirdS = third.getText().toString();
                fourthS = fourth.getText().toString();


                if (firstS.equals(chosenString)) {
                    first.setBackgroundResource(R.drawable.button_correct);
                    second.setBackgroundResource(R.drawable.button_wrong);
                    third.setBackgroundResource(R.drawable.button_wrong);
                    fourth.setBackgroundResource(R.drawable.button_wrong);
                } else if (secondS.equals(chosenString)) {

                    second.setBackgroundResource(R.drawable.button_correct);
                    first.setBackgroundResource(R.drawable.button_wrong);
                    third.setBackgroundResource(R.drawable.button_wrong);
                    fourth.setBackgroundResource(R.drawable.button_wrong);

                } else if (thirdS.equals(chosenString)) {

                    third.setBackgroundResource(R.drawable.button_correct);
                    first.setBackgroundResource(R.drawable.button_wrong);
                    second.setBackgroundResource(R.drawable.button_wrong);
                    fourth.setBackgroundResource(R.drawable.button_wrong);

                } else if (fourthS.equals(chosenString)) {
                    fourth.setBackgroundResource(R.drawable.button_correct);
                    first.setBackgroundResource(R.drawable.button_wrong);
                    second.setBackgroundResource(R.drawable.button_wrong);
                    third.setBackgroundResource(R.drawable.button_wrong);
                }

                answerTimer = new CountDownTimer(1000, 4000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {

                        first.setEnabled(true);
                        second.setEnabled(true);
                        third.setEnabled(true);
                        fourth.setEnabled(true);

                        if (checkString == chosenString) {

                            SharedPreferences preferences = getSharedPreferences("PlayerScore", MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();

                            score++;
                            editor.putInt("score", score).apply();

                            if (carNames.size() > 0) {
                                theGame();
                            } else {
                                Intent score = new Intent(Organizmy.this, Score.class);
                                Organizmy.this.finish();
                                startActivity(score);
                            }

                        } else {

                            if (carNames.size() > 0) {
                                theGame();
                            } else {
                                Intent score = new Intent(Organizmy.this, Score.class);
                                Organizmy.this.finish();
                                startActivity(score);
                            }
                        }
                    }

                }.start();

            }

        });

        theGame();

    }

    private void theGame() {

        fourth.setBackgroundResource(R.drawable.buttons);
        first.setBackgroundResource(R.drawable.buttons);
        second.setBackgroundResource(R.drawable.buttons);
        third.setBackgroundResource(R.drawable.buttons);


        time.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        animation = ObjectAnimator.ofInt(time, "progress", 0, 500);
        animation.setDuration(5000); //in milliseconds bruv
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();


        cdTimer = new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {

                if (carNames.size() > 0) {
                    theGame();
                } else {
                    Intent score = new Intent(Organizmy.this, Score.class);
                    Organizmy.this.finish();
                    startActivity(score);
                }
            }
        }.start();


        SharedPreferences preferences = getSharedPreferences("PlayerScore", MODE_PRIVATE);
//        Toast.makeText(Organizmy.this, "Score is " + preferences.getInt("score", 0), Toast.LENGTH_SHORT).show();

        firstPart();
        leftOverButtons();

    }

    private void firstPart() {
        Button chosenButton;
        Integer chosenButtonNumber;
        Random position = new Random();

        chosenPos = position.nextInt(carLogos.size()); //Randomne vybere cislo na branie z arraye (velkost podla velkosti arraye)
        chosenImage = carLogos.get(chosenPos); //vybere obrazok prislusny cislu
        image.setImageResource(chosenImage); //setne obrazok
        chosenName = carNames.get(chosenPos); //vybere string prisny cislu


        Random button = new Random();
        chosenButtonNumber = button.nextInt(buttonAL.size());
        chosenButton = buttonAL.get(chosenButtonNumber);
        chosenButton.setText(chosenName);

        chosenString = chosenName;

        buttonAL.remove(chosenButton);
        carLogos.remove(chosenImage);
        carNames.remove(chosenName);

    }


    private void leftOverButtons() {


        for (int i = 0; i < 3; i++) {

            setterinoLefterinoButterinoMethoderino();

        }

        buttonAL.add(first);     // opatovne pridanie
        buttonAL.add(second);    // buttonv do arraye aby
        buttonAL.add(third);     // sa z nej mohlo
        buttonAL.add(fourth);    // zase vyberat

    }

    private void setterinoLefterinoButterinoMethoderino() {

        Button chosenButton;
        String chosenString; //String na random vybrane zvysne buttony
        Integer number; //randomne vygenerovany int

        firstS = first.getText().toString();
        secondS = second.getText().toString();
        thirdS = third.getText().toString();
        fourthS = fourth.getText().toString();

        Random random = new Random();
        number = random.nextInt(buttonAL.size());
        chosenButton = buttonAL.get(number);

        Random randString = new Random();
        try {
            number = randString.nextInt(randCarNames.size());
            chosenString = randCarNames.get(number);

            if (firstS.equals(chosenString) || secondS.equals(chosenString) || thirdS.equals(chosenString) || fourthS.equals(chosenString)) {
                setterinoLefterinoButterinoMethoderino();
            } else {
                chosenButton.setText(chosenString);
                buttonAL.remove(chosenButton); //vymaze uz pouzity button aby sa neprepisovali stringy
            }


        } catch (Exception e) {
        }

    }


    @Override
    public void onBackPressed() {

        SharedPreferences preferences = getSharedPreferences("PlayerScore", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear().apply();

        Intent toMain = new Intent(Organizmy.this, MainActivity.class);
        Organizmy.this.finish();
        startActivity(toMain);

    }
}
