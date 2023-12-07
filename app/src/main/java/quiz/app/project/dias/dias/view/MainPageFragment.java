package quiz.app.project.dias.dias.view;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import quiz.app.project.dias.dias.R;
import quiz.app.project.dias.dias.model.theme.Theme;
import quiz.app.project.dias.dias.model.theme.ThemeRepo;
import quiz.app.project.dias.dias.viewmodel.AchievementViewModel;
import quiz.app.project.dias.dias.viewmodel.ThemeViewModel;
import quiz.app.project.dias.dias.viewmodel.UserCurrencyViewModel;
import quiz.app.project.dias.dias.viewmodel.UserViewModel;

public class MainPageFragment extends Fragment {
    TextView lblUsernameMainPage;
    TextView lblCoinsHome;
    private int userId;
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private MainPageAdapter adapter;
    private ThemeViewModel themeViewModel;
    private AchievementViewModel achievementViewModel;
    private UserViewModel userViewModel;
    private UserCurrencyViewModel userCurrencyViewModel;
    private ThemeRepo themeRepository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_page, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerViewProfile);
        layoutManager = new LinearLayoutManager(getContext());

        themeViewModel = new ThemeViewModel(requireActivity().getApplication());
        achievementViewModel = new AchievementViewModel(requireActivity().getApplication());
        userViewModel = new UserViewModel(requireActivity().getApplication());
        userCurrencyViewModel = new UserCurrencyViewModel(requireActivity().getApplication());
        themeRepository = new ThemeRepo(requireActivity().getApplication());

        adapter = new MainPageAdapter(new ArrayList<>(), new ArrayList<>(), getContext());

        themeViewModel.getThemesLiveData().observe(getViewLifecycleOwner(), themes -> {
            if (themes != null) {
                adapter.setThemes(themes);
            }
        });

        achievementViewModel.getAllAchievements().observe(getViewLifecycleOwner(), achievements -> {
            if (achievements != null) {
                adapter.refreshAchievementList(achievements);
            }
        });

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

        fetchThemesAndUpdateTextView();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        return rootView;
    }

    private void fetchThemesAndUpdateTextView() {
        themeRepository.getAllThemesLiveData().observe(getViewLifecycleOwner(), themes -> {
            if (themes != null && !themes.isEmpty()) {
                adapter.setThemes(themes);
            }
        });
        themeRepository.fetchThemes(requireContext());
    }

    private String convertThemesToString(List<Theme> themes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Theme theme : themes) {
            stringBuilder.append(theme.getThemeName());
        }
        return stringBuilder.toString();
    }
}