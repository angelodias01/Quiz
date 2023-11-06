package quiz.app.project.dias.dias.view;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import quiz.app.project.dias.dias.model.achievementsuser.AchievementUserDao;
import quiz.app.project.dias.dias.model.achievements.AchievementsDao;
import quiz.app.project.dias.dias.model.QuizDatabase;
import quiz.app.project.dias.dias.model.usercurrency.UserCurrency;
import quiz.app.project.dias.dias.model.usercurrency.UserCurrencyDao;
import quiz.app.project.dias.dias.model.user.User;
import quiz.app.project.dias.dias.model.user.UserDao;
import quiz.app.project.dias.dias.R;
import quiz.app.project.dias.dias.viewmodel.AchievementUserViewModel;
import quiz.app.project.dias.dias.viewmodel.AchievementViewModel;
import quiz.app.project.dias.dias.viewmodel.UserCurrencyViewModel;
import quiz.app.project.dias.dias.viewmodel.UserViewModel;

public class ProfileFragment extends Fragment {
    TextView lblUsernameProfile, lblCoinsProfile;
    Button btnStatistics;
    private int userId;
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private ProfileAdapter adapter;
    private AchievementViewModel achievementViewModel;
    private AchievementUserViewModel achievementUserViewModel;
    private UserViewModel userViewModel;
    private UserCurrencyViewModel userCurrencyViewModel;


    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        achievementUserViewModel = new ViewModelProvider(this).get(AchievementUserViewModel.class);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userCurrencyViewModel = new ViewModelProvider(this).get(UserCurrencyViewModel.class);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        userId = sharedPreferences.getInt("userId", 0);

        recyclerView = rootView.findViewById(R.id.recyclerViewProfile);
        // Get instances of the ChatDao and MessagesDao from the AppDatabase
        QuizDatabase db = QuizDatabase.getInstance(this.getContext());
        AchievementUserDao achievementUserDao = db.getAchievementUserDao();
        AchievementsDao achievementsDao = db.getAchievementsDao();
        UserDao userDao = db.getUserDao();
        UserCurrencyDao userCurrencyDao = db.getUserCurrencyDao();

        achievementUserViewModel.getUserAchievementByUserId(userId).observe(getViewLifecycleOwner(), achievementUsers -> {

            this.adapter = new ProfileAdapter(achievementUsers, achievementsDao.getAllAchievements());

            // Create a LinearLayoutManager for the RecyclerView
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            // Set the adapter and layout manager for the RecyclerView
            recyclerView.setAdapter(this.adapter);
            recyclerView.setLayoutManager(layoutManager);

        });

        // Create a LinearLayoutManager for the RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        // Set the adapter and layout manager for the RecyclerView
        recyclerView.setAdapter(this.adapter);
        recyclerView.setLayoutManager(layoutManager);

        btnStatistics = rootView.findViewById(R.id.btnStatisticsProfile);

        lblUsernameProfile = rootView.findViewById(R.id.lblUsernameProfile);
        lblCoinsProfile = rootView.findViewById(R.id.lblCoinsProfile);


        if (userId != 0) {
            LiveData<User> user = userViewModel.getUserById(userId);
            LiveData<UserCurrency> existingUserCurrency = userCurrencyViewModel.getUserCurrencyByUserId(userId);

            if (user != null) {
                String username = user.getValue().getUsername();
                lblUsernameProfile.setText(username);
                lblCoinsProfile.setText(String.valueOf(existingUserCurrency.getValue()));
            }
        }

        btnStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireContext(), "Statistics not available in the moment", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
}