package edu.andrews.cptr252.ahenriquez.quizapp;

import android.content.Context;
import java.util.ArrayList;


/** Manage list of questions. This is a singleton class.
 * Only one instance of this class can be created.
 */
public class QuestionList {
    private static QuestionList sOurInstance;

    /** List of questions */
    private ArrayList<Quiz> mQuestions;

    /** Reference to information about app environment */
    private Context mAppContext;

    /** Private Constructor */
    private QuestionList(Context appContext) {
        mAppContext = appContext;
        mQuestions = new ArrayList<>();
        for(int i = 0; i < 100; i++) {
            Quiz quiz = new Quiz();
            quiz.setQuestion("Question " + i);
            //every other one is true
            quiz.setAnswerTrue(i % 2 == 0);
            mQuestions.add(quiz);
        }
    }
    /** Create one and only one instance of the questions list.
     * (If it does not exist create it)
     * @param c is the Application context
     * @return Reference to the bug list
      */
    public static QuestionList getInstance(Context c) {
        if (sOurInstance == null) {
            sOurInstance = new QuestionList(c.getApplicationContext());
        }
        return sOurInstance;
    }
    /** Return list of questions
     * @return Array of Question objects
     */
    public ArrayList<Quiz> getQuestions() {return mQuestions; }
}
