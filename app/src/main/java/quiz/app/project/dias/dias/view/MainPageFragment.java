package quiz.app.project.dias.dias.view;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import quiz.app.project.dias.dias.model.achievements.Achievements;
import quiz.app.project.dias.dias.model.achievements.AchievementsDao;
import quiz.app.project.dias.dias.model.QuizDatabase;
import quiz.app.project.dias.dias.model.theme.Theme;
import quiz.app.project.dias.dias.model.theme.ThemeDao;
import quiz.app.project.dias.dias.model.usercurrency.UserCurrency;
import quiz.app.project.dias.dias.model.usercurrency.UserCurrencyDao;
import quiz.app.project.dias.dias.model.user.User;
import quiz.app.project.dias.dias.model.user.UserDao;
import quiz.app.project.dias.dias.R;

public class MainPageFragment extends Fragment {
    TextView lblUsernameMainPage;
    TextView lblCoinsHome;
    Button btnMultiplayer;
    private int userId;
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private MainPageAdapter adapter;


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
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main_page, container, false);
        btnMultiplayer = rootView.findViewById(R.id.btnMultiPlayerHome);

        recyclerView = rootView.findViewById(R.id.recyclerViewProfile);
        QuizDatabase db = QuizDatabase.getInstance(this.getContext());
        ThemeDao themeDao = db.getThemeDao();
        AchievementsDao achievementDao = db.getAchievementsDao(); // Get the AchievementDao

        List<Theme> themeList = themeDao.getThemes();
        List<Achievements> achievementList = achievementDao.getAllAchievements(); // Retrieve the list of achievements

        this.adapter = new MainPageAdapter(themeList, achievementList, getContext()); // Pass the achievementList to the adapter
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setAdapter(this.adapter);
        recyclerView.setLayoutManager(layoutManager);

        lblUsernameMainPage = rootView.findViewById(R.id.lblUsernameHome);
        lblCoinsHome = rootView.findViewById(R.id.lblCoinsHome);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        userId = sharedPreferences.getInt("userId", 0);

        if (userId != 0) {
            UserDao userDao = db.getUserDao();
            UserCurrencyDao userCurrencyDao = db.getUserCurrencyDao();

            User user = userDao.getUserById(userId);
            UserCurrency existingUserCurrency = userCurrencyDao.getUserCurrencyByUserId(userId);

            if (user != null) {
                String username = user.getUsername();
                lblUsernameMainPage.setText(username);
                lblCoinsHome.setText(String.valueOf(existingUserCurrency.getAmount()));
            }
        }
        btnMultiplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireContext(), "Multiplayer is not available yet", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Update the coins when returning from a quiz
        QuizDatabase db = QuizDatabase.getInstance(getContext());
        UserCurrencyDao userCurrencyDao = db.getUserCurrencyDao();

        UserCurrency userCurrency = userCurrencyDao.getUserCurrencyByUserId(userId);
        if (userCurrency != null) {
            int currentCoins = userCurrency.getAmount();
            lblCoinsHome.setText(String.valueOf(currentCoins));
        }
    }

}
