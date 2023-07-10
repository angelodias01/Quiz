package quiz.app.project.dias.dias.MainMenuUser;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import quiz.app.project.dias.dias.QuizDatabase.QuizDatabase;
import quiz.app.project.dias.dias.QuizDatabase.ScoreDB.Score;
import quiz.app.project.dias.dias.QuizDatabase.ScoreDB.ScoreDao;
import quiz.app.project.dias.dias.QuizDatabase.ThemeDB.ThemeDao;
import quiz.app.project.dias.dias.QuizDatabase.UserDB.User;
import quiz.app.project.dias.dias.QuizDatabase.UserDB.UserDao;
import quiz.app.project.dias.dias.R;
import quiz.app.project.dias.dias.databinding.FragmentScoreBinding;

public class ScoreFragment extends Fragment {
    TextView lblUsernameScore;
    private int userId;
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private ScoreAdapter adapter;

    public ScoreFragment() {
        // Required empty public constructor
    }

    public static ScoreFragment newInstance() {
        ScoreFragment fragment = new ScoreFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_score, container, false);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        userId = sharedPreferences.getInt("userId", 0);

        recyclerView = rootView.findViewById(R.id.recyclerViewScore);
        // Get instances of the ChatDao and MessagesDao from the AppDatabase
        QuizDatabase db = QuizDatabase.getInstance(this.getContext());
        ScoreDao scoreDao = db.getScoreDao();
        ThemeDao themeDao = db.getThemeDao();

        // Create an instance of the ChatAdapter and pass the necessary data
        this.adapter = new ScoreAdapter(scoreDao.getScores(userId), themeDao.getThemes());

        // Create a LinearLayoutManager for the RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());

        // Set the adapter and layout manager for the RecyclerView
        recyclerView.setAdapter(this.adapter);
        recyclerView.setLayoutManager(layoutManager);

        lblUsernameScore = rootView.findViewById(R.id.lblUsernameScore);

        if (userId != 0) {
            QuizDatabase quizDB = QuizDatabase.getInstance(requireContext());
            UserDao userDao = quizDB.getUserDao();

            User user = userDao.getUserById(userId);

            if (user != null) {
                String username = user.getUsername();
                lblUsernameScore.setText(username);
            }
        }
        return rootView;
    }
}