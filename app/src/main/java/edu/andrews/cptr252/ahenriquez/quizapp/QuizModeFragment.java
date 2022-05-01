package edu.andrews.cptr252.ahenriquez.quizapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;


public class QuizModeFragment extends Fragment {

    private static final String TAG = "QuizModeFragment";

    private ArrayList<Question> mQuestion;
    private TextView questionTextView;
    private ToggleButton trueFalseButton;
    private Button mNextButton;
    private Context context;

    int mCurrentIndex;
    int score;


    public QuizModeFragment() {
        //Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuestion = QuestionList.getInstance(getActivity()).getQuestions();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        score = 0;
        mCurrentIndex = 0;
        //inflate the layout for fragment
        View v = inflater.inflate(R.layout.activity_quiz_mode, container, false);
        context = v.getContext();


        questionTextView = v.findViewById(R.id.questionTextView);

        mQuestion = QuestionList.getInstance(getActivity()).getQuestions();
        questionTextView.setText(mQuestion.get(mCurrentIndex).getQuestion());

        if (mQuestion.size() == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("No questions found. Add questions in the question editor");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    Intent i = new Intent(context, QuestionListActivity.class);
                    context.startActivity(i);
                }
            });
            builder.setCancelable(false);
            AlertDialog dialog = builder.create();
            dialog.show();
            Log.d(TAG, "No quiz found");
            return v;
        }



        trueFalseButton = v.findViewById(R.id.trueFalseButton);

        final Toast correct_toast = Toast.makeText(context, "Correct", Toast.LENGTH_SHORT);
        final Toast incorrect_toast = Toast.makeText(context, "Incorrect", Toast.LENGTH_SHORT);
        trueFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mQuestion.get(mCurrentIndex).isAnswerTrue() == true) {
                    score++;
                    if (mCurrentIndex + 1 < mQuestion.size()) {
                        mCurrentIndex++;
                    } else if (mQuestion.get(mCurrentIndex).isAnswerTrue() == false) {
                        score++;
                    } else {
                        endQuiz();
                        return;
                    }
                    Log.d("QuizModeFragment", "current index: " + mCurrentIndex + "Questions size: " + mQuestion.size());
                    correct_toast.show();
                    questionTextView.setText(mQuestion.get(mCurrentIndex).getQuestion());
                } else {
                    if (mCurrentIndex + 1 < mQuestion.size()) {
                        mCurrentIndex++;
                    } else {
                        endQuiz();
                        return;
                    }
                    Log.d("QuizModeFragment", "current index: " + mCurrentIndex + "Questions size: " + mQuestion.size());
                    incorrect_toast.show();
                }
                questionTextView.setText(mQuestion.get(mCurrentIndex).getQuestion());
            }
        });
        return v;
    }

    public void endQuiz() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("You scored a " + score + " out of " + mQuestion.size());
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(context, QuizMenuActivity.class);
                context.startActivity(i);
            }
        });
    }
}
