package edu.andrews.cptr252.ahenriquez.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class QuizQuestionsActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new QuizQuestionsFragment();
    }
}