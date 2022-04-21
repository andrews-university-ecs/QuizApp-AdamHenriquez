package edu.andrews.cptr252.ahenriquez.quizapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

/**
 * Fragment to display list of different questions
 */
public class QuestionListFragment extends Fragment {
    /**
     * Tag for message log
     */
    private static final String TAG = "QuestionsListFragment";

    /**
     * Reference to list of questions in display
     */
    private ArrayList<Quiz> mQuestions;

    public QuestionListFragment() {
        //Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.questions_list_label);
        mQuestions = QuestionList.getInstance(getActivity()).getQuestions();

        //for now list bugs in log
        for (Quiz quiz: mQuestions) {
            Log.d(TAG, quiz.getQuestion());
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quiz_list, container, false);

        return v;
    }
}
