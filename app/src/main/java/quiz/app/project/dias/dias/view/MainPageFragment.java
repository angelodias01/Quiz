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
import java.util.ArrayList;
import quiz.app.project.dias.dias.R;
import quiz.app.project.dias.dias.viewmodel.AchievementViewModel;
import quiz.app.project.dias.dias.viewmodel.ThemeViewModel;
import quiz.app.project.dias.dias.viewmodel.UserCurrencyViewModel;
import quiz.app.project.dias.dias.viewmodel.UserViewModel;

public class MainPageFragment extends Fragment {
    TextView lblUsernameMainPage;
    TextView lblCoinsHome;
    Button btnMultiplayer;
    private int userId;
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private MainPageAdapter adapter;
    private ThemeViewModel themeViewModel;
    private AchievementViewModel achievementViewModel;
    private UserViewModel userViewModel;
    private UserCurrencyViewModel userCurrencyViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main_page, container, false);
        btnMultiplayer = rootView.findViewById(R.id.btnMultiPlayerHome);

        recyclerView = rootView.findViewById(R.id.recyclerViewProfile);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        themeViewModel = new ThemeViewModel(requireActivity().getApplication());
        achievementViewModel = new AchievementViewModel(requireActivity().getApplication());
        userViewModel = new UserViewModel(requireActivity().getApplication());
        userCurrencyViewModel = new UserCurrencyViewModel(requireActivity().getApplication());

        adapter = new MainPageAdapter(new ArrayList<>(), new ArrayList<>(), getContext());

        // Observe the changes in the theme list
        themeViewModel.getAllThemes().observe(getViewLifecycleOwner(), themes -> {
            if (themes != null) {
                adapter.refreshList(themes);
            }
        });

        // Observe the changes in the achievements list
        achievementViewModel.getAllAchievements().observe(getViewLifecycleOwner(), achievements -> {
            if (achievements != null) {
                adapter.refreshAchievementList(achievements);
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);


        lblUsernameMainPage = rootView.findViewById(R.id.lblUsernameHome);
        lblCoinsHome = rootView.findViewById(R.id.lblCoinsHome);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        userId = sharedPreferences.getInt("userId", 0);

        userViewModel.getUserById(userId).observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                String username = user.getUsername();
                lblUsernameMainPage.setText(username);
            }
        });

        userCurrencyViewModel.getUserCurrencyByUserId(userId).observe(getViewLifecycleOwner(), existingUserCurrency -> {
            if (existingUserCurrency != null) {
                int currentCoins = existingUserCurrency.getAmount();
                lblCoinsHome.setText(String.valueOf(currentCoins));
            }
        });

        btnMultiplayer.setOnClickListener(v -> {
            Toast.makeText(requireContext(), "Multiplayer is not available yet", Toast.LENGTH_SHORT).show();
        });

        return rootView;
    }
}