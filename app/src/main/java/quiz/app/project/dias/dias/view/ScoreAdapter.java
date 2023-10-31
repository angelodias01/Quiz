package quiz.app.project.dias.dias.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import quiz.app.project.dias.dias.model.score.Score;
import quiz.app.project.dias.dias.model.theme.Theme;
import quiz.app.project.dias.dias.R;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreAdapterViewHolder> {
    private List<ScoreWithTheme> scoreList;

    public ScoreAdapter(List<Score> scoreList, List<Theme> themeList) {
        this.scoreList = mergeScoreWithTheme(scoreList, themeList);
    }

    private List<ScoreWithTheme> mergeScoreWithTheme(List<Score> scoreList, List<Theme> themeList) {
        List<ScoreWithTheme> mergedList = new ArrayList<>();
        for (Score score : scoreList) {
            Theme theme = findThemeById(themeList, score.getThemeId());
            mergedList.add(new ScoreWithTheme(score, theme));
        }
        return mergedList;
    }

    private Theme findThemeById(List<Theme> themeList, int themeId) {
        for (Theme theme : themeList) {
            if (theme.getThemeId() == themeId) {
                return theme;
            }
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        if (scoreList == null || scoreList.isEmpty()) {
            return 1;
        } else {
            return 0;
        }
    }

    @NonNull
    @Override
    public ScoreAdapter.ScoreAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.score_item, parent, false);
        return new ScoreAdapter.ScoreAdapterViewHolder(rootView, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreAdapter.ScoreAdapterViewHolder holder, int position) {
        if (getItemViewType(position) == 0) {
            ScoreWithTheme scoreWithTheme = scoreList.get(position);
            Score score = scoreWithTheme.score;
            Theme theme = scoreWithTheme.theme;

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            String currentDateandTime = sdf.format(score.getDate());

            holder.btnTheme.setText(theme != null ? theme.getThemeAbreviation() : "");
            holder.btnScores.setText(String.valueOf(score.getScore()) + " / 7");
            holder.btnDate.setText(currentDateandTime);

            // Make the button clickable only if the user has played
            holder.btnDate.setClickable(true);
        } else {
            holder.btnTheme.setVisibility(View.GONE);
            holder.btnScores.setVisibility(View.GONE);
            holder.btnDate.setText("You haven't played yet!");

            // Make the button unclickable
            holder.btnDate.setClickable(false);
        }
    }

    @Override
    public int getItemCount() {
        if (scoreList == null || scoreList.isEmpty()) {
            return 1; // Display the "no data" item
        }
        return scoreList.size();
    }

    public class ScoreAdapterViewHolder extends RecyclerView.ViewHolder {
        private Context context;
        private View rootView;
        private Button btnTheme, btnScores, btnDate;

        public ScoreAdapterViewHolder(@NonNull View rootView, Context context) {
            super(rootView);
            this.context = context;
            this.rootView = rootView;
            this.btnTheme = this.rootView.findViewById(R.id.btnTheme);
            this.btnScores = this.rootView.findViewById(R.id.btnScores);
            this.btnDate = this.rootView.findViewById(R.id.btnDate);
        }
    }

    public static class ScoreWithTheme {
        public Score score;
        public Theme theme;

        public ScoreWithTheme(Score score, Theme theme) {
            this.score = score;
            this.theme = theme;
        }
    }

    public void refreshList(List<Score> scoreList, List<Theme> themeList) {
        this.scoreList = mergeScoreWithTheme(scoreList, themeList);
        notifyDataSetChanged();
    }
}
