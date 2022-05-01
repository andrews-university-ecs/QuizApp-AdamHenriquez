package edu.andrews.cptr252.ahenriquez.quizapp;

import androidx.fragment.app.Fragment;

import edu.andrews.cptr252.ahenriquez.quizapp.QuizModeFragment;
import edu.andrews.cptr252.ahenriquez.quizapp.SingleFragmentActivity;

public class QuizModeActivity extends SingleFragmentActivity {

        @Override
        protected Fragment createFragment() {
                return new QuizModeFragment();
        }
}

