package keinerweiss.eventertainer.GuessGame;

/**
 * Created by ruedi_000 on 06.08.2016.
 */
public class FiftyFiftyJoker extends Joker {

    FiftyFiftyJoker(String name) {
        super(name);
    }

    public void apply(Question question) {
        super.apply(question);
        // make two answers disabled
    }



}
