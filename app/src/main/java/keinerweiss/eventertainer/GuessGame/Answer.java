package keinerweiss.eventertainer.GuessGame;

/**
 * Created by ruedi_000 on 06.08.2016.
 */
public class Answer {

    public String text = "";
    public boolean enabled = true;

    Answer(String answer) {
        text = answer;
    }

    public void disable() {
        enabled = false;
    }

    public void enable() {
        enabled = true;
    }

    boolean enabled() {
        return enabled;
    }
}
