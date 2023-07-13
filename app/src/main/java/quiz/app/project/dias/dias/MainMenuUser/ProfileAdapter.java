package quiz.app.project.dias.dias.MainMenuUser;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import quiz.app.project.dias.dias.QuizDatabase.AchievementUserDB.AchievementUser;
import quiz.app.project.dias.dias.QuizDatabase.AchievementsDB.Achievements;
import quiz.app.project.dias.dias.R;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {
    private List<AchievementUser> achievementsUserList;
    private List<Achievements> achievementsList;

    public ProfileAdapter(List<AchievementUser> achievementsUserList, List<Achievements> achievementsList) {
        this.achievementsUserList = achievementsUserList;
        this.achievementsList = achievementsList;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.achievement_item, parent, false);
        return new ProfileViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        Achievements achievements = achievementsList.get(position);
        AchievementUser achievementUser = getAchievementUserById(achievements.getAchievementId());
        holder.bind(achievements);
    }

    @Override
    public int getItemCount() {
        return achievementsList.size();
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder {
        TextView lblAchievement;
        TextView lblDescription;
        TextView lblAchievementDate;
        ConstraintLayout constraintLayout;

        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            lblAchievement = itemView.findViewById(R.id.lblAchievement);
            lblDescription = itemView.findViewById(R.id.lblDescription);
            lblAchievementDate = itemView.findViewById(R.id.lblAchievementDate);
            constraintLayout = itemView.findViewById(R.id.ConstraintLayout);
        }

        public void bind(Achievements achievements) {
            lblAchievement.setText(achievements.getAchievementName());
            lblDescription.setText(achievements.getDescription());

            AchievementUser achievementUser = getAchievementUserById(achievements.getAchievementId());
            if (achievementUser != null) {
                constraintLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#F9B01A")));

                long dateEarned = achievementUser.getDateEarned();
                Date date = new Date(dateEarned);
                SimpleDateFormat sdf = new SimpleDateFormat(" | dd/MM/yyyy | HH:mm |");
                String formattedDate = sdf.format(date);
                lblAchievementDate.setText(formattedDate);
                lblAchievementDate.setVisibility(View.VISIBLE);
            } else {
                constraintLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#999997")));
                lblAchievementDate.setVisibility(View.GONE);
            }
        }


        private boolean checkUserHasAchievement(int achievementId) {
            for (AchievementUser achievementUser : achievementsUserList) {
                if (achievementUser.getAchievementId() == achievementId) {
                    return true;
                }
            }
            return false;
        }
    }
    private Achievements getAchievementById(int achievementId) {
        for (Achievements achievement : achievementsList) {
            if (achievement.getAchievementId() == achievementId) {
                return achievement;
            }
        }
        return null;
    }
    private AchievementUser getAchievementUserById(int achievementId) {
        for (AchievementUser achievementUser : achievementsUserList) {
            if (achievementUser.getAchievementId() == achievementId) {
                return achievementUser;
            }
        }
        return null;
    }
}