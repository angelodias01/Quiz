package quiz.app.project.dias.dias.MainMenuUser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import quiz.app.project.dias.dias.QuizDatabase.ScoreDB.Score;
import quiz.app.project.dias.dias.QuizDatabase.ThemeDB.Theme;
import quiz.app.project.dias.dias.R;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreAdapterViewHolder>{
    List<Score> scoreList;
    List<Theme> themeList;
    public ScoreAdapter(List<Score> scoreList, List<Theme> themeList) {
        this.scoreList = scoreList;
        this.themeList = themeList;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @NonNull
    @Override
    public ScoreAdapter.ScoreAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a view object based on the layout created (chat_item.xml)
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.score_item, parent, false);
        // Create and return an object of the ChatViewHolder type
        return new ScoreAdapter.ScoreAdapterViewHolder(rootView, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreAdapter.ScoreAdapterViewHolder holder, int position) {
        Score score = scoreList.get(position);
        Theme theme = themeList.get(position);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateandTime = sdf.format(score.getDate());

        if (getItemViewType(position) == 0) {
            holder.btnTheme.setText(theme.getThemeAbreviation());
            holder.btnScores.setText(String.valueOf(score.getScore()) + " / 7");
            holder.btnDate.setText(currentDateandTime);
        }
    }

    @Override
    public int getItemCount() {
        if (scoreList == null || scoreList.isEmpty()) {
            return 0;
        }
        return scoreList.size();
    }

    public class ScoreAdapterViewHolder extends RecyclerView.ViewHolder {

        private Context context;
        private View rootView;
        private Button btnTheme, btnScores, btnDate;

        private RecyclerView recyclerViewHome;

        public ScoreAdapterViewHolder(@NonNull View rootView, Context context) {
            super(rootView);
            this.context = context;
            this.rootView = rootView;
            this.btnTheme = this.rootView.findViewById(R.id.btnTheme);
            this.btnScores = this.rootView.findViewById(R.id.btnScores);
            this.btnDate = this.rootView.findViewById(R.id.btnDate);
        }
    }

    public void refreshList(List<Score> scoreList) {
        this.scoreList = scoreList;
        notifyDataSetChanged();
    }
}
