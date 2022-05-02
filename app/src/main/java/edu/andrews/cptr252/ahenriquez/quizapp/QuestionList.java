package edu.andrews.cptr252.ahenriquez.quizapp;

import android.content.Context;
import android.util.Log;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import edu.andrews.cptr252.ahenriquez.quizapp.database.QuestionCursorWrapper;
import edu.andrews.cptr252.ahenriquez.quizapp.database.QuestionDbHelper;
import edu.andrews.cptr252.ahenriquez.quizapp.database.QuestionDbSchema.QuestionTable;
import java.util.ArrayList;
import java.util.UUID;


/** Manage list of questions. This is a singleton class.
 * Only one instance of this class can be created.
 */
public class QuestionList {
    private static QuestionList sOurInstance;

    private SQLiteDatabase mDatabase;

    /** Reference to information about app environment */
    private Context mAppContext;

    /** Tag for message log */
    private static final String TAG = "QuestionList";
    /** name of JSON file containing list of questions */
    private static final String FILENAME = "questions.json";
    /** Reference to JSON serializer for a list of questions */
    private QuestionJSONSerializer mSerializer;

    /**
     * Pack question information into a ContentValues object.
     * @param question to pack.
     * @return resulting ContentValues object
     */
    public static ContentValues getContentValues(Question question) {
        ContentValues values = new ContentValues();
        values.put(QuestionTable.Cols.UUID, question.getId().toString());
        values.put(QuestionTable.Cols.QUESTION, question.getQuestion());
        values.put(QuestionTable.Cols.TRUE, question.isAnswerTrue() ? 1 : 0);

        return values;
    }

    /**
     * Build a query for Question DB.
     * @param whereClause defines the where clause of a SQL query
     * @param whereArgs defines where arguments for an SQL query
     * @return Object defining an SQL query
     */
    private QuestionCursorWrapper queryQuestions(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                QuestionTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new QuestionCursorWrapper(cursor);
    }

    /**
     * Update information for a given question.
     * @param question contains the latest information for the question.
     */
    public void updateQuestion(Question question) {
        String uuidString = question.getId().toString();
        ContentValues values = getContentValues(question);

        mDatabase.update(QuestionTable.NAME, values,
                QuestionTable.Cols.UUID + " = ?",
                new String[] {uuidString});
    }

    /**
     * Delete a given question from the list of questions
     * @param question is the index of the question to delete
     */
    public void deleteQuestion(Question question) {
        String uuidString = question.getId().toString();
        mDatabase.delete(QuestionTable.NAME,
                QuestionTable.Cols.UUID + " = ? ",
                new String [] {uuidString});
    }

    /**
     * Add a question to the list.
     * @param question is the question to add.
     */
    public void addQuestion(Question question) {
        ContentValues values = getContentValues(question);
        mDatabase.insert(QuestionTable.NAME, null, values);
    }


    /**
     * Return the question with a given id.
     * @param id Unique id for the question
     * @return The question object or null if not found.
     */
    public Question getQuestion(UUID id) {
        QuestionCursorWrapper cursor = queryQuestions(QuestionTable.Cols.UUID + " = ?",
                new String[] {id.toString()});
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getQuestion();
        } finally {
            cursor.close();
        }
    }

    /** Private Constructor */
    private QuestionList(Context appContext) {
        mAppContext = appContext.getApplicationContext();

        //Open DB file or create it if it does not already exist.
        //If the DB is older version, onUpgrade will called.
        mDatabase = new QuestionDbHelper(mAppContext).getWritableDatabase();
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
    public ArrayList<Question> getQuestions() {
        ArrayList<Question> questions = new ArrayList<>();
        QuestionCursorWrapper cursor = queryQuestions(null, null);

        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                questions.add(cursor.getQuestion());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
    return questions;
    }
}
