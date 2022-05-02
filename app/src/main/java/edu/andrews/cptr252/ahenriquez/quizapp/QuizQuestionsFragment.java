package edu.andrews.cptr252.ahenriquez.quizapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuizDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

/** show the details for a Question and allow editing
 */


public class QuizQuestionsFragment extends Fragment {

    /* Tag for logging fragment messages */
    public static final String TAG = "QuizQuestionsFragment";
    /* Bug that is being viewed or edited */
    private Question mQuestion;
    /* Reference to title field for quiz */
    private EditText mQuestionField;
    /** Reference to true or false button */
    private ToggleButton mTrueFalseButton;

    /**
     * Key used to pass the id of a bug
     */
    public static final String EXTRA_QUESTION_ID = "edu.andrews.cptr252.ahenriquez.quizapp.question_id";

    /**
     * Create a new QuizQuestionsFragment with a given question id as an argument.
     * @param questionId
     * @return A reference to the new QuizQuestionsFragment
     * @return A new instance of fragment QuizQuestionsFragment.
     */
    public static QuizQuestionsFragment newInstance(UUID questionId) {
        QuizQuestionsFragment fragment = new QuizQuestionsFragment();
        Bundle args = new Bundle();

        args.putSerializable(EXTRA_QUESTION_ID, questionId);

        fragment.setArguments(args);
        return fragment;
    }
    public QuizQuestionsFragment() {
        // Required empty public constructor
    }

    /**
     * Save the question to a list to a JSON file when app is paused.
     */
    @Override
    public void onPause() {
        super.onPause();
        QuestionList.getInstance(getActivity()).updateQuestion(mQuestion);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Extract question id from Bundle
        UUID questionID = (UUID)getArguments().getSerializable(EXTRA_QUESTION_ID);

        //Get the question with the id from the bundle
        // This will be the question that the fragment displays
        mQuestion = QuestionList.getInstance(getActivity()).getQuestion(questionID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quiz_details, container, false);

        // get reference to EditText box for quiz title
        mQuestionField = v.findViewById(R.id.quiz_question);
        mQuestionField.setText(mQuestion.getQuestion());
        mQuestionField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //intentionally blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // User typed text, update the quiz title
                mQuestion.setQuestion(s.toString());
                //write the new title to the message log for debugging
                Log.d(TAG, "Question changed to " + mQuestion.getQuestion());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //intentionally blank
            }
        });



        /** Reference to true or false button*/
        mTrueFalseButton = v.findViewById(R.id.answer_true);
        mTrueFalseButton.setChecked(mQuestion.isAnswerTrue());
        mTrueFalseButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Question is true
                    mQuestion.setAnswerTrue(true);
                } else {
                    // Question is false
                    mQuestion.setAnswerTrue(false);
                }

            }
        });



        return v;
    }
}