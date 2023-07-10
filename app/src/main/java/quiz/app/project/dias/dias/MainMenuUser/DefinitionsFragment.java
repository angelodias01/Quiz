package quiz.app.project.dias.dias.MainMenuUser;

import static android.content.ContentValues.TAG;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import quiz.app.project.dias.dias.QuizDatabase.AchievementUserDB.AchievementUser;
import quiz.app.project.dias.dias.QuizDatabase.AchievementUserDB.AchievementUserDao;
import quiz.app.project.dias.dias.QuizDatabase.AchievementsDB.AchievementsDao;
import quiz.app.project.dias.dias.QuizDatabase.QuestionsDB.QuestionsDao;
import quiz.app.project.dias.dias.QuizDatabase.QuizDatabase;
import quiz.app.project.dias.dias.QuizDatabase.ScoreDB.Score;
import quiz.app.project.dias.dias.QuizDatabase.ScoreDB.ScoreDao;
import quiz.app.project.dias.dias.QuizDatabase.ThemeDB.ThemeDao;
import quiz.app.project.dias.dias.QuizDatabase.UserCurrencyDB.UserCurrencyDao;
import quiz.app.project.dias.dias.QuizDatabase.UserDB.User;
import quiz.app.project.dias.dias.QuizDatabase.UserDB.UserDao;
import quiz.app.project.dias.dias.R;
import quiz.app.project.dias.dias.mainActivityFragments.MainActivity;

public class DefinitionsFragment extends Fragment {
    private int userId;
    TextView lblUsernameDefinitions;
    Button btnDeleteScores,btnDeleteAchievements,btnDeleteStatistics, btnDeleteAll;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_definitions, container, false);

        lblUsernameDefinitions = rootView.findViewById(R.id.lblUsernameDefinitions);
        ImageButton btnLogout = rootView.findViewById(R.id.btnLogoutDefinitions);
        btnDeleteScores = rootView.findViewById(R.id.btnDeleteScoresDefinitions);
        btnDeleteAchievements = rootView.findViewById(R.id.btnDeleteAchievementsDefinitions);
        btnDeleteStatistics = rootView.findViewById(R.id.btnDeleteStatisticsDefinitions);
        btnDeleteAll = rootView.findViewById(R.id.btnDeleteAllDefinitions);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        userId = sharedPreferences.getInt("userId", 0);

        if (userId != 0) {
            QuizDatabase quizDB = QuizDatabase.getInstance(requireContext());
            UserDao userDao = quizDB.getUserDao();

            User user = userDao.getUserById(userId);

            if (user != null) {
                String username = user.getUsername();
                lblUsernameDefinitions.setText(username);
            }
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
                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle();
                getActivity().startActivity(intent, bundle);
            }
        });
        btnDeleteScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuizDatabase quizDB = QuizDatabase.getInstance(requireContext());
                UserDao userDao = quizDB.getUserDao();
                ScoreDao scoreDao = quizDB.getScoreDao();

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
                userId = sharedPreferences.getInt("userId", 0);
                User user = userDao.getUserById(userId);

                if (user != null) {
                    scoreDao.deleteScoresByUserId(userId);
                    Toast.makeText(requireContext(), "Scores deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnDeleteAchievements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuizDatabase quizDB = QuizDatabase.getInstance(requireContext());
                UserDao userDao = quizDB.getUserDao();
                AchievementUserDao achievementUserDao = quizDB.getAchievementUserDao();
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
                userId = sharedPreferences.getInt("userId", 0);
                User user = userDao.getUserById(userId);

                if (user != null) {
                    achievementUserDao.deleteAchievementsByUserId(userId);
                    Toast.makeText(requireContext(), "Achievements deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDeleteStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuizDatabase quizDB = QuizDatabase.getInstance(requireContext());
                UserDao userDao = quizDB.getUserDao();
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
                userId = sharedPreferences.getInt("userId", 0);
                User user = userDao.getUserById(userId);

                if (user != null) {

                }
            }
        });

        btnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuizDatabase quizDB = QuizDatabase.getInstance(requireContext());
                UserDao userDao = quizDB.getUserDao();
                ScoreDao scoreDao = quizDB.getScoreDao();
                AchievementUserDao achievementUserDao = quizDB.getAchievementUserDao();
                UserCurrencyDao userCurrencyDao = quizDB.getUserCurrencyDao();
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
                userId = sharedPreferences.getInt("userId", 0);
                User user = userDao.getUserById(userId);

                if (user != null) {
                    scoreDao.deleteScoresByUserId(userId);
                    achievementUserDao.deleteAchievementsByUserId(userId);
                    userCurrencyDao.updateValue(userId);
                    Toast.makeText(requireContext(), "All data deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return rootView;
    }
}