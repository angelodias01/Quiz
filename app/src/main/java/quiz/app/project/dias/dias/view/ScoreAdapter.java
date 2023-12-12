/**
 * ProfileAdapter.java
 * The ScoreAdapter class is a RecyclerView adapter for displaying scores with themes.
 */

package quiz.app.project.dias.dias.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
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
    private OnLongClickListener onLongClickListener;
    private OnClickListener onClickListener;

    /**
     * Constructs a ScoreAdapter with the given score and theme lists.
     *
     * @param scoreList The list of scores.
     * @param themeList The list of themes.
     */
    public ScoreAdapter(List<Score> scoreList, List<Theme> themeList) {
        this.scoreList = mergeScoreWithTheme(scoreList, themeList);
    }

    /**
     * Sets the long click listener for the adapter.
     *
     * @param listener The long click listener.
     */
    public void setOnLongClickListener(OnLongClickListener listener) {
        this.onLongClickListener = listener;
    }

    /**
     * Sets the click listener for the adapter.
     *
     * @param listener The click listener.
     */
    public void setOnClickListener(OnClickListener listener) {
        this.onClickListener = listener;
    }

    /**
     * Gets the score with theme at the specified position.
     *
     * @param position The position of the item.
     * @return The ScoreWithTheme object at the specified position, or null if the position is out of bounds.
     */
    public ScoreWithTheme getItem(int position) {
        if (position >= 0 && position < scoreList.size()) {
            return scoreList.get(position);
        }
        return null;
    }

    /**
     * Removes the item at the specified position from the adapter.
     *
     * @param position The position of the item to be removed.
     */
    public void removeItem(int position) {
        if (position >= 0 && position < scoreList.size()) {
            scoreList.remove(position);
            notifyItemRemoved(position);
        }
    }

    @Override
    public int getItemCount() {
        if (scoreList == null || scoreList.isEmpty()) {
            return 1; // Display the "no data" item
        }
        return scoreList.size();
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
    public ScoreAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.score_item, parent, false);
        return new ScoreAdapterViewHolder(rootView, parent.getContext());
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ScoreAdapterViewHolder holder, int position) {
        if (getItemViewType(position) == 0) {
            ConstraintLayout layout = holder.rootView.findViewById(R.id.layout);

            ScoreWithTheme scoreWithTheme = scoreList.get(position);
            Score score = scoreWithTheme.score;
            Theme theme = scoreWithTheme.theme;

            holder.btnDate.setClickable(false);
            holder.btnTheme.setClickable(false);
            holder.btnScores.setClickable(false);

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            String currentDateandTime = sdf.format(score.getDate());

            holder.btnTheme.setText(theme != null ? theme.getThemeAbreviation() : "");
            holder.btnScores.setText(String.valueOf(score.getScore()) + " / 7");
            holder.btnDate.setText(currentDateandTime);

            holder.layout.setOnLongClickListener(view -> {
                if (onClickListener != null) {
                    onClickListener.onClick(position);
                }
                return true;
            });
            holder.btnTheme.setOnLongClickListener(view -> {
                if (onLongClickListener != null) {
                    onLongClickListener.onLongClick(position);
                }
                return true;
            });
            holder.btnScores.setOnLongClickListener(view -> {
                if (onLongClickListener != null) {
                    onLongClickListener.onLongClick(position);
                }
                return true;
            });
            holder.btnDate.setOnLongClickListener(view -> {
                if (onLongClickListener != null) {
                    onLongClickListener.onLongClick(position);
                }
                return true;
            });

            holder.layout.setOnClickListener(view -> {
                if (onClickListener != null) {
                    onClickListener.onClick(position);
                }
            });
            holder.btnTheme.setOnClickListener(view -> {
                if (onClickListener != null) {
                    onClickListener.onClick(position);
                }
            });
            holder.btnScores.setOnClickListener(view -> {
                if (onClickListener != null) {
                    onClickListener.onClick(position);
                }
            });
            holder.btnDate.setOnClickListener(view -> {
                if (onClickListener != null) {
                    onClickListener.onClick(position);
                }
            });

        } else {
            holder.btnTheme.setVisibility(View.GONE);
            holder.btnScores.setVisibility(View.GONE);
            holder.btnDate.setText(R.string.noPlayed);

            holder.btnDate.setClickable(false);
        }
    }

    /**
     * The ScoreAdapterViewHolder class represents a ViewHolder for the ScoreAdapter.
     */
    public class ScoreAdapterViewHolder extends RecyclerView.ViewHolder {
        private Context context;
        private View rootView;
        private Button btnTheme, btnScores, btnDate;
        private ConstraintLayout layout;

        /**
         * Constructs a ScoreAdapterViewHolder with the given rootView and context.
         *
         * @param rootView The root view of the ViewHolder.
         * @param context  The context of the ViewHolder.
         */
        public ScoreAdapterViewHolder(@NonNull View rootView, Context context) {
            super(rootView);
            this.context = context;
            this.rootView = rootView;

            this.btnTheme = this.rootView.findViewById(R.id.btnTheme);
            this.btnScores = this.rootView.findViewById(R.id.btnScores);
            this.btnDate = this.rootView.findViewById(R.id.btnDate);
            this.layout = this.rootView.findViewById(R.id.layout);
        }
    }

    /**
     * The ScoreWithTheme class represents a combination of a Score and a Theme.
     */
    public static class ScoreWithTheme {
        public Score score;
        public Theme theme;

        /**
         * Constructs a ScoreWithTheme with the given score and theme.
         *
         * @param score The score.
         * @param theme The theme.
         */
        public ScoreWithTheme(Score score, Theme theme) {
            this.score = score;
            this.theme = theme;
        }
    }

    private List<ScoreWithTheme> mergeScoreWithTheme(List<Score> scoreList, List<Theme> themeList) {
        List<ScoreWithTheme> mergedList = new ArrayList<>();
        if (themeList == null || themeList.isEmpty()) {
            return mergedList;
        }

        for (Score score : scoreList) {
            Theme theme = findThemeById(themeList, score.getThemeId());
            if (theme != null) {
                mergedList.add(new ScoreWithTheme(score, theme));
            }
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

    /**
     * The OnLongClickListener interface provides a callback for long clicks.
     */
    public interface OnLongClickListener {
        void onLongClick(int position);
    }

    /**
     * The OnClickListener interface provides a callback for clicks.
     */
    public interface OnClickListener {
        void onClick(int position);
    }

    /**
     * Refreshes the list with new score and theme data.
     *
     * @param scoreList The list of scores.
     * @param themeList The list of themes.
     */
    public void refreshList(List<Score> scoreList, List<Theme> themeList) {
        this.scoreList = mergeScoreWithTheme(scoreList, themeList);
        notifyDataSetChanged();
    }
}