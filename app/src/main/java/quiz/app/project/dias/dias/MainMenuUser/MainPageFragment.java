package quiz.app.project.dias.dias.MainMenuUser;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import quiz.app.project.dias.dias.QuizDatabase.QuizDatabase;
import quiz.app.project.dias.dias.QuizDatabase.ThemeDB.ThemeDao;
import quiz.app.project.dias.dias.QuizDatabase.UserCurrencyDB.UserCurrency;
import quiz.app.project.dias.dias.QuizDatabase.UserCurrencyDB.UserCurrencyDao;
import quiz.app.project.dias.dias.QuizDatabase.UserDB.User;
import quiz.app.project.dias.dias.QuizDatabase.UserDB.UserDao;
import quiz.app.project.dias.dias.R;

public class MainPageFragment extends Fragment{
    TextView lblUsernameMainPage;
    TextView lblCoinsHome;
    private int userId;
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private MainPageAdapter adapter;// Get a reference to the RecyclerView in the layout

    public MainPageFragment() {
        // Required empty public constructor
    }

    public static MainPageFragment newInstance() {
        MainPageFragment fragment = new MainPageFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_page, container, false);


        recyclerView = rootView.findViewById(R.id.recyclerViewHome);
        // Get instances of the ChatDao and MessagesDao from the AppDatabase
        QuizDatabase db = QuizDatabase.getInstance(this.getContext());
        ThemeDao themeDao = db.getThemeDao();

        // Create an instance of the ChatAdapter and pass the necessary data
        this.adapter = new MainPageAdapter(themeDao.getAllThemes());

        // Create a LinearLayoutManager for the RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());

        // Set the adapter and layout manager for the RecyclerView
        recyclerView.setAdapter(this.adapter);
        recyclerView.setLayoutManager(layoutManager);

        lblUsernameMainPage = rootView.findViewById(R.id.lblUsernameHome);
        lblCoinsHome = rootView.findViewById(R.id.lblCoinsHome);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        userId = sharedPreferences.getInt("userId", 0);

        if (userId != 0) {
            QuizDatabase quizDB = QuizDatabase.getInstance(requireContext());
            UserDao userDao = quizDB.getUserDao();
            UserCurrencyDao userCurrencyDao = quizDB.getUserCurrencyDao();

            User user = userDao.getUserById(userId);
            UserCurrency existingUserCurrency = userCurrencyDao.getUserCurrencyByUserId(userId);

            if (user != null) {
                String username = user.getUsername();
                lblUsernameMainPage.setText(username);
                lblCoinsHome.setText(String.valueOf(existingUserCurrency.getAmount()));
            }
        }
        return rootView;
    }
}