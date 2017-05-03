package hpiz.reaction.com.reaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import hpiz.reaction.com.reaction.miniGames.dropReaction.DropReaction;
import hpiz.reaction.com.reaction.miniGames.surpriseNumberedReaction.SurpriseNumberedReaction;
import hpiz.reaction.com.reaction.miniGames.surpriseReaction.SurpriseReaction;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class GameActivity extends AppCompatActivity {
    public int redScore;
    public int blueScore;
    private ConstraintLayout contentContainer;
    private Button playAgainButton;
    private Button backToMainMenuButton;
    private TextView redScoreText;
    private TextView blueScoreText;
    private String TAG = "GameActivity";
    private TextView topHalf;
    private TextView bottomHalf;
    private int gameProgress;
    private SharedPreferences sp;


    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // save your sate here
        sp.edit().putInt("gameProgress", gameProgress).apply();
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "Invoke onCreate()");
        super.onCreate(savedInstanceState);
        sp = getSharedPreferences("runningPreferences", Context.MODE_PRIVATE);
        gameProgress = sp.getInt("gameProgress", 0);
        setContentView(R.layout.activity_game);

        //configureObjects();
        hide();
        Intent i;
        Log.v(TAG, "GameProgress=" + String.valueOf(gameProgress));
        switch (gameProgress) {
            case 2:
                i = new Intent(GameActivity.this, SurpriseReaction.class);
                gameProgress++;
                Log.v(TAG, "GameProgress=" + String.valueOf(gameProgress));
                //sp.edit().putInt("gameProgress",gameProgress).commit();
                startActivity(i);
                break;
            case 1:
                i = new Intent(GameActivity.this, SurpriseNumberedReaction.class);
                gameProgress++;
                //sp.edit().putInt("gameProgress",gameProgress).commit();
                startActivity(i);
                break;

            case 0:
                i = new Intent(GameActivity.this, DropReaction.class);
                gameProgress++;
                //sp.edit().putInt("gameProgress",gameProgress).commit();
                startActivity(i);
                break;

            case 3:
                i = new Intent(GameActivity.this, MainMenuActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //sp.edit().putInt("gameProgress",gameProgress).commit();
                startActivity(i);
                break;
        }

        //sreaction = new DropReaction(this);
        //sreaction.run();
        //runGame();

        // SupriseReaction simonSays = new SupriseReaction();
        //setContentView(R.layout.minigame_simon_says);


    }

    /*
        public void runSimonSays() {
            setContentView(R.layout.minigame_simon_says);
            Log.v(TAG, " setting game layout");
            hide();
            initializeButtonReactionObjects();
            runSingleRound();

        }
        */
/*
    private void runSingleRound() {
        if (redScore<10){
            if (blueScore<10){
                updateScores();
                clearScreen();

                setEarlyListeners();
                //simonSays = new DropReactionBackgroundTask(this);
                //simonSays.execute("RUN");
            }else{
                blueWonGame();
            }
        }else{
            redWonGame();
        }

    }
*/
/*
    private void clearScreen() {
        setTopBlack();
        setBottomBlack();
        topHalf.setText("");
        bottomHalf.setText("");
    }

    public void redWonGame() {
        playAgainButton.setVisibility(View.VISIBLE);
        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runSimonSays();
            }
        });
        backToMainMenuButton.setVisibility(View.VISIBLE);
        backToMainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GameActivity.this, MainMenuActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
        topHalf.setOnClickListener(null);
        bottomHalf.setOnClickListener(null);
        setBottomBlue();
        setTopRed();
        bottomHalf.setText("You lost to Red " + String.valueOf(redScore) + " to " + String.valueOf(blueScore) +".");
        topHalf.setText("You beat Blue " + String.valueOf(redScore) + " to " + String.valueOf(blueScore) +".");
    }

    public void blueWonGame() {
        playAgainButton.setVisibility(View.VISIBLE);
        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runSimonSays();
            }
        });
        backToMainMenuButton.setVisibility(View.VISIBLE);
        backToMainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GameActivity.this, MainMenuActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
        topHalf.setOnClickListener(null);
        bottomHalf.setOnClickListener(null);
        setBottomBlue();
        setTopRed();
        bottomHalf.setText("You beat Red " + String.valueOf(blueScore) + " to " + String.valueOf(redScore) +".");
        topHalf.setText("You lost to Blue " + String.valueOf(blueScore) + " to " + String.valueOf(redScore) +".");
    }

    public void setTopWhite() {
        topHalf.setBackgroundColor(Color.WHITE);
    }

    public void setBottomWhite() {
        bottomHalf.setBackgroundColor(Color.WHITE);
    }

    public void setTopRed() {
        topHalf.setBackgroundColor(Color.RED);
    }

    public void setBottomBlue() {
        bottomHalf.setBackgroundColor(Color.BLUE);
    }

    public void setTopBlack() {
        topHalf.setBackgroundColor(Color.BLACK);
    }

    public void setBottomBlack() {
        bottomHalf.setBackgroundColor(Color.BLACK);
    }

    private void configureObjects() {


    }

    private void updateScores() {
        redScoreText.setText("Red Score: "+ String.valueOf(redScore));
        blueScoreText.setText("Blue Score: "+ String.valueOf(blueScore));
    }

    private void initializeButtonReactionObjects() {
        redScore = 0;
        blueScore = 0;
        playAgainButton = (Button)findViewById(R.id.playAgainButton);
        playAgainButton.setVisibility(View.GONE);
        playAgainButton.setOnClickListener(null);

        backToMainMenuButton = (Button)findViewById(R.id.backToMainMenuButton);

        backToMainMenuButton.setVisibility(View.GONE);
        backToMainMenuButton.setOnClickListener(null);
        topHalf = (TextView) findViewById(R.id.topHalf);
        bottomHalf = (TextView) findViewById(R.id.bottomHalf);
        redScoreText = (TextView)findViewById(R.id.redScoreText);
        blueScoreText = (TextView)findViewById(R.id.blueScoreText);
        redScoreText.setTextColor(Color.WHITE);
        blueScoreText.setTextColor(Color.WHITE);
        redScoreText.setBackgroundColor(Color.RED);
        blueScoreText.setBackgroundColor(Color.BLUE);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

*/
    public void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //mControlsView.setVisibility(View.GONE);
        //mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        contentContainer = (ConstraintLayout) findViewById(R.id.contentContainer);
        contentContainer.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE

                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

    }

/*

    public void startButtonListeners() {
        topHalf.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                topWon();
                if (bottomHalf.hasOnClickListeners()) {
                    bottomHalf.setOnClickListener(null);
                }
                nextRound();
            }
        });
        bottomHalf.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                bottomWon();
                if (topHalf.hasOnClickListeners()) {
                    topHalf.setOnClickListener(null);
                }
                nextRound();
            }
        });
    }
*/
/*
    private void bottomWon() {
    bottomHalf.setBackgroundColor(Color.BLUE);
        bottomHalf.setText("You Won");
        topHalf.setText("You Lost");
        blueScore++;
        updateScores();
        if (redScore>9){
            redWonGame();
        }
        if (blueScore>9){
            blueWonGame();
        }
        Log.v(TAG,"Blue Score: " + String.valueOf(blueScore));
        Log.v(TAG,"Red Score: " + String.valueOf(redScore));
    }

    private void topWon() {
        topHalf.setBackgroundColor(Color.RED);
        bottomHalf.setText("You Lost");

        topHalf.setText("You Won");
        redScore++;
        updateScores();
        if (redScore>9){
            redWonGame();
        }
        if (blueScore>9){
            blueWonGame();
        }
        Log.v(TAG,"Blue Score: " + String.valueOf(blueScore));
        Log.v(TAG,"Red Score: " + String.valueOf(redScore));
    }
/*
    public void setEarlyListeners() {
        topHalf.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //setBottomWhite();
                bottomWon();
                if (bottomHalf.hasOnClickListeners()) {
                    bottomHalf.setOnClickListener(null);
                }
                simonSays.cancel(true);
                nextRound();
            }
        });
        bottomHalf.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //setTopWhite();
                topWon();
                if (topHalf.hasOnClickListeners()) {
                    topHalf.setOnClickListener(null);
                }
                simonSays.cancel(true);
                nextRound();
            }
        });
    }

    public void nextRound() {
        //DropReactionBackgroundTask simonSays = new DropReactionBackgroundTask(this);
        simonSays.execute("SLEEP:1000");
    }
*/

}
