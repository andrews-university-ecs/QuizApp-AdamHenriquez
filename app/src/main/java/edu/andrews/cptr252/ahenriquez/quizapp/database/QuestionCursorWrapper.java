package edu.andrews.cptr252.ahenriquez.quizapp.database;

import android.database.Cursor;
import android.database.CursorWrapper;
import java.util.Date;
import java.util.UUID;

import edu.andrews.cptr252.ahenriquez.quizapp.Question;
import edu.andrews.cptr252.ahenriquez.quizapp.database.QuestionDbSchema;

/**
 * Handle results from DB queries
 */
public class QuestionCursorWrapper extends CursorWrapper {
    public QuestionCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    /**
     * Extract question information from a query
     * @return Question that resulted from the query
     */
    public Question getQuestion() {
        String uuidString = getString(getColumnIndex(QuestionDbSchema.QuestionTable.Cols.UUID));
        String question = getString(getColumnIndex(QuestionDbSchema.QuestionTable.Cols.QUESTION));
        int isTrue = getInt(getColumnIndex(QuestionDbSchema.QuestionTable.Cols.TRUE));

        Question question1 = new Question(UUID.fromString(uuidString));
        question1.setQuestion(question);
        question1.setAnswerTrue(isTrue != 0);
        return question1;
    }
}
