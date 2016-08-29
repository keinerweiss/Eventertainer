package keinerweiss.eventertainer.GuessGame;

import java.util.ArrayList;

/**
 * Created by ruedi_000 on 06.08.2016.
 */
public class Question {
    public String question;
    public ArrayList<Answer> answers;
    public Integer correctAnswer;

    Question(String question, ArrayList<Answer> answers, Integer correctAnswer) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public Answer getFirst() {
        return answers.get(0);
    }
    public Answer getSecond() {
        return answers.get(1);
    }
    public Answer getThird() {
        return answers.get(2);
    }
    public Answer getFourth() {
        return answers.get(3);
    }

    public Answer getCorrectAnswer() {
        return answers.get(correctAnswer);
    }
}
