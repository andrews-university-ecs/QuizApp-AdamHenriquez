package edu.andrews.cptr252.ahenriquez.quizapp;

import java.util.UUID;
import java.util.Date;

public class Quiz {
/* UUID class is used to automatically create new unique ids for a specific question*/

    /* The question for the quiz */
    private String mQuestion;

    private boolean mAnswerTrue;

    private UUID mId;

    /* Create and initialize new Question */
    public Quiz() {
        //Generate unique identifier for new Question
        mId = UUID.randomUUID();
    }
    /* Return unique id for question
    @return Question id
     */
    public String getQuestion() {
        return mQuestion;
    }
    /* provide new question for Quiz
    @param question New question
     */
    public void setQuestion(String title) {
        mQuestion = title;
    }

    //getters and setters for private fields
    public boolean isAnswerTrue() { return mAnswerTrue;}

    public void setAnswerTrue(boolean answerTrue) { this.mAnswerTrue = answerTrue; }
}


