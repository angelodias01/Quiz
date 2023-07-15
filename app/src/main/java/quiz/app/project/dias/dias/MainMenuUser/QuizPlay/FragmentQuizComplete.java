package quiz.app.project.dias.dias.MainMenuUser.QuizPlay;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import quiz.app.project.dias.dias.MainMenuUser.MainMenuUser;
import quiz.app.project.dias.dias.QuizDatabase.QuizDatabase;
import quiz.app.project.dias.dias.QuizDatabase.ThemeDB.Theme;
import quiz.app.project.dias.dias.R;

public class FragmentQuizComplete extends Fragment {

    private static final String ARG_SCORE = "score";
    private static final String ARG_THEME_ID = "themeId";
    private static final String ARG_THEME_NAME = "themeName";

    private int score;
    private int themeId;
    private String themeName;
    private Button buttonFinish;

    public FragmentQuizComplete() {
        // Required empty public constructor
    }

    public static FragmentQuizComplete newInstance(int score, int themeId, String themeName) {
        FragmentQuizComplete fragment = new FragmentQuizComplete();
        Bundle args = new Bundle();
        args.putInt(ARG_SCORE, score);
        args.putInt(ARG_THEME_ID, themeId);
        args.putString(ARG_THEME_NAME, themeName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            score = getArguments().getInt(ARG_SCORE);
            themeId = getArguments().getInt(ARG_THEME_ID);
            themeName = getArguments().getString(ARG_THEME_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quiz_complete, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView textViewCongrats = view.findViewById(R.id.textViewCongrats);
        TextView textViewTheme = view.findViewById(R.id.textViewTheme);
        TextView textViewScore = view.findViewById(R.id.textViewScore);

        if (themeName != null) {
            textViewTheme.setText(themeName);
            textViewScore.setText(getString(R.string.score_text, score));
        } else {
            // Handle error, theme name not found
            textViewTheme.setText(getString(R.string.theme_text, "Unknown Theme"));
            textViewScore.setText(getString(R.string.score_text, score));
        }

        buttonFinish = view.findViewById(R.id.buttonFinish);
        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the main menu home screen
                Intent intent = new Intent(getActivity(), MainMenuUser.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Clear the activity stack
                startActivity(intent);
                getActivity().finish(); // Finish the current activity
            }
        });
    }
}
