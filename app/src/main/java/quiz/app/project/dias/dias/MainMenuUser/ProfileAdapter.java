package quiz.app.project.dias.dias.MainMenuUser;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import quiz.app.project.dias.dias.QuizDatabase.AchievementUserDB.AchievementUser;
import quiz.app.project.dias.dias.QuizDatabase.AchievementsDB.Achievements;
import quiz.app.project.dias.dias.QuizDatabase.AchievementsDB.AchievementsDao;
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
        AchievementUser achievementUser = achievementsUserList.get(position);
        Achievements achievements = getAchievementById(achievementUser.getAchievementId());
        holder.bind(achievements);
    }

    @Override
    public int getItemCount() {
        return achievementsUserList.size();
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder {
        TextView lblAchievement;
        TextView lblDescription;

        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            lblAchievement = itemView.findViewById(R.id.lblAchievement);
            lblDescription = itemView.findViewById(R.id.lblDescription);
        }

        public void bind(Achievements achievements) {
            if (lblAchievement != null && achievements != null) {
                String achievementName = achievements.getAchievementName();
                String achievementDescription = achievements.getDescription();
                lblAchievement.setText(achievementName);
                lblDescription.setText(achievementDescription);
            }
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
}