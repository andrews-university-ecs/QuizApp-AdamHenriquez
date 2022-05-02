package edu.andrews.cptr252.ahenriquez.quizapp;

import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Final commit comment
 */

public class Question {
/* UUID class is used to automatically create new unique ids for a specific question*/

    /* The question for the quiz */
    private String mQuestion;

    // The true and false button for the question
    private boolean mAnswerTrue;

    //random ID for the question
    private UUID mId;

    /* Create and initialize new Question */
    public Question() {
        //Generate unique identifier for new Question
        this(UUID.randomUUID());
    }

    //Used from lab 03
    public Question(String question) {
        mQuestion = question;
    }

    //JSON attribute for question id
    private static final String JSON_ID = "id";
    //JSON attribute for question
    private static final String JSON_QUESTION = "question";
    //JSON attribute for question true status
    private static final String JSON_TRUE = "true";

    /**
     * Initialize a new question from a JSON object
     * @param json is the JSON object for a question
     * @throws JSONException
     */
    public Question(JSONObject json) throws JSONException {
        mId = UUID.fromString(json.getString(JSON_ID));
        mQuestion = json.optString(JSON_QUESTION);
        mAnswerTrue = json.getBoolean(JSON_TRUE);
    }

    /**
     *Write the question to a JSON object
     * @return JSON object containing the question information
     * @throws JSONException
     */
    public JSONObject toJSON() throws JSONException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put(JSON_ID, mId.toString());
        jsonObject.put(JSON_QUESTION, mQuestion);
        jsonObject.put(JSON_TRUE, mAnswerTrue);

        return jsonObject;
    }



    public Question(UUID id) {
        mId = id;
    }
    /* Return unique id for question
    @return Question id
     */
    public String getQuestion() {
        return mQuestion;
    }
    /* provide new question for Question
    @param question New question
     */
    public void setQuestion(String title) {
        mQuestion = title;
    }

    public UUID getId() {
        return mId;
    }

    //getters and setters for private fields
    public boolean isAnswerTrue() { return mAnswerTrue;}

    public void setAnswerTrue(boolean answerTrue) { this.mAnswerTrue = answerTrue; }
}


