package edu.andrews.cptr252.ahenriquez.quizapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

/**
 * Adapter is responsible for getting the view for a question
 */

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private static final String TAG = "QuizAdapter";

    /**
     * Used to store reference to list of questions
     */
    private ArrayList<Question> mQuestions;

    /**
     * Activity hosting the list fragment
     */
    private Activity mActivity;

    /**
     * Constructor for QuestionAdapter. Initialize adapter with given list of bugs.
     * @param questions list of questions to display
     */
    public QuestionAdapter(ArrayList<Question> questions, Activity activity) {
        mQuestions = questions;
        mActivity = activity;
    }

    // Return reference to activity hosting the question list fragment
    public Context getActivity() {
        return mActivity;
    }

    /**
     * Put deleted question back into list
     * @param question to restore
     * @param position in list where question will go
     */
    public void restoreQuestion(Question question, int position) {
        QuestionList.getInstance(mActivity).addQuestion(position, question);
        notifyItemInserted(position);
    }

    /**
     * Create Snackbar with ability to undo question deletion
     */
    private void  showUndoSnackbar(final Question question, final int position) {
        //get root view for activity hosting question list fragment
        View view = mActivity.findViewById(android.R.id.content);
        //build message stating which question was deleted
        String questionDeletedText = mActivity.getString(R.string.question_delete_msg, question.getQuestion());
        //create the snackbar
        Snackbar snackbar = Snackbar.make(view, questionDeletedText, Snackbar.LENGTH_LONG);
        //add the Undo option to the snackbar
        snackbar.setAction(R.string.undo_option, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //undo is selected, restore the deleted item
                restoreQuestion(question, position);
            }
        });
        //Text for UNDO will be yellow
        snackbar.setActionTextColor(Color.YELLOW);
        //display snackbar
        snackbar.show();
    }
    /**
     * Remove question from list
     * @param position index of question to remove
     */
    public void deleteQuestion(int position) {
        //Save deleted question so we can undo the delete if needed
        final Question question = mQuestions.get(position);
        //delete question from list
        QuestionList.getInstance(mActivity).deleteQuestion(position);
        // update list of questions in recyclerview
        notifyItemRemoved(position);
        //display snackbar so user may undo delete
        showUndoSnackbar(question, position);
    }


    /**
     * Class to hold references to widgets on a given view.
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        /**
         * Text view that displays question title
         */
        //public TextView questionTitleTextView;
        /**
         * TextView that displays the question
         */
        public TextView questionTextView;
        /**
         * Toggle Button that displays whether teh question is true or false
         */
        public ToggleButton questionTrueToggleButton;

        /**
         * Context hosting the view
         */
        public Context mContext;





        /**
         * Create a new view holder for a given view item in the questions list
         */
        public ViewHolder(View itemView) {
            super(itemView);

            // Store references to the widgets on the view item
            //questionTitleTextView = itemView.findViewById(R.id.question_list_item_questionTextView);
            questionTextView = itemView.findViewById(R.id.question_list_item_questionTextView);
            questionTrueToggleButton = itemView.findViewById(R.id.question_item_list_ToggleButton);

            //Get the context of the view. This will be the activity hosting the view.
            mContext = itemView.getContext();

            itemView.setOnClickListener(this);
        }






        /**
         * onClick listener for question in the questions list.
         * Triggered when the user clicks on a question in the list
         * @param v is view for the quiz that was clicked
         */
        @Override
        public void onClick(View v) {
            // Get index of a question that was clicked
            int position = getAdapterPosition();
            //For now, just display the question title
            //In the future, open selected question.
            if (position != RecyclerView.NO_POSITION) {
                Question question = mQuestions.get(position);

                //start an instance of QuizQuestionsFragment
                Intent i = new Intent(mContext, QuizQuestionsActivity.class);

                //pass the id of the question as an intent
                i.putExtra(QuizQuestionsFragment.EXTRA_QUESTION_ID, question.getId());
                mContext.startActivity(i);

            }
        }
    } // end ViewHolder
    /**
     * Create a new view to display a question
     * Return the ViewHolder that stores references to the widgets in the new view
     */
    @Override
    public QuestionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Get the layout inflater from parent that contains the bug view item
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the layout to display a question in the list
        View quizView = inflater.inflate(R.layout.list_item_question, parent, false);

        //Create a view holder to store references to the widgets on the new view.
        ViewHolder viewHolder = new ViewHolder(quizView);
        return viewHolder;
    }

    /**
     * Display given question in the view referenced by the given ViewHolder.
     * @param viewHolder Contains references to widgets used to display quiz.
     * @param position Index of the question in the list
     */
    @Override
    public void onBindViewHolder(QuestionAdapter.ViewHolder viewHolder, int position) {
        // Get bug for given index in questions list
        Question question = mQuestions.get(position);

        //get references to widgets stored in ViewHolder
        //TextView questionTitleTextView = viewHolder.questionTitleTextView;
        TextView questionTextView = viewHolder.questionTextView;
        ToggleButton questionTrueToggleButton = viewHolder.questionTrueToggleButton;

        //Update widgets on view with question details
        //questionTitleTextView.setText(question.getQuestion());
        questionTextView.setText(question.getQuestion());
        questionTrueToggleButton.setChecked(question.isAnswerTrue());
    }

    /**
     * get number of questions in the questions list
     */
    @Override
    public int getItemCount() {
        return mQuestions.size();
    }



}
