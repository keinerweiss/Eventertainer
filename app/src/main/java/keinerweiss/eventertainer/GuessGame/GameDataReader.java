package keinerweiss.eventertainer.GuessGame;

import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by ruedi_000 on 06.08.2016.
 *
 *
 */
/*
    File format as JSON:
    {
        name: "The game name",
        questions = [
            {
                question: "A ?",
                answers: [ "1 !", "2 !", "3 !", "4 !"]
                correctAnswer = 0
            },
            {
                question: "B ?",
                answers: [ "1 !", "2 !", "3 !", "4 !"]
                correctAnswer = 1
            },
        ],
        jokers: 3,
        fiftyFiftyJoker: true
    }
*/
public class GameDataReader {

    GameManager gameManager;

    GameDataReader(GameManager gameManager) {
        this.gameManager = gameManager;

    }

    void readGameDataFromString(String gameDataString) {
        JSONObject gameDataJson = new JSONObject();

        String gameName = "";
        JSONArray questions = null;
        Integer jokers = 0;
        Boolean fiftyFiftyJoker;

        try {
            gameDataJson = new JSONObject(gameDataString);
        } catch(JSONException e) {
            // json could not be read
            try {
                gameDataJson = new JSONObject("{}");
            } catch(JSONException e2) {
                Log.e("readGameData", "Creating a fallback game data object failed.", e2);
            }
        }
        try {
            gameName = gameDataJson.getString("name");
        } catch(JSONException e) {
            gameName = "";
            // name not found
        }
        try {
            questions = gameDataJson.getJSONArray("questions");
        } catch(JSONException e) {
            // questions array not found
            try {
                questions = new JSONArray("[]");
            } catch(JSONException e2) {
                Log.e("readGameData", "Creating a fallback questions array failed.", e2);
            }
        }
        try {
            jokers = gameDataJson.getInt("jokers");
        } catch(JSONException e) {
            // jokers not defined
            jokers = 0;
        }
        try {
            fiftyFiftyJoker = gameDataJson.getBoolean("fiftyFiftyJoker");
        } catch(JSONException e) {
            // fiftyFiftyJoker not defined
            fiftyFiftyJoker = false;
        }

        gameManager.setQuestions( readQuestions(questions) );

    }

    ArrayList<Question> readQuestions(JSONArray questions) {
        ArrayList<Question> result = new ArrayList<Question>();
        for (int i=0; i < questions.length(); i++)
        {
            JSONArray answers = null;
            String questionText = "";
            Integer correctAnswer = 0;

            try {
                JSONObject q = questions.getJSONObject(i);
                questionText = q.getString("question");
                answers = q.getJSONArray("answers");
                correctAnswer = q.getInt("correctAnswer");
            } catch (JSONException e) {
                Log.e("readAnswers", "Could not read question " + i, e);
            }

            ArrayList<Answer> answersList = readAnswers(answers);
            Question question = new Question(questionText, answersList, correctAnswer);
            result.add(question);
        }
        return result;
    }

    ArrayList<Answer> readAnswers(JSONArray answers) {

        ArrayList<Answer> result = new ArrayList<Answer>();

        for (int i=0; i < answers.length(); i++)
        {
            String a = "";
            String answer = "";
            try {
                a = answers.getString(i);
            } catch (JSONException e) {
                Log.e("readAnswers", "Could not read answer " + i, e);
                a = "";
            }
            if(a != null) {
                answer = a.toString();
                if(answer.length() == 0) {
                    Log.e("readAnswers", "Answer " + i + " was empty.");
                }
            }
            result.add(new Answer(answer));
        }
        return result;
    }
}
