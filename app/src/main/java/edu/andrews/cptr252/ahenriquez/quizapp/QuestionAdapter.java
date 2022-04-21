package edu.andrews.cptr252.ahenriquez.quizapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Adapter is responsible for getting the view for a question
 */

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private static final String TAG = "QuizAdapter";

    /**
     * Used to store reference to list of questions
     */
    private ArrayList<Quiz> mQuestions;

    /**
     * Constructor for QuestionAdapter. Initialize adapter with given list of bugs.
     * @param questions list of questions to display
     */
    public QuestionAdapter(ArrayList<Quiz> questions) {
        mQuestions = questions;
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
         * Create a new view holder for a given view item in the questions list
         */
        public ViewHolder(View itemView) {
            super(itemView);

            // Store references to the widgets on the view item
            //questionTitleTextView = itemView.findViewById(R.id.question_list_item_questionTextView);
            questionTextView = itemView.findViewById(R.id.question_list_item_questionTextView);
            questionTrueToggleButton = itemView.findViewById(R.id.question_item_list_ToggleButton);

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
                Quiz quiz = mQuestions.get(position);
                Log.d(TAG, quiz.getQuestion() + "was clicked");
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
        Quiz quiz = mQuestions.get(position);

        //get references to widgets stored in ViewHolder
        //TextView questionTitleTextView = viewHolder.questionTitleTextView;
        TextView questionTextView = viewHolder.questionTextView;
        ToggleButton questionTrueToggleButton = viewHolder.questionTrueToggleButton;

        //Update widgets on view with quiz details
        //questionTitleTextView.setText(quiz.getTitle());
        questionTextView.setText(quiz.getQuestion());
        questionTrueToggleButton.setChecked(quiz.isAnswerTrue());
    }

    /**
     * get number of questions in the questions list
     */
    @Override
    public int getItemCount() {
        return mQuestions.size();
    }

}
