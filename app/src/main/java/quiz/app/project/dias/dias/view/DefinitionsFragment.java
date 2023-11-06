package quiz.app.project.dias.dias.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import quiz.app.project.dias.dias.model.QuizDatabase;
import quiz.app.project.dias.dias.model.user.User;
import quiz.app.project.dias.dias.R;
import quiz.app.project.dias.dias.viewmodel.AchievementUserViewModel;
import quiz.app.project.dias.dias.viewmodel.ScoreViewModel;
import quiz.app.project.dias.dias.viewmodel.UserCurrencyViewModel;
import quiz.app.project.dias.dias.viewmodel.UserViewModel;

public class DefinitionsFragment extends Fragment {
    private int userId;
    TextView lblUsernameDefinitions;
    Button btnDeleteScores,btnDeleteAchievements,btnDeleteStatistics, btnDeleteAll;
    private UserViewModel userViewModel;
    private ScoreViewModel scoreViewModel;
    private AchievementUserViewModel achievementUserViewModel;
    private UserCurrencyViewModel userCurrencyViewModel;

    public DefinitionsFragment() {
        // Required empty public constructor
    }
    public static DefinitionsFragment newInstance() {
        DefinitionsFragment fragment = new DefinitionsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        scoreViewModel = new ViewModelProvider(this).get(ScoreViewModel.class);
        userCurrencyViewModel = new ViewModelProvider(this).get(UserCurrencyViewModel.class);
        achievementUserViewModel = new ViewModelProvider(this).get(AchievementUserViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_definitions, container, false);

        QuizDatabase quizDB = QuizDatabase.getInstance(requireContext());

        lblUsernameDefinitions = rootView.findViewById(R.id.lblUsernameDefinitions);
        ImageButton btnLogout = rootView.findViewById(R.id.btnLogoutDefinitions);
        btnDeleteScores = rootView.findViewById(R.id.btnDeleteScoresDefinitions);
        btnDeleteAchievements = rootView.findViewById(R.id.btnDeleteAchievementsDefinitions);
        btnDeleteStatistics = rootView.findViewById(R.id.btnDeleteStatisticsDefinitions);
        btnDeleteAll = rootView.findViewById(R.id.btnDeleteAllDefinitions);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        userId = sharedPreferences.getInt("userId", 0);

        if (userId != 0) {
            LiveData<User> userLiveData = userViewModel.getUserById(userId);
            userLiveData.observe(getViewLifecycleOwner(), user -> {
                if (user != null) {
                    String username = user.getUsername();
                    lblUsernameDefinitions.setText(username);
                }
            });
        }
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLoggedIn", false);
                editor.remove("userId");
                editor.apply();

                getActivity().finishAffinity();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                getActivity().startActivity(intent);
            }
        });
        btnDeleteScores.setOnClickListener(v -> {
            if (userId != 0) {
                scoreViewModel.deleteScoresByUserId(userId);
                Toast.makeText(requireContext(), "Scores deleted", Toast.LENGTH_SHORT).show();
            }
        });

        btnDeleteAchievements.setOnClickListener(v -> {
            if (userId != 0) {
                achievementUserViewModel.deleteAchievementsByUserId(userId);
                Toast.makeText(requireContext(), "Achievements deleted", Toast.LENGTH_SHORT).show();
            }
        });

        btnDeleteStatistics.setOnClickListener(v -> {
            if (userId != 0) {
                Toast.makeText(requireContext(), "Statistics deleted", Toast.LENGTH_SHORT).show();
            }
        });

        btnDeleteAll.setOnClickListener(v -> {
            if (userId != 0) {
                scoreViewModel.deleteScoresByUserId(userId);
                achievementUserViewModel.deleteAchievementsByUserId(userId);
                userCurrencyViewModel.updateValue(userId);
                Toast.makeText(requireContext(), "All data deleted", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
}