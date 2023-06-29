package quiz.app.project.dias.dias.MainMenuUser;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import quiz.app.project.dias.dias.QuizDatabase.QuizDatabase;
import quiz.app.project.dias.dias.QuizDatabase.UserDB.User;
import quiz.app.project.dias.dias.QuizDatabase.UserDB.UserDao;
import quiz.app.project.dias.dias.R;

public class DefinitionsFragment extends Fragment {
    private int userId;
    TextView lblUsernameDefinitions;

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
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        userId = sharedPreferences.getInt("userId", 0);

        if (userId != 0) {
            QuizDatabase quizDB = QuizDatabase.getInstance(requireContext());
            UserDao userDao = quizDB.getUserDao();

            User user = userDao.getUserById(userId);

            if (user != null) {
                String username = user.getUsername();
                lblUsernameDefinitions.setText(username);
            } else {
                lblUsernameDefinitions.setText("Error Loading Username!");
            }
        }
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        userId = sharedPreferences.getInt("userId", 0);

        if (userId != 0) {
            QuizDatabase quizDB = QuizDatabase.getInstance(requireContext());
            UserDao userDao = quizDB.getUserDao();

            User user = userDao.getUserById(userId);

            if (user != null) {
                String username = user.getUsername();
                lblUsernameDefinitions.setText(username);
            } else {
                lblUsernameDefinitions.setText("Error Loading Username!");
            }
        }
    }

    public View onResumeView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_definitions, container, false);
        lblUsernameDefinitions = rootView.findViewById(R.id.lblUsernameDefinitions);
        return rootView;
    }
}