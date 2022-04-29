package edu.andrews.cptr252.ahenriquez.quizapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class QuizModeActivity extends AppCompatActivity{
        private TextView mQuestionTextView;
        private Button mNextButton;
        private ToggleButton mToggleButton;
        private int correct = 0;

        private Question[] mQuestionList = new Question[] {
                //new Question
        };

        //Index of current question in the list
        private int mCurrentIndex = 0;

        private void updateQuestion() {
                String question = mQuestionList[mCurrentIndex].getQuestion();

                mQuestionTextView.setText(question);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_quiz_mode);

                //get references to our textviews
                /**
                 * May need to change question_textView to questionTextView
                 */
                mQuestionTextView = findViewById(R.id.question_textView);

                //display the current question
                updateQuestion();

                //set up listener to handle next/submit button presses
                mNextButton = findViewById(R.id.nextButton);
                mNextButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                //move to next question in the list
                                //if index reaches end of the array,
                                //reset index to zero(first question)
                                mCurrentIndex++;
                                if (mCurrentIndex == mQuestionList.length) {
                                        mCurrentIndex = 0;
                                }
                                updateQuestion();
                        }
                });

        }

    }

