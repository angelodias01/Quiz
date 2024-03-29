/**
 * DefinitionsFragment.java
 * Represents the definitions/settings fragment of the application.
 */

package quiz.app.project.dias.dias.view;

import android.app.AlertDialog;
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
    Button btnDeleteScores, btnDeleteAchievements, btnDeleteAll;
    private UserViewModel userViewModel;
    private ScoreViewModel scoreViewModel;
    private AchievementUserViewModel achievementUserViewModel;
    private UserCurrencyViewModel userCurrencyViewModel;

    /**
     * Default constructor for DefinitionsFragment.
     */
    public DefinitionsFragment() {
        // Required empty public constructor
    }

    /**
     * Creates a new instance of DefinitionsFragment.
     * @return A new instance of DefinitionsFragment.
     */
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
        // Button click listeners for logout, delete scores, delete achievements, and delete all data
        btnLogout.setOnClickListener(v -> handleLogoutClick());
        btnDeleteScores.setOnClickListener(v -> handleDeleteScoresClick());
        btnDeleteAchievements.setOnClickListener(v -> handleDeleteAchievementsClick());
        btnDeleteAll.setOnClickListener(v -> handleDeleteAllDataClick());

        return rootView;
    }

    /**
     * Handles the click event for the logout button.
     * Prompts the user with a confirmation dialog and logs out if confirmed.
     */
    private void handleLogoutClick() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle(R.string.logout);
        builder.setMessage(R.string.logoutConf);
        builder.setPositiveButton(R.string.yes, (dialog, which) -> {
            // Clear user session and navigate to the login screen
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isLoggedIn", false);
            editor.remove("userId");
            editor.apply();

            getActivity().finishAffinity();
            Intent intent = new Intent(getActivity(), MainActivity.class);
            getActivity().startActivity(intent);
        });
        builder.setNegativeButton(R.string.no, (dialog, which) -> {
            dialog.dismiss();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * Handles the click event for the delete scores button.
     * Prompts the user with a confirmation dialog and deletes scores if confirmed.
     */
    private void handleDeleteScoresClick() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle(R.string.deleteScores);
        builder.setMessage(R.string.deleteScoresConf);
        builder.setPositiveButton(R.string.yes, (dialog, which) -> {
            // Delete scores for the current user
            if (userId != 0) {
                scoreViewModel.deleteScoresByUserId(userId);
                Toast.makeText(requireContext(), R.string.deleteScoresSuccess, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton(R.string.no, (dialog, which) -> {
            dialog.dismiss();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * Handles the click event for the delete achievements button.
     * Prompts the user with a confirmation dialog and deletes achievements if confirmed.
     */
    private void handleDeleteAchievementsClick() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle(R.string.deleteAchievements);
        builder.setMessage(R.string.deleteAchievementsConf);
        builder.setPositiveButton(R.string.yes, (dialog, which) -> {
            // Delete achievements for the current user
            if (userId != 0) {
                achievementUserViewModel.deleteAchievementsByUserId(userId);
                Toast.makeText(requireContext(), R.string.deleteAllDataSuccess , Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton(R.string.no, (dialog, which) -> {
            dialog.dismiss();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * Handles the click event for the delete all data button.
     * Prompts the user with a confirmation dialog and deletes all data if confirmed.
     */
    private void handleDeleteAllDataClick() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle(R.string.deleteAllData);
        builder.setMessage(R.string.deleteAllDataConf);
        builder.setPositiveButton(R.string.yes, (dialog, which) -> {
            // Delete scores, achievements, and update currency for the current user
            if (userId != 0) {
                scoreViewModel.deleteScoresByUserId(userId);
                achievementUserViewModel.deleteAchievementsByUserId(userId);
                userCurrencyViewModel.updateValue(userId);
                Toast.makeText(requireContext(), R.string.deleteAllDataSuccess, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton(R.string.no, (dialog, which) -> {
            dialog.dismiss();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}