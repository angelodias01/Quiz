/**
 * MainPageAdapter.java
 * Manages the RecyclerView adapter for the main page displaying themes and achievements.
 */

package quiz.app.project.dias.dias.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import quiz.app.project.dias.dias.model.achievements.Achievements;
import quiz.app.project.dias.dias.model.theme.Themes;
import quiz.app.project.dias.dias.R;

public class MainPageAdapter extends RecyclerView.Adapter<MainPageAdapter.MainPageViewHolder> {
    private List<Themes> themeList = new ArrayList<>();
    private List<Achievements> achievementsList = new ArrayList<>();
    private Context context;

    public MainPageAdapter(List<Themes> themeList, List<Achievements> achievementsList, Context context) {
        this.themeList = themeList;
        this.achievementsList = achievementsList;
        this.context = context;
    }
    public List<Themes> getThemeList() {
        return themeList;
    }
    /**
     * Sets the list of themes for the adapter and sorts them alphabetically.
     *
     * @param newThemeList The updated list of themes.
     */
    public void setThemes(List<Themes> newThemeList) {
        Collections.sort(newThemeList, Comparator.comparing(Themes::getThemeName));
        themeList.clear();
        themeList.addAll(newThemeList);
        notifyDataSetChanged();
    }

    /**
     * Sets the list of achievements for the adapter.
     *
     * @param newAchievementsList The updated list of achievements.
     */
    public void setAchievements(List<Achievements> newAchievementsList) {
        achievementsList.clear();
        achievementsList.addAll(newAchievementsList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainPageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.themes_item, parent, false);
        return new MainPageViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainPageViewHolder holder, int position) {
        final Themes theme = themeList.get(position);
        holder.lblTheme.setText(theme.getThemeName());
        holder.rootView.setOnClickListener(v -> startThemeActivity(theme));
    }

    /**
     * Starts the QuizActivity with the selected theme.
     *
     * @param theme The selected theme to start the activity with.
     */
    private void startThemeActivity(Themes theme) {
        Intent intent = new Intent(context, QuizActivity.class);
        intent.putExtra("themeId", theme.getThemeId());
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return themeList.size();
    }

    /**
     * Refreshes the theme list with the updated list and sorts it alphabetically.
     *
     * @param newThemeList The updated list of themes.
     */
    public void refreshList(List<Themes> newThemeList) {
        Collections.sort(newThemeList, Comparator.comparing(Themes::getThemeName));
        themeList.clear();
        themeList.addAll(newThemeList);
        notifyDataSetChanged();
    }

    /**
     * Refreshes the achievement list with the updated list.
     *
     * @param newAchievementsList The updated list of achievements.
     */
    public void refreshAchievementList(List<Achievements> newAchievementsList) {
        achievementsList.clear();
        achievementsList.addAll(newAchievementsList);
        notifyDataSetChanged();
    }

    /**
     * ViewHolder class for the RecyclerView.
     */
    public static class MainPageViewHolder extends RecyclerView.ViewHolder {
        private View rootView;
        private TextView lblTheme;

        public MainPageViewHolder(@NonNull View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.lblTheme = rootView.findViewById(R.id.lblAchievement);
        }
    }
}
