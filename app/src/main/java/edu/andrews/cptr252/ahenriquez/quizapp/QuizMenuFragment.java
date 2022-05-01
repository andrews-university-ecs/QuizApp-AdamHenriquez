package edu.andrews.cptr252.ahenriquez.quizapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class QuizMenuFragment extends Fragment {

    private Button quiz_mode_button;
    private Button edit_quiz_button;
    private TextView menu_textView;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public QuizMenuFragment() {
        //require empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_quiz_menu, container, false);

        quiz_mode_button = v.findViewById(R.id.quizModeButton);
        edit_quiz_button = v.findViewById(R.id.quizEditButton);

        context = v.getContext();

        quiz_mode_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, edu.andrews.cptr252.ahenriquez.quizapp.QuizModeActivity.class);
                context.startActivity(i);
            }
        });

        edit_quiz_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, edu.andrews.cptr252.ahenriquez.quizapp.QuestionListActivity.class);
                context.startActivity(i);
            }
        });
        return v;
    }
}
