package keinerweiss.eventertainer.GuessGame;

import android.util.ArrayMap;
import android.util.Pair;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by ruedi_000 on 06.08.2016.
 */
public class GameManager {

    protected ArrayList<Question> questions;
    protected ArrayList<Player> players;
    protected ArrayList<Joker> jokers;

    protected ArrayMap<Player, Integer> playerScores;
    protected ArrayMap<Pair<Player, Joker>, Boolean> appliedJokers;

    protected ArrayMap<Question, Player> playerQuestions;
    protected ArrayMap<Question, Boolean> playedQuestions;

    protected Player currentPlayer;
    protected Integer currentQuestion;
    protected Boolean finished;

    GameManager() {
        questions = new ArrayList<Question>();
        players = new ArrayList<Player>();
        jokers = new ArrayList<Joker>();
        appliedJokers = new ArrayMap<Pair<Player, Joker>, Boolean>();
        playerQuestions = new ArrayMap<Question, Player>();
        playedQuestions = new ArrayMap<Question, Boolean>();
        playerScores = new ArrayMap<Player, Integer>();

        reset();
    }

    public void start() {
        reset();
        nextTurn();
    }

    public void addPlayer(String name) {
        Player player = new Player(name);
        players.add(player);
    }

    public void reset() {
        resetPlayerScores();
        resetAppliedJokers();
        resetPlayerQuestions();
        resetPlayedQuestions();

        currentQuestion = -1;
        currentPlayer = null;
        finished = false;
    }

    public void resetPlayerScores() {
        playerScores.clear();
        for (Player player : players) {
            playerScores.put(player, 0);
        }
    }

    public void resetAppliedJokers() {
        appliedJokers.clear();
        for (Player player : players) {
            for (Joker joker : jokers) {
                appliedJokers.put(new Pair<Player, Joker>(player, joker), false);
            }
        }
    }

    public void resetPlayerQuestions() {
        playerQuestions.clear();
        // TODO: is this a shallow or deep copy clone? Are player indexes right?
        ArrayList<Player> shuffledPlayers = (ArrayList<Player>)players.clone();
        Collections.shuffle(shuffledPlayers);

        Integer numPlayers = shuffledPlayers.size();
        Integer i = 0;
        for (Question question: questions) {
            playerQuestions.put(question, shuffledPlayers.get( i++ % numPlayers ));
        }
    }

    public void resetPlayedQuestions() {
        playedQuestions.clear();
        for (Question question: questions) {
            playedQuestions.put(question, false);
        }
    }

    public void nextTurn() {
        currentQuestion = currentQuestion + 1;
        Question q = questions.get(currentQuestion);
        playedQuestions.setValueAt(
                playedQuestions.indexOfKey( q ), true);

        if(currentQuestion >= questions.size()) {
            currentQuestion = -1;
            currentPlayer = null;
            finished = true;
        } else {
            currentPlayer = playerQuestions.get( q );
        }
    }

    public Player getWinner() {
        Player winner = null;
        Integer highscore = 0;
        for (Player player : players) {
            Integer playerScore = playerScores.get(player);
            if(playerScore > highscore) {
                highscore = playerScore;
                winner = player;
            }
        }
        // TODO: This may return null!
        return winner;
    }

    public Question getCurrentQuestion() {
        return questions.get(currentQuestion);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void applyJoker() {

    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public void increaseScoreOfCurrentPlayer() {
        Integer score = playerScores.get(currentPlayer);
        playerScores.put(currentPlayer, score + 1);
    }
}
