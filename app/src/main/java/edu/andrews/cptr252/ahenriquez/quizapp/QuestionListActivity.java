package edu.andrews.cptr252.ahenriquez.quizapp;

import androidx.fragment.app.Fragment;

/**
 * Activity for displaying list of questions
 */

public class QuestionListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {
        return new QuestionListFragment();
    }
}
