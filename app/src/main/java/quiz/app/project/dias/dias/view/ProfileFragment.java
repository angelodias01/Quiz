package quiz.app.project.dias.dias.view;
import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
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

import quiz.app.project.dias.dias.model.QuizDatabase;
import quiz.app.project.dias.dias.model.achievements.Achievements;
import quiz.app.project.dias.dias.model.usercurrency.UserCurrency;
import quiz.app.project.dias.dias.model.user.User;
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
        achievementViewModel = new ViewModelProvider(this).get(AchievementViewModel.class); // Add this line
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        userId = sharedPreferences.getInt("userId", 0);

        recyclerView = rootView.findViewById(R.id.recyclerViewProfile);

        QuizDatabase db = QuizDatabase.getInstance(this.getContext());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        achievementViewModel.getAllAchievements().observe(getViewLifecycleOwner(), achievements -> {
            achievementUserViewModel.getUserAchievementByUserId(userId).observe(getViewLifecycleOwner(), achievementUsers -> {
                ProfileAdapter adapter = new ProfileAdapter(achievementUsers, achievements);
                List<Achievements> orderedAchievements = adapter.getOrderedAchievementsList();
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            });
        });


        lblUsernameProfile = rootView.findViewById(R.id.lblUsernameProfile);
        lblCoinsProfile = rootView.findViewById(R.id.lblCoinsProfile);

        if (userId != 0) {
            userViewModel.getUserById(userId).observe(getViewLifecycleOwner(), user -> {
                if (user != null) {
                    String username = user.getUsername();
                    lblUsernameProfile.setText(username);
                }
            });

            userCurrencyViewModel.getUserCurrencyByUserId(userId).observe(getViewLifecycleOwner(), existingUserCurrency -> {
                if (existingUserCurrency != null) {
                    int currentCoins = existingUserCurrency.getAmount();
                    lblCoinsProfile.setText(String.valueOf(currentCoins));
                }
            });
        }

        return rootView;
    }
}
