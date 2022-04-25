package edu.andrews.cptr252.ahenriquez.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import java.util.UUID;

import android.os.Bundle;

public class QuizQuestionsActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {

        //QuestionListFragment now launches QuizDetailsActivity with a specific question id.
        //Get the intent sent to this activity from the QuestionListFragment
        UUID questionId = (UUID)getIntent().getSerializableExtra(QuizQuestionsFragment.EXTRA_QUESTION_ID);

        //Create new instance of the QuizQuestionsFragment
        //With the questionId as an argument
        return QuizQuestionsFragment.newInstance(questionId);
    }
}