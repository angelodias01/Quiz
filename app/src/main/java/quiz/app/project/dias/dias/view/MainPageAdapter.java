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
import quiz.app.project.dias.dias.model.theme.Theme;
import quiz.app.project.dias.dias.R;

public class MainPageAdapter extends RecyclerView.Adapter<MainPageAdapter.MainPageViewHolder> {
    private List<Theme> themeList = new ArrayList<>();
    private List<Achievements> achievementsList = new ArrayList<>();
    private Context context;

    public MainPageAdapter(List<Theme> themeList, List<Achievements> achievementsList, Context context) {
        this.themeList = themeList;
        this.achievementsList = achievementsList;
        this.context = context;
    }

    @NonNull
    @Override
    public MainPageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.themes_item, parent, false);
        return new MainPageViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainPageViewHolder holder, int position) {
        final Theme theme = themeList.get(position);
        holder.lblTheme.setText(theme.getThemeName());
        holder.rootView.setOnClickListener(v -> startThemeActivity(theme));
    }

    private void startThemeActivity(Theme theme) {
        Intent intent = new Intent(context, QuizActivity.class);
        intent.putExtra("themeId", theme.getThemeId());
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return themeList.size();
    }

    public void refreshList(List<Theme> newThemeList) {
        Collections.sort(newThemeList, Comparator.comparing(Theme::getThemeName));
        themeList.clear();
        themeList.addAll(newThemeList);
        notifyDataSetChanged();
    }

    public void refreshAchievementList(List<Achievements> newAchievementsList) {
        achievementsList.clear();
        achievementsList.addAll(newAchievementsList);
        notifyDataSetChanged();
    }


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
