package keinerweiss.eventertainer.GuessGame;

import android.content.Context;

/**
 * Players
 * Score per player
 * N Jokers per player - can take each once
 *
 * Questions
 * Questions roll on players (random equal distribution)
 * 4 answers per question - one is right
 *
 *
 *
 */
public class GuessGame {

    protected GameManager gm;
    protected Player player;
    protected Question question;

    public GuessGame() {
        gm = new GameManager();
    }

    public void setGameDataJson(String gameDataJson) {
        GameDataReader reader = new GameDataReader(gm);
        reader.readGameDataFromString(gameDataJson);
    }

    public void start() {
        gm.start();
    }

    public void addPlayer(String name) {
        gm.addPlayer(name);
    }

    public Question getCurrentQuestion() {
        return gm.getCurrentQuestion();
    }

    public Player getCurrentPlayer() {
        return gm.getCurrentPlayer();
    }

    public void increaseScoreOfCurrentPlayer() {
        gm.increaseScoreOfCurrentPlayer();
    }

    public void nextTurn() {
        gm.nextTurn();
    }
}
