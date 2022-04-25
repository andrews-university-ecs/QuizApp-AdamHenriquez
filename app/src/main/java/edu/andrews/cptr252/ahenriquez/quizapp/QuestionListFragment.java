package edu.andrews.cptr252.ahenriquez.quizapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    /**
     * Recycler view that displays lists of questions
     */
    private RecyclerView mRecyclerView;

    /**
     * Adapter that generates/reuses views to display bugs
     */
    private QuestionAdapter mQuestionAdapter;

    public QuestionListFragment() {
        //Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.questions_list_label);
        mQuestions = QuestionList.getInstance(getActivity()).getQuestions();

        mQuestionAdapter = new QuestionAdapter(mQuestions);

        //for now list bugs in log
        for (Quiz quiz: mQuestions) {
            Log.d(TAG, quiz.getQuestion());
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quiz_list, container, false);

        mRecyclerView = v.findViewById(R.id.question_list_recyclerView);
        // RecyclerView will use QuestionAdapter to create views for questions
        mRecyclerView.setAdapter(mQuestionAdapter);
        // Use a linear layout when displaying questions
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return v;
    }

    /**
     * Question list fragment was paused (user was most likely editing a question).
     * Notify the adapter that the data set (question list) may have changed.
     * The adapter should update the views.
     */
    @Override
    public void onResume() {
        super.onResume(); //first execute parent's onResume method
        mQuestionAdapter.notifyDataSetChanged();
    }
}
