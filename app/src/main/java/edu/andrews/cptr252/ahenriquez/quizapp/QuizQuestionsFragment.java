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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuizDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

/** show the details for a Quiz and allow editing
 */


public class QuizQuestionsFragment extends Fragment {

    /* Tag for logging fragment messages */
    public static final String TAG = "QuizQuestionsFragment";
    /* Bug that is being viewed or edited */
    private Quiz mQuiz;
    /* Reference to title field for quiz */
    private EditText mQuestionField;
    /** Reference to true or false button */
    private ToggleButton mTrueFalseButton;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QuizQuestionsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuizQuestionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuizQuestionsFragment newInstance(String param1, String param2) {
        QuizQuestionsFragment fragment = new QuizQuestionsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mQuiz = new Quiz(); //Create new question
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quiz_details, container, false);

        // get reference to EditText box for quiz title
        mQuestionField = v.findViewById(R.id.quiz_title);
        mQuestionField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //intentionally blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // User typed text, update the quiz title
                mQuiz.setQuestion(s.toString());
                //write the new title to the message log for debugging
                Log.d(TAG, "Question changed to " + mQuiz.getQuestion());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //intentionally blank
            }
        });

        /** Reference to true or false button*/
        mTrueFalseButton = v.findViewById(R.id.answer_true);

        mTrueFalseButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Question is true
                    mQuiz.setAnswerTrue(true);
                } else {
                    // Question is false
                    mQuiz.setAnswerTrue(false);
                }

            }
        });

        return v;
    }
}