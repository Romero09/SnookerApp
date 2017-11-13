package com.example.android.snookerapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.android.snookerapp.R.id.player_one_name_show;

public class MainActivity extends AppCompatActivity {

    //Global variable for player names because they can be changed
    String playerOneName = "Player 1";
    String playerTwoName = "Player 2";

    //Global variable for color of the ball so i can be showed in the logs
    String ballcolor = " ";

    //Global variable for score
    int scoreOne = 0;
    int scoreTwo = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //adding custom toolbar (contains only image for background)
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);



        //Taking player 1 name from EditText View and adding it to simple TextView,
        //after that hiding EditText and making TextView with player name visible
        final EditText editPlayerOneView = (EditText) findViewById(R.id.player_one_name);
        editPlayerOneView.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    playerOneName = String.valueOf(editPlayerOneView.getText());
                    TextView playerOneNameText = (TextView) findViewById(player_one_name_show);

                    if(playerOneName.isEmpty()){
                        playerOneName = "Player 1";
                        playerOneNameText.setText(playerOneName);
                    editPlayerOneView.setVisibility(View.GONE);
                    playerOneNameText.setVisibility(View.VISIBLE);}


                    else {

                        playerOneNameText.setText(playerOneName);
                    editPlayerOneView.setVisibility(View.GONE);
                    playerOneNameText.setVisibility(View.VISIBLE);}

                    return true;
                }
                return false;
            }
        });


        //onClikc listener to change backward EdixText player 1 visible, to chnage player Name
        final TextView playerOneNameText = (TextView) findViewById(player_one_name_show);
        playerOneNameText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editPlayerOneView.setVisibility(View.VISIBLE);
                playerOneNameText.setVisibility(View.GONE);
           }
           });


        //Taking player 2 name from EditText View and adding it to simple TextView,
        //after that hiding EditText and making TextView with player name visible
        final EditText editPlayerTwoView = (EditText) findViewById(R.id.player_two_name);
        editPlayerTwoView.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    playerTwoName = String.valueOf(editPlayerTwoView.getText());
                    TextView playerTwoNameText = (TextView) findViewById(R.id.player_two_name_show);

                    if(playerTwoName.isEmpty()){
                        playerTwoName = "Player 2";
                        playerTwoNameText.setText(playerTwoName);
                        editPlayerTwoView.setVisibility(View.GONE);
                        playerTwoNameText.setVisibility(View.VISIBLE);}

                    else {

                        playerTwoNameText.setText(playerTwoName);
                        editPlayerTwoView.setVisibility(View.GONE);
                        playerTwoNameText.setVisibility(View.VISIBLE);}


                    return true;
                }
                return false;
            }
        });

        //onClikc listener to change backward EdixText player 2 visible, to chnage player Name
        final TextView playerTwoNameText = (TextView) findViewById(R.id.player_two_name_show);
        playerTwoNameText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editPlayerTwoView.setVisibility(View.VISIBLE);
                playerTwoNameText.setVisibility(View.GONE);
            }
        });




        //Setting onclick listener on Score Text view for P1 for opening and closing
        //hidden buttons for score correction
        TextView scorePOne = (TextView) findViewById(R.id.playerOneScore);
        scorePOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View correctionPlusP1 = findViewById(R.id.increase_player_one);
                View correctionMinusP1 = findViewById(R.id.decrease_player_one);


                if (correctionPlusP1.getVisibility() == View.INVISIBLE) {

                    correctionMinusP1.setVisibility(View.VISIBLE);
                    correctionPlusP1.setVisibility(View.VISIBLE);


                } else {
                    correctionPlusP1.setVisibility(View.INVISIBLE);
                    correctionMinusP1.setVisibility(View.INVISIBLE);

                }
            }
        });

        //Setting onclick listener on Score Text view for P2 for showing and closing
        //hidden buttons for score correction
        TextView scorePTwo = (TextView) findViewById(R.id.playerTwoScore);
        scorePTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View correctionPlusP2 = findViewById(R.id.increase_player_two);
                View correctionMinusP2 = findViewById(R.id.decrease_player_two);


                if (correctionPlusP2.getVisibility() == View.INVISIBLE) {

                    correctionMinusP2.setVisibility(View.VISIBLE);
                    correctionPlusP2.setVisibility(View.VISIBLE);


                } else {
                    correctionPlusP2.setVisibility(View.INVISIBLE);
                    correctionMinusP2.setVisibility(View.INVISIBLE);

                }
            }
        });


    }

    //Score dislay method also includes check for max score + adding logs feature + logs scrollview always scroll down
    public void displayP1Score(int score) {

        //Checking for max score in one set
        if (score >= 147) {
            score = 147;
            Toast maxScore = Toast.makeText(this, "Max score 147 " +playerOneName, Toast.LENGTH_SHORT);
            maxScore.show();
        }

        TextView scoreView = (TextView) findViewById(R.id.playerOneScore);
        scoreView.setText(String.valueOf(score));

        //Finding logs text view. and adding to this view every hit of player.
        TextView scoreLogs = (TextView) findViewById(R.id.logs);
        scoreLogs.append("\n" + playerOneName + " hit " + ballcolor + " +" + score + ", total: " + scoreOne);

        //Logs scroll vie always scroll down to show latest logs
        final ScrollView scrollview = ((ScrollView) findViewById(R.id.logs_scroll_view));
        scrollview.post(new Runnable() {
            @Override
            public void run() {
                scrollview.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });

    }

    //Buttons that add score to P1
    public void plusOneScoreP1(View view) {
        scoreOne += +1;
        ballcolor = "red";
        displayP1Score(scoreOne);
    }

    public void plusTwoScoreP1(View view) {
        scoreOne += +2;
        ballcolor = "green";
        displayP1Score(scoreOne);
    }

    public void plusThreeScoreP1(View view) {
        scoreOne += +3;
        ballcolor = "yellow";
        displayP1Score(scoreOne);
    }

    public void plusFourScoreP1(View view) {
        scoreOne += +4;
        ballcolor = "brown";
        displayP1Score(scoreOne);
    }

    public void plusFiveScoreP1(View view) {
        scoreOne += +5;
        ballcolor = "blue";
        displayP1Score(scoreOne);
    }

    public void plusSixScoreP1(View view) {
        scoreOne += +6;
        ballcolor = "pink";
        displayP1Score(scoreOne);
    }

    public void plusSevenScoreP1(View view) {
        scoreOne += +7;
        ballcolor = "black";
        displayP1Score(scoreOne);
    }


    //Score dislay method also includes check for max score + adding logs feature + logs scrollview always scroll down
    public void displayP2Score(int score) {

        //Checking for max score in one set
        if (score >= 147) {
            score = 147;
            Toast maxScore = Toast.makeText(this, "Max score 147 for " +playerTwoName, Toast.LENGTH_SHORT);
            maxScore.show();
        }

        TextView scoreView = (TextView) findViewById(R.id.playerTwoScore);
        scoreView.setText(String.valueOf(score));

        //Finding logs text view. and adding to this view every hit of player.
        TextView scoreLogs = (TextView) findViewById(R.id.logs);
        scoreLogs.append("\n" + playerTwoName + " hit " + ballcolor + " +" + score + ", total: " + scoreTwo);

        //Logs scroll vie always scroll down to show latest logs
        final ScrollView scrollview = ((ScrollView) findViewById(R.id.logs_scroll_view));
        scrollview.post(new Runnable() {
            @Override
            public void run() {
                scrollview.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });



    }

    //Buttons that add score to P2
    public void plusOneScoreP2(View view) {
        scoreTwo += +1;
        ballcolor = "red";
        displayP2Score(scoreTwo);

    }

    public void plusTwoScoreP2(View view) {
        scoreTwo += +2;
        ballcolor = "green";
        displayP2Score(scoreTwo);
    }

    public void plusThreeScoreP2(View view) {
        scoreTwo += +3;
        ballcolor = "yellow";
        displayP2Score(scoreTwo);
    }

    public void plusFourScoreP2(View view) {
        scoreTwo += +4;
        ballcolor = "brown";
        displayP2Score(scoreTwo);
    }

    public void plusFiveScoreP2(View view) {
        scoreTwo += +5;
        ballcolor = "blue";
        displayP2Score(scoreTwo);
    }

    public void plusSixScoreP2(View view) {
        scoreTwo += +6;
        ballcolor = "pink";
        displayP2Score(scoreTwo);
    }

    public void plusSevenScoreP2(View view) {
        scoreTwo += +7;
        ballcolor = "black";
        displayP2Score(scoreTwo);
    }


    //Reset button resets scores + hides correction score buttons + and declaring the winner + reseting logs
    public void resetScore(View view) {

        //hide score correction buttons
        View correctionPlusP2 = findViewById(R.id.increase_player_two);
        View correctionMinusP2 = findViewById(R.id.decrease_player_two);
        View correctionPlusP1 = findViewById(R.id.increase_player_one);
        View correctionMinusP1 = findViewById(R.id.decrease_player_one);
        correctionPlusP1.setVisibility(View.INVISIBLE);
        correctionMinusP1.setVisibility(View.INVISIBLE);
        correctionPlusP2.setVisibility(View.INVISIBLE);
        correctionMinusP2.setVisibility(View.INVISIBLE);


        //declearing who is the winner
        if (scoreOne > scoreTwo) {
            Toast winnerP1 = Toast.makeText(this, playerOneName + " Wins!", Toast.LENGTH_SHORT);
            winnerP1.show();

        } else if (scoreTwo > scoreOne) {
            Toast winnerP2 = Toast.makeText(this, playerTwoName + "Wins!", Toast.LENGTH_SHORT);
            winnerP2.show();

        } else if (scoreOne > 0 && scoreTwo > 0 && scoreOne == scoreTwo) {

            Toast Draw = Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT);
            Draw.show();

        }

        //reseting scores
        scoreOne = 0;
        scoreTwo = 0;
        displayP2Score(scoreTwo);
        displayP1Score(scoreOne);


        //clearing all logs
        TextView scoreLogs = (TextView) findViewById(R.id.logs);
        scoreLogs.setText("Logs:");



    }


    //correction score buttons
    public void correctionPlusP1(View view) {
        scoreOne += +1;
        displayP1Score(scoreOne);
    }

    public void correctionPlusP2(View view) {
        scoreTwo += +1;
        displayP2Score(scoreTwo);
    }

    public void correctionMinusP1(View view) {
        scoreOne += -1;
        displayP1Score(scoreOne);
    }

    public void correctionMinusP2(View view) {
        scoreTwo += -1;
        displayP2Score(scoreTwo);
    }


}

