package edu.andrews.cptr252.ahenriquez.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    //Create a new question, add it to the list and launch quiz editor.
    private void addQuestion() {
        //Create new question
        Quiz question = new Quiz();
        //add question to the list
        QuestionList.getInstance(getActivity()).addQuestion(question);
        //create an intent to send to QuizQuestionsActivity
        //add the question ID as an extra so QuizQuestionsFragment can edit it.
        Intent intent = new Intent(getActivity(), QuizQuestionsActivity.class);
        intent.putExtra(QuizQuestionsFragment.EXTRA_QUESTION_ID, question.getId());
        //launch QuizQuestionsActivity which will launch QuizQuestionsFragment
        startActivityForResult(intent, 0);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        //Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_question_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_add_question:
                //new question icon clicked
                addQuestion();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

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
