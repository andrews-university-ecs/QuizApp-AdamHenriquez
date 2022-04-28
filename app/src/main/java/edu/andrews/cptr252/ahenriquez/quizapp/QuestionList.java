package edu.andrews.cptr252.ahenriquez.quizapp;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;


/** Manage list of questions. This is a singleton class.
 * Only one instance of this class can be created.
 */
public class QuestionList {
    private static QuestionList sOurInstance;

    /** List of questions */
    private ArrayList<Quiz> mQuestions;

    /** Reference to information about app environment */
    private Context mAppContext;

    /** Tag for message log */
    private static final String TAG = "QuestionList";
    /** name of JSON file containing list of questions */
    private static final String FILENAME = "questions.json";
    /** Reference to JSON serializer for a list of questions */
    private QuestionJSONSerializer mSerializer;

    /** Add a question to the list at given position
     * @param position is the index for the question to add
     * @param question is the question to add.
     */
    public void addQuestion(int position, Quiz question) {
        mQuestions.add(position, question);
        saveQuestions();
    }

    /**
     * Delete a given question from the list of questions
     * @param position is the index of the question to delete
     */
    public void deleteQuestion(int position) {
        mQuestions.remove(position);
        saveQuestions();
    }

    /**
     * Write question list to JSON file.
     * @return trie if successful, false otherwise.
     */
    public boolean saveQuestions() {
        try {
            mSerializer.saveQuestions(mQuestions);
            Log.d(TAG, "Questions saved to file");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving questions: " + e);
            return false;
        }
    }

    /**
     * Add a question to the list.
     * @param question is the question to add.
     */
    public void addQuestion(Quiz question) {
        mQuestions.add(question);
        saveQuestions();
    }


    /**
     * Return the question with a given id.
     * @param id Unique id for the question
     * @return The question object or null if not found.
     */
    public Quiz getQuestion(UUID id) {
        for (Quiz question : mQuestions) {
            if (question.getId().equals(id))
            return question;
        }
        return null;
    }

    /** Private Constructor */
    private QuestionList(Context appContext) {
        mAppContext = appContext;
        // Create our serializer to load and save questions
        mSerializer = new QuestionJSONSerializer(mAppContext, FILENAME);

        try {
            //load questions from JSON file
            mQuestions = mSerializer.loadQuestions();
        } catch (Exception e) {
            // Unable to load from file, so create empty question list.
            // Either file does not exist (okay)
            // Or file contains error (not great)
            mQuestions = new ArrayList<>();
            Log.e(TAG, "Error loading bugs: " + e);
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
