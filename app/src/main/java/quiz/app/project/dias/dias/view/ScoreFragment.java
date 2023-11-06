package quiz.app.project.dias.dias.view;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import quiz.app.project.dias.dias.model.QuizDatabase;
import quiz.app.project.dias.dias.model.user.User;
import quiz.app.project.dias.dias.R;
import quiz.app.project.dias.dias.viewmodel.ScoreViewModel;
import quiz.app.project.dias.dias.viewmodel.ThemeViewModel;
import quiz.app.project.dias.dias.viewmodel.UserViewModel;

public class ScoreFragment extends Fragment {
    TextView lblUsernameScore;
    private int userId;
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private ScoreAdapter adapter;
    private ScoreViewModel scoreViewModel;
    private ThemeViewModel themeViewModel;
    private UserViewModel userViewModel;

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
        scoreViewModel = new ScoreViewModel(getActivity().getApplication());
        themeViewModel = new ThemeViewModel(getActivity().getApplication());
    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_score, container, false);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        userId = sharedPreferences.getInt("userId", 0);

        recyclerView = rootView.findViewById(R.id.recyclerViewProfile);
        // Get instances of the ChatDao and MessagesDao from the AppDatabase
        QuizDatabase db = QuizDatabase.getInstance(this.getContext());

        // Create an instance of the ChatAdapter and pass the necessary data
        this.adapter = new ScoreAdapter(scoreViewModel.getScores(userId).getValue(), themeViewModel.getThemes().getValue());

        // Create a LinearLayoutManager for the RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());

        // Set the adapter and layout manager for the RecyclerView
        recyclerView.setAdapter(this.adapter);
        recyclerView.setLayoutManager(layoutManager);

        lblUsernameScore = rootView.findViewById(R.id.lblUsernameScore);

        if (userId != 0) {
            QuizDatabase quizDB = QuizDatabase.getInstance(requireContext());

            User user = userViewModel.getUserById(userId).getValue();

            if (user != null) {
                String username = user.getUsername();
                lblUsernameScore.setText(username);
            }
        }
        return rootView;
    }
}