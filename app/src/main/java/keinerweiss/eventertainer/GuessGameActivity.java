package keinerweiss.eventertainer;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.io.InputStream;

import keinerweiss.eventertainer.GuessGame.GuessGame;
import keinerweiss.eventertainer.databinding.ActivityGuessGameBinding;

public class GuessGameActivity extends AppCompatActivity {

    protected GuessGame game;

    protected TextView question;
    protected Button answer1;
    protected Button answer2;
    protected Button answer3;
    protected Button answer4;

    private ActivityGuessGameBinding binding;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_game);

        question = (TextView) findViewById(R.id.question);
        answer1 = (Button) findViewById(R.id.answer1);
        answer2 = (Button) findViewById(R.id.answer2);
        answer3 = (Button) findViewById(R.id.answer3);
        answer4 = (Button) findViewById(R.id.answer4);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_guess_game);


        game = new GuessGame();

        String exampleData = loadExampleGameData();
        game.setGameDataJson(exampleData);

        game.addPlayer("Player 1");
        game.addPlayer("Player 2");
        game.addPlayer("Player 3");

        game.start();

        binding.setGame(game);
        binding.setQuestion(game.getCurrentQuestion());
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    protected String loadExampleGameData() {
        String result = "";
        byte[] b;
        try {
            Resources res = getResources();
            InputStream in_s = res.openRawResource(R.raw.questions);
            b = new byte[in_s.available()];
            in_s.read(b);
        } catch (IOException e) {
            Log.e("loadExampleGame", "Cannot read example questions.");
            return result;
        }
        result = new String(b);
        return result;
    }

    public void pickedJoker(View view) {
        // remove regular joker
    }

    public void pickedFiftyFiftyJoker(View view) {
        // remove 5050joker
        // hide 2 buttons != correct answer

    }


    public void answerSelected(View view) {
        Integer correctAnswer = game.getCurrentQuestion().correctAnswer;
        Boolean correct = false;
        Button correctButtonTemp;
        final Button pressedButton = (Button) view;

        if(correctAnswer == 0) {
            correctButtonTemp = answer1;
        }
        else if(correctAnswer == 1) {
            correctButtonTemp = answer1;
        }
        else if(correctAnswer == 2) {
            correctButtonTemp = answer1;
        }
        else if(correctAnswer == 3) {
            correctButtonTemp = answer1;
        } else {
            correctButtonTemp = null;
        }
        final Button correctButton = correctButtonTemp;

        // TODO catch a null correct button
        if (view.getId() == R.id.answer1 && correctAnswer == 0) {
            correct = true;
        } else if (view.getId() == R.id.answer2 && correctAnswer == 1) {
            correct = true;
        } else if (view.getId() == R.id.answer3 && correctAnswer == 2) {
            correct = true;
        } else if (view.getId() == R.id.answer4 && correctAnswer == 3) {
            correct = true;
        }
        if (correct) {
            // level up the player
            game.increaseScoreOfCurrentPlayer();
            // add to played questions

            // show LevelUp Screen
            game.nextTurn();
            binding.setGame(game);
        } else {
            // show Loose Screen
            // highlight error button red
            final Handler handler = new Handler();
            final GuessGameActivity context = this;

            game.nextTurn();
            binding.setGame(game);

            // wait 3 seconds
            // also highlight right button green
            // wait 5 seconds
            // next turn
        }
        // make sure 4 answer buttons are shown afterwards (reset 505 joker)
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "GuessGame Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://keinerweiss.eventertainer/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "GuessGame Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://keinerweiss.eventertainer/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
