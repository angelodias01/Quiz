/**
 * ProfileFragment.java
 * Represents the user profile fragment displaying user information, achievements, and statistics.
 */

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
import java.util.List;
import quiz.app.project.dias.dias.model.QuizDatabase;
import quiz.app.project.dias.dias.model.achievements.Achievements;
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

    /**
     * Creates a new instance of ProfileFragment.
     *
     * @return A new instance of ProfileFragment.
     */
    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize ViewModels
        achievementUserViewModel = new ViewModelProvider(this).get(AchievementUserViewModel.class);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userCurrencyViewModel = new ViewModelProvider(this).get(UserCurrencyViewModel.class);
        achievementViewModel = new ViewModelProvider(this).get(AchievementViewModel.class);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        // Get user ID from SharedPreferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        userId = sharedPreferences.getInt("userId", 0);

        // Initialize UI elements
        recyclerView = rootView.findViewById(R.id.recyclerViewProfile);
        lblUsernameProfile = rootView.findViewById(R.id.lblUsernameProfile);
        lblCoinsProfile = rootView.findViewById(R.id.lblCoinsProfile);

        // Set up RecyclerView with layout manager
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // Observe changes in achievements and user achievements
        achievementViewModel.getAllAchievements().observe(getViewLifecycleOwner(), achievements -> {
            achievementUserViewModel.getUserAchievementByUserId(userId).observe(getViewLifecycleOwner(), achievementUsers -> {
                // Create adapter with ordered achievements list and set it to RecyclerView
                ProfileAdapter adapter = new ProfileAdapter(achievementUsers, achievements);
                List<Achievements> orderedAchievements = adapter.getOrderedAchievementsList();
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            });
        });

        // Load and display username and coins if user ID is not 0
        if (userId != 0) {
            // Load and display username
            userViewModel.getUserById(userId).observe(getViewLifecycleOwner(), user -> {
                if (user != null) {
                    String username = user.getUsername();
                    lblUsernameProfile.setText(username);
                }
            });

            // Load and display user coins
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