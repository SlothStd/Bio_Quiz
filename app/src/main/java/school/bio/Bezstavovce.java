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
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Random;

public class Bezstavovce extends Activity {

    Button first, second, third, fourth, nothing;
    ArrayList<Integer> obrazky;
    ArrayList<String> nazvy, randnazvy;
    ArrayList<Button> buttonAL;
    ProgressBar time;
    ObjectAnimator animation;
    Integer chosenImage, chosenPos, score;
    String chosenName, checkString, chosenString;
    ImageView image;
    CountDownTimer cdTimer, answerTimer, additionalTime;
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
        image.bringToFront();
        time = (ProgressBar) findViewById(R.id.timePB);

        Typeface canter = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/canter.otf");
        first.setTypeface(canter);
        first.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
        second.setTypeface(canter);
        second.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
        third.setTypeface(canter);
        third.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
        fourth.setTypeface(canter);
        fourth.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);

        buttonAL = new ArrayList<>();
        buttonAL.add(first);
        buttonAL.add(second);
        buttonAL.add(third);
        buttonAL.add(fourth);

        obrazky = new ArrayList<>();
        obrazky.add(R.drawable.bezstavovce_acropora_millepora);
        obrazky.add(R.drawable.bezstavovce_dazdovka);
        obrazky.add(R.drawable.bezstavovce_meduza_vlasata);
        obrazky.add(R.drawable.bezstavovce_motolica_pecenova);
        obrazky.add(R.drawable.bezstavovce_pasomnica_dlha);
        obrazky.add(R.drawable.bezstavovce_perovnik);
        obrazky.add(R.drawable.bezstavovce_pijavica_lekarska);
        obrazky.add(R.drawable.bezstavovce_ploskula_mliecna);
        obrazky.add(R.drawable.bezstavovce_rak_riecny);
        obrazky.add(R.drawable.bezstavovce_skebla_rybnicna);
        obrazky.add(R.drawable.bezstavovce_stvorhranka_smrtelna);
        obrazky.add(R.drawable.bezstavovce_terebellidae);

        nazvy = new ArrayList<>();
        nazvy.add("Acropora Millepora");
        nazvy.add("Dážďovka");
        nazvy.add("Medúza Vlasatá");
        nazvy.add("Motolica Pečeňová");
        nazvy.add("Pásomnica Dlhá");
        nazvy.add("Perovník");
        nazvy.add("Pijavica Lekárska");
        nazvy.add("Ploskula Mliečna");
        nazvy.add("Rak Riečny");
        nazvy.add("Šekbla Rybničná");
        nazvy.add("Štvorhranka Smrteľná");
        nazvy.add("Terebellidae");

        randnazvy = new ArrayList<>();
        randnazvy.add("Acropora Millepora");
        randnazvy.add("Dážďovka");
        randnazvy.add("Medúza Vlasatá");
        randnazvy.add("Motolica Pečeňová");
        randnazvy.add("Pásomnica Dlhá");
        randnazvy.add("Perovník");
        randnazvy.add("Pijavica Lekárska");
        randnazvy.add("Ploskula Mliečna");
        randnazvy.add("Rak Riečny");
        randnazvy.add("Šekbla Rybničná");
        randnazvy.add("Štvorhranka Smrteľná");
        randnazvy.add("Terebellidae");
        randnazvy.add("Krtko");
        randnazvy.add("Slimák");
        randnazvy.add("Osa");
        randnazvy.add("Salamandra škvrnitá");
        randnazvy.add("Krokodíl morský");
        randnazvy.add("Zemiak");
        randnazvy.add("kôň");
        randnazvy.add("antilopa");
        randnazvy.add("medveď");
        randnazvy.add("Mola slncovitá");
        randnazvy.add("Pes");
        randnazvy.add("Kazuár veľkoprilbý");
        randnazvy.add("Petromyzontidai");
        randnazvy.add("Diplorhina");
        randnazvy.add("Sliznatka morská");
        randnazvy.add("Lampetra Fluviatilis");
        randnazvy.add("Orol skalný");
        randnazvy.add("Gnathostomata");
        randnazvy.add("Archeopteryx");
        randnazvy.add("Amur biely");
        randnazvy.add("Býčko nahotemenný");
        randnazvy.add("Hlaváč pásoplutvý");
        randnazvy.add("Hrúzovec sieťovaný");
        randnazvy.add("Lieň sliznatý");
        randnazvy.add("mlok horský");
        randnazvy.add("kunka červenobruchá");
        randnazvy.add("hrabavka škvrnitá");

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

                            if (nazvy.size() > 0) {
                                theGame();
                            } else {
                                Intent score = new Intent(Bezstavovce.this, Score.class);
                                Bezstavovce.this.finish();
                                startActivity(score);
                            }

                        } else {

                            if (nazvy.size() > 0) {
                                theGame();
                            } else {
                                Intent score = new Intent(Bezstavovce.this, Score.class);
                                Bezstavovce.this.finish();
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

                            if (nazvy.size() > 0) {
                                theGame();
                            } else {
                                Intent score = new Intent(Bezstavovce.this, Score.class);
                                Bezstavovce.this.finish();
                                startActivity(score);
                            }

                        } else {

                            if (nazvy.size() > 0) {
                                theGame();
                            } else {
                                Intent score = new Intent(Bezstavovce.this, Score.class);
                                Bezstavovce.this.finish();
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

                            if (nazvy.size() > 0) {
                                theGame();
                            } else {
                                Intent score = new Intent(Bezstavovce.this, Score.class);
                                Bezstavovce.this.finish();
                                startActivity(score);
                            }

                        } else {

                            if (nazvy.size() > 0) {
                                theGame();
                            } else {
                                Intent score = new Intent(Bezstavovce.this, Score.class);
                                Bezstavovce.this.finish();
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

                            if (nazvy.size() > 0) {
                                theGame();
                            } else {
                                Intent score = new Intent(Bezstavovce.this, Score.class);
                                Bezstavovce.this.finish();
                                startActivity(score);
                            }

                        } else {

                            if (nazvy.size() > 0) {
                                theGame();
                            } else {
                                Intent score = new Intent(Bezstavovce.this, Score.class);
                                Bezstavovce.this.finish();
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


        time.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        fourth.setBackgroundResource(R.drawable.buttons);
        first.setBackgroundResource(R.drawable.buttons);
        second.setBackgroundResource(R.drawable.buttons);
        third.setBackgroundResource(R.drawable.buttons);

        animation = ObjectAnimator.ofInt(time, "progress", 0, 500);
        animation.setDuration(6000); //in milliseconds bruv
        animation.setInterpolator(new LinearInterpolator());
        animation.start();

        cdTimer = new CountDownTimer(6000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {

                if (nazvy.size() > 0) {
                    theGame();
                } else {
                    Intent score = new Intent(Bezstavovce.this, Score.class);
                    Bezstavovce.this.finish();
                    startActivity(score);
                }
            }
        }.start();


        firstPart();
        leftOverButtons();

    }

    private void firstPart() {
        Button chosenButton;
        Integer chosenButtonNumber;
        Random position = new Random();

        chosenPos = position.nextInt(obrazky.size()); //Randomne vybere cislo na branie z arraye (velkost podla velkosti arraye)
        chosenImage = obrazky.get(chosenPos); //vybere obrazok prislusny cislu
        image.setImageResource(chosenImage); //setne obrazok
        chosenName = nazvy.get(chosenPos); //vybere string prisny cislu


        Random button = new Random();
        chosenButtonNumber = button.nextInt(buttonAL.size());
        chosenButton = buttonAL.get(chosenButtonNumber);
        chosenButton.setText(chosenName);

        chosenString = chosenName;

        buttonAL.remove(chosenButton);
        obrazky.remove(chosenImage);
        nazvy.remove(chosenName);

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
            number = randString.nextInt(randnazvy.size());
            chosenString = randnazvy.get(number);

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

        cdTimer.cancel();
        animation.cancel();

        SharedPreferences preferences = getSharedPreferences("PlayerScore", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear().apply();

        Intent toMain = new Intent(Bezstavovce.this, MainActivity.class);
        Bezstavovce.this.finish();
        startActivity(toMain);

    }
}
