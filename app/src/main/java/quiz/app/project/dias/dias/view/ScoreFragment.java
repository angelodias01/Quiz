package quiz.app.project.dias.dias.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import quiz.app.project.dias.dias.model.QuizDatabase;
import quiz.app.project.dias.dias.model.score.Score;
import quiz.app.project.dias.dias.model.theme.Theme;
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
        userViewModel = new UserViewModel(getActivity().getApplication());
    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_score, container, false);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        userId = sharedPreferences.getInt("userId", 0);

        recyclerView = rootView.findViewById(R.id.recyclerViewProfile);

        // Create a LinearLayoutManager for the RecyclerView
        layoutManager = new LinearLayoutManager(requireContext());

        // Set the adapter and layout manager for the RecyclerView
        adapter = new ScoreAdapter(new ArrayList<>(), new ArrayList<>());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        lblUsernameScore = rootView.findViewById(R.id.lblUsernameScore);

        userViewModel.getUserById(userId).observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                String username = user.getUsername();
                lblUsernameScore.setText(username);
            }
        });

        scoreViewModel.getScoresByUserId(userId).observe(getViewLifecycleOwner(), score -> {
            themeViewModel.getThemes().observe(getViewLifecycleOwner(), theme -> {
                adapter.refreshList(score, theme);
            });
        });

        adapter.setOnLongClickListener(position -> {
            handleLongClick(position);
        });
        adapter.setOnClickListener(position -> {
            handleClick(position);
        });

        return rootView;
    }

    private void handleLongClick(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle(R.string.deleteScores);
        builder.setMessage(R.string.deleteScoresConf);
        builder.setPositiveButton(R.string.yes, (dialog, which) -> {
            // Handle deletion based on the clicked position
            if (position >= 0 && position < adapter.getItemCount()) {
                ScoreAdapter.ScoreWithTheme scoreWithTheme = adapter.getItem(position);
                if (scoreWithTheme != null) {
                    deleteScore(scoreWithTheme.score);
                }
            }
        });
        builder.setNegativeButton(R.string.no, (dialog, which) -> {
            dialog.dismiss();
        });

        // Show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void handleClick(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog);
        View dialogView = getLayoutInflater().inflate(R.layout.custom_dialog_layout, null);
        builder.setView(dialogView);

        TextView textViewTitle = dialogView.findViewById(R.id.textViewTitle);
        TextView textViewMessage = dialogView.findViewById(R.id.textViewMessage);
        Button btnClose = dialogView.findViewById(R.id.btnClose);

        // Set your title and message here
        textViewTitle.setText(getString(R.string.ScoreInfo));

        if (position >= 0 && position < adapter.getItemCount()) {
            ScoreAdapter.ScoreWithTheme scoreWithTheme = adapter.getItem(position);
            if (scoreWithTheme != null) {
                // Extract details from ScoreWithTheme
                String themeName = scoreWithTheme.theme != null ? scoreWithTheme.theme.getThemeName() : "";
                String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                        .format(scoreWithTheme.score.getDate());
                int scoreValue = scoreWithTheme.score.getScore();

                // Build the message with theme name, date, and score
                String message = "\n" + "Theme: " +themeName + "\n\n " +
                        "Date: " + currentDate + "\n\n" +
                        "Score: " + scoreValue+ " / 7"+ "\n";

                textViewMessage.setText(message);
            }
        }
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        btnClose.setOnClickListener(view -> {
            // Handle close button click
            alertDialog.dismiss();
        });
    }

    private List<ScoreAdapter.ScoreWithTheme> mergeScoreWithTheme(List<Score> scoreList, List<Theme> themeList) {
        List<ScoreAdapter.ScoreWithTheme> mergedList = new ArrayList<>();
        if (themeList == null || themeList.isEmpty()) {
            return mergedList;
        }

        for (Score score : scoreList) {
            Theme theme = findThemeById(themeList, score.getThemeId());
            if (theme != null) {
                mergedList.add(new ScoreAdapter.ScoreWithTheme(score, theme));
            }
        }
        return mergedList;
    }

    private Theme findThemeById(List<Theme> themeList, int themeId) {
        for (Theme theme : themeList) {
            if (theme.getThemeId() == themeId) {
                return theme;
            }
        }
        return null;
    }

    private void deleteScore(Score score) {
        scoreViewModel.deleteScore(score);
    }
}
